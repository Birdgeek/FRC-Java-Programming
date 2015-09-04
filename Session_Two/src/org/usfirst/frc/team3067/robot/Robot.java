
package org.usfirst.frc.team3067.robot;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;


public class Robot extends IterativeRobot {
	
	public boolean isLifted;
	public boolean isLifting;
	Solenoid liftUp = new Solenoid(0);
	Solenoid liftDown = new Solenoid(1);
	Solenoid clampIn = new Solenoid(2);
	Solenoid clampOut = new Solenoid(3);
	Compressor comp = new Compressor(0);
	Talon front_left = new Talon(0);
	Talon front_right = new Talon(1);
	Talon back_left = new Talon(2);
	Talon back_right = new Talon(3);
	Joystick rightStick = new Joystick(0);
	DigitalInput topLimit = new DigitalInput(1);
	public enum robotState { OPEN, DRIVING, LIFTED, DROPPING };
	robotState state;	   
	RobotDrive chassis = new RobotDrive(front_left, back_left, front_right, back_right);
	DigitalInput photoEye = new DigitalInput(2);
	int counter = 0;
	boolean hasSeen = false;
	Encoder encoder = new Encoder(1, counter);
	
    public void robotInit() {
    	
    	comp.setClosedLoopControl(true); // Below 120 PSI
    	isLifted = false;
    	isLifting = false;
    	liftUp.set(false);
    	liftDown.set(true);
    	SmartDashboard.putBoolean("Lifter Value", isLifted);
    	SmartDashboard.putNumber("Front Right Speed", front_right.get());
    	SmartDashboard.putNumber("Front Left Speed", front_left.get());
    	SmartDashboard.putNumber("Back Right Speed", back_right.get());
    	SmartDashboard.putNumber("Back Left Speed", back_left.get());        	
    	state = robotState.OPEN;    	
    }

    
    public void autonomousPeriodic() {

    	if (photoEye.get()) {
    		hasSeen = true;
    	}
    	else if (!photoEye.get() && hasSeen) {
    		counter++;
    	}
    	encoder.getDistance();
    	Timer.delay(1.0);
    }

    
    public void teleopPeriodic() {
        
    	chassis.arcadeDrive(rightStick);
    	
    	if (rightStick.getRawButton(10)) {
    		switch (state) {
			case DRIVING:
				state = robotState.LIFTED;
				updateRobot();
				break;
			case DROPPING:
				state = robotState.OPEN;
				updateRobot();
				break;
			case LIFTED:
				state = robotState.DROPPING;
				updateRobot();
				break;
			case OPEN:
				state = robotState.DRIVING;
				updateRobot();
				break;
			default:
				state = robotState.OPEN;
				updateRobot();
				break;    		
    		}
    	}
    	if (rightStick.getRawButton(1)) {
    		isLifting = !isLifting;
    		updatedLifter();
    	}    	
    }
    
    private void updateRobot() {
    	
    	switch (state) {
		case DRIVING:
			clampIn.set(true);
			clampOut.set(false);
			liftUp.set(false);
			liftDown.set(true);			
			break;
			
		case DROPPING:
			liftDown.set(true);
			liftUp.set(false);
			clampIn.set(false);
			clampOut.set(true);
			break;
			
		case LIFTED:
			clampIn.set(true);
			clampOut.set(false);
			liftUp.set(true);
			liftDown.set(false);
			break;
			
		case OPEN:
			clampOut.set(true);
			clampIn.set(false);
			liftUp.set(false);
			liftDown.set(true);			
			break;
			
		default:
			SmartDashboard.putString("Err", "You messed up somehow");
			break;
    	
    	}
    }
    
    private void updatedLifter() {
    	if (topLimit.get()) {
    		isLifted = true;
    		isLifting = false;
    	}
    	else {
    		isLifted = false;
    	}
		if (isLifting && !isLifted) {
			liftUp.set(true);
			liftDown.set(false);
		}
		else if (isLifted && isLifting) {
			liftUp.set(false);
			liftDown.set(true);
		}		
	}


	@SuppressWarnings("unused")
	private void setAllMotors(double speed) {
		front_left.set(speed);
		back_left.set(speed);
		front_right.set(speed);
		back_right.set(speed);
		
	}


	public void testPeriodic() {
    
    }
    
}
