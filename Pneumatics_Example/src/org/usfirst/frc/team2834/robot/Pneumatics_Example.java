
package org.usfirst.frc.team2834.robot;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.RobotDrive.MotorType;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.Timer;



public class Pneumatics_Example extends IterativeRobot {
	Joystick gamepad = new Joystick(0);
    RobotDrive motors = new RobotDrive(0,1,2,3);
    Compressor compressor_1 = new Compressor(0);
    Compressor compressor_2 = new Compressor(1);
    Solenoid shootOut = new Solenoid(0);
    Solenoid shootIn = new Solenoid(1);
    Solenoid liftUp = new Solenoid(2);
    Solenoid liftDown = new Solenoid(3);
    public void robotInit() {
        
    	compressor_1.setClosedLoopControl(true); //Compressor will turn on if PSI is below ~120 (Pressure switch Closed)
        compressor_2.setClosedLoopControl(true);
    }

    

    public void autonomousPeriodic() {

    }

    

    public void teleopPeriodic() {
    	
        solenoidControls();
    }
    
    

    public void testPeriodic() {
    
    }
    
    public void solenoidControls() {
    	
    	if (gamepad.getRawButton(4)) { //'Y' button to lift
        	liftUp.set(true);
        	liftDown.set(false);
        }
        if (gamepad.getRawButton(2)) { //Just shoots
        	shootOut.set(true);
        	shootOut.set(false);
        }
        if (gamepad.getRawButton(1)) { //'A' button to lift then shoots then comes back down
        	liftUp.set(true);
        	liftDown.set(false);
        	Timer.delay(2);
        	shootOut.set(true);
        	shootIn.set(false);
        	Timer.delay(1);
        	liftUp.set(false);
        	liftDown.set(true);
        }
        else { //Default state for the solenoids when no button is pressed
        	liftUp.set(false);
        	liftDown.set(true);
        	shootIn.set(true);
        	shootOut.set(false);
        }
    }
    
}
