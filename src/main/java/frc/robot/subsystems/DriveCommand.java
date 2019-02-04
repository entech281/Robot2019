package frc.robot.subsystems;

/**
 *
 * A command for a drive system
 */
public class DriveCommand {
    double x,y,z;
    double field_angle, field_x, field_y;

    public DriveCommand(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.field_angle = 0.0;    
        this.field_x = 0.0;
        this.field_y = 0.0;
    }

    public DriveCommand(double x, double y, double z, double angle) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.field_angle = angle;    
        this.field_x = 0.0;
        this.field_y = 0.0;
    }

    public DriveCommand(double x, double y, double z, double angle, double fieldX, double fieldY) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.field_angle = angle;    
        this.field_x = fieldX;
        this.field_y = fieldY;
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

    public double getFieldAngle() {
        return field_angle;
    }

    public double getFieldX() {
        return field_x;
    }

    public double getFieldY() {
        return field_y;
    }
}
