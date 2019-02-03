package frc.robot.oi;

/**
 *
 * A command for a drive system
 */
public class OperatorDriveCommand {
    
    private double x = 0;
    private double y = 0;
    private double z = 0;
    private boolean fieldAbsolute = false;
    
    public OperatorDriveCommand(double x, double y, double z,  boolean fieldAbsolute){
        this.x = x;
        this.y = y;
        this.z = z;
        this.fieldAbsolute = fieldAbsolute;
    }
    
    public double getX(){
        return x;
    }
    
    public double getY(){
        return y;
    }
    
    public double getZ(){
        return z;
    }
    public boolean isFieldAbsolute(){
        return fieldAbsolute ;
    }
    
    @Override
    public String toString(){
        return String.format("x=%f,y=%f,z=%f,absolute=%s",x,y,z,fieldAbsolute+"");
    }
}
