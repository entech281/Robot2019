package frc.logging;

/**
 * VERY lightweight. Just gets the job done
 * @author dcowden
 */
public class Logging {
    
    private Logging(){}
    private static boolean enableDebug = true;
    
    public static void setEnableDebug(boolean enableDebug){
        Logging.enableDebug = enableDebug;
    }
    
    public static Logger getLogger(String name){
        return new Logger(name,Logging.enableDebug);
    }
}
