package frc.robot.core;

/**
 * The robot pose, relative to some reference frame
 * @author dcowden
 */
public class RobotPose {
    
    double x,y,angle;

    public RobotPose(double x, double y, double angle){
        this.x = x;
        this.y = y;
        this.angle = angle;
    }
    
    //this is sideways offset
    public double getX(){
        return x;
    }
    
    //this is front-to-back offset
    public double getY(){
        return y;
    }
    
    public double getAngle(){
        return angle;
    }
}
