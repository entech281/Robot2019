/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package frc.logging;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.logging.SmartDashboardVerifier;
import frc.robot.drive.DriveInput;

/**
 *
 * @author dcowden
 */
public class SmartDashboardLogger {
    
    public static void putOnSmartDashboard(String name, DriveInput input){
    SmartDashboardVerifier.putNumber(name + " X", input.getX());
    SmartDashboardVerifier.putNumber(name +" Y", input.getY());
    SmartDashboardVerifier.putNumber(name +" Z", input.getZ());
    SmartDashboardVerifier.putNumber(name +" Angle", input.getFieldAngle());        
    }
}
