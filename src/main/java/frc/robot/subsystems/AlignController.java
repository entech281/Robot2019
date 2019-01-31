package frc.robot.subsystems;

/**
 * Generates output that aligns the robot 
 * left-to-right based on sensor input
 * 
 * input: navx angle
 * sensor feedback ( offset from center)
 * joystick inputs
 * output: x and y control signals for drive
 * @author dcowden
 */
public class AlignController {
    
    public AlignController(){
        
    }
    
    public DriveCommand getOutput(DriveCommand input, RobotPose pose ){
        return null;
    }
}
