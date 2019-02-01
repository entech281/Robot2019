package frc.robot.subsystems;

import frc.robot.core.RobotPose;

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
public class AlignController extends DriveFilter{
    
    public AlignController(){
        
    }

    @Override
    public DriveCommand doFilter(DriveCommand input, RobotPose state) {
        //here we do the logic to read sensors and all that.
        //ideally, MOST sensor data is already availalbe in state.
        return input;
    }


}
