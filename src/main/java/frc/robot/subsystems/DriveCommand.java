package frc.robot.subsystems;

/**
 *
 * A command for a drive system
 */
public class DriveCommand {
    double x,y,z;

    public DriveCommand(double x, double y, double z){
        this.x = x;
        this.y = y;
        this.z = z;    
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
}
