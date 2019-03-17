package frc.logging;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.drive.DriveInput;


public class SmartDashboardLogger {

    public static final long UPDATE_RATE_MILLIS = 300;    
    private static UpdateRateTracker updateTracker = new UpdateRateTracker(UPDATE_RATE_MILLIS);  
    
    public static void putNumber(String key, double output){
  
        boolean valid = true;
        if ( Double.isNaN(output) || Double.isInfinite(output)){
            valid = false;
        }
        
        if(valid && updateTracker.shouldUpdate(key)){
            SmartDashboard.putNumber(key, output);            
        }
        
    }
    public static void putBoolean(String name, boolean value){
        if ( updateTracker.shouldUpdate(name)){
            SmartDashboard.putBoolean(name, value);
        }
    }
    
    public static void putDriveInput(String name, DriveInput input){
        SmartDashboardLogger.putNumber(name + " X", input.getX());
        SmartDashboardLogger.putNumber(name +" Y", input.getY());
        SmartDashboardLogger.putNumber(name +" Z", input.getZ());
        SmartDashboardLogger.putNumber(name +" Angle", input.getFieldAngle());        
    }    
}