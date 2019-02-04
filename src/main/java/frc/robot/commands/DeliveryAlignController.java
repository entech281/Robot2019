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
public class DeliveryAlignController implements DriveController{
    
    public DriveInstruction getDriveInstruction( RobotPose robotPose, OperatorDriveInput driverInput){
        //TODO: here we discard operator input except for Y.
        return null;
    }
}
