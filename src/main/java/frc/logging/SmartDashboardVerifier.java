package frc.logging;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class SmartDashboardVerifier {
    
    public static void putNumber(String key, double output){
        boolean valid = true;
        if ( Double.isNaN(output) || Double.isInfinite(output)){
            valid = false;
        }
        if(valid){
            SmartDashboard.putNumber(key, output);
        }
    }
}