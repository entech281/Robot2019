package frc.logging;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import java.util.HashMap;
import java.util.Map;



public class SmartDashboardVerifier {
    
    private static Map<String,Long> counters = new HashMap<>();    
   
    private static long getCounter(String key){
        long value = 0;
        Long l = counters.get(key);        
        if ( l == null){
            counters.put(key,value);
        }
        else{
            value = l;
        }
        return value;
    }

    private static void incrementCounter(String key){
        long value = getCounter(key);
        counters.put(key,value+1);
    }
    
    public static void putNumber(String key, double output){
        long counter = getCounter(key);
  
        boolean valid = true;
        if ( Double.isNaN(output) || Double.isInfinite(output)){
            valid = false;
        }
        
        if(valid){
            if ( (counter % 10) == 0){
                SmartDashboard.putNumber(key, output);
                
            }
            
        }
        incrementCounter(key);
    }
}