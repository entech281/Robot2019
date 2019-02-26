/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package frc.logging;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.drive.DriveInput;

/**
 *
 * @author dcowden
 */
public class SmartDashboardLogger {
    
    public static void putOnSmartDashboard(String name, DriveInput input){
    SmartDashboard.putNumber(name + " X", input.getX());
    SmartDashboard.putNumber(name +" Y", input.getY());
    SmartDashboard.putNumber(name +" Z", input.getZ());
    SmartDashboard.putNumber(name +" Angle", input.getFieldAngle());        
    }
}
