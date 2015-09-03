
package org.usfirst.frc.team3067.robot;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Solenoid;


public class Robot extends IterativeRobot {
 
	public enum roboState { OPEN, CLAMPED, LIFTED };
	roboState state;
	Joystick rightStick = new Joystick(1);
	Solenoid liftUp = new Solenoid(0);
	Solenoid liftDown = new Solenoid(1);
	Solenoid clampClose = new Solenoid(2);
	Solenoid clampOpen = new Solenoid(3);
	
    public void robotInit() {

    	state = roboState.OPEN;
    }

    
    public void autonomousPeriodic() {

    }

    
    public void teleopPeriodic() {
    	
    	if (rightStick.getRawButton(1)) {
    		switch (state) {
			case CLAMPED:
				state = roboState.LIFTED;
				updateState();
				break;
			case LIFTED:
				state = roboState.OPEN;
				updateState();
				break;
			case OPEN:
				state = roboState.CLAMPED;
				updateState();
				break;
			default:
				state = roboState.OPEN;
				updateState();
				break;
    		
    		}
    	}

    }
    
    
    private void updateState() {    	    	
        
    	switch (state) {
		case CLAMPED:
			clampOpen.set(false);
			clampClose.set(true);
			break;
		case LIFTED:
			liftUp.set(true);
			liftDown.set(false);
			break;
		case OPEN:
			liftDown.set(true);
			liftUp.set(false);
			clampOpen.set(true);
			clampClose.set(false);			
			break;
		default:
			break;                
        }		
	}


	public void testPeriodic() {
    
    }
    
}
