
package org.usfirst.frc.team3067.robot;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.Timer;

	

public class Robot extends IterativeRobot {
	Talon front_left = new Talon(0);
	Talon front_right = new Talon(1);
	Talon back_left = new Talon(2);
	Talon back_right = new Talon(3);
	
	
	Joystick rightStick = new Joystick(0);
	Joystick leftStick = new Joystick(1);
	
	RobotDrive chassis = new RobotDrive(front_left, back_left, front_right, back_right);
	
    public void robotInit() {
    	
    	
    }

    public void autonomousPeriodic() {
    	setAllMotors(.5);
    	Timer.delay(2);
    	setAllMotors(0);

    }

    public void teleopPeriodic() {
    	
    	
    	chassis.arcadeDrive(rightStick); // Runs Arcade Drive on Right Stick
    	
    	chassis.tankDrive(leftStick, rightStick); // Tank Drive with 2 sticks
    	
    	chassis.mecanumDrive_Polar(rightStick.getY(), rightStick.getX(), rightStick.getZ()); // Mecanum Drive
    	
        
    }

    public void testPeriodic() {
    
    }
    
    public void setAllMotors(double speed) {
    	
    	front_left.set(speed);
    	front_right.set(speed);
    	back_left.set(speed);
    	back_right.set(speed);
    	
    }
}
