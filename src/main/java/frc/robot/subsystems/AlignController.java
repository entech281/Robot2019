/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

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
    
    public AlignController() {
        
    }
    
    public DriveCommand getOutput(DriveCommand input, RobotPose pose) {
        return null;
    }
}
