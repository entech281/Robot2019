package frc.logging;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author dcowden
 */
public class Logger {
    private String name;
    private boolean debugEnabled;
    private SimpleDateFormat df = new SimpleDateFormat("HH:mm:ss.SSS");    
    
    Logger(String name, boolean debugEnabled) {
        this.name = name;
        this.debugEnabled = debugEnabled;
    }
    
    public void debug(Object message){
        if ( debugEnabled){
            logMessage("DEBUG",message);
        }            
    }
    
    public void warn(Object message){
        logMessage("WARN",message);
    }
    
    protected void logMessage(String level, Object message){   
        System.out.println(
                String.format("%s  [%s] %s",df.format(new Date() ), level, message+"")
        );
    }
}
