/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package frc.robot.commands;

import frc.robot.navigation.RobotPose;
import frc.robot.oi.OperatorDriveInput;
import frc.robot.subsystems.DriveInstruction;

/**
 * So that we can unit test this code-- its among the most complex
 * @author dcowden
 */
public class NormalDriveController implements DriveController{
    
    public DriveInstruction getDriveInstruction( RobotPose robotPose, OperatorDriveInput driverInput){

        double z = 0.0;
        if (driverInput.isCanTwist()){
            z = driverInput.getZ();
        }
        //this can be complex! so its good to allow it to be tested
        if ( driverInput.isFieldAbsolute()){
            return new DriveInstruction(
                driverInput.getX(),
                driverInput.getY(),
                z,
                robotPose.getRobotAngleOnField()
            );
        }
        else{
            return new DriveInstruction(
                driverInput.getX(),
                driverInput.getY(),
                z,
                0.0
            );            
        }
    }
}
