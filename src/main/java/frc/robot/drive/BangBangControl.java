package frc.robot.drive;

/**
 *
 * @author dcowden
 */
public class BangBangControl {
    
    private double threshold;
    private double onOutput;
    
    public BangBangControl( double threshold, double onOutput){
        this.threshold = threshold;
        this.onOutput = onOutput;
    }
    
    public double control(double desired, double actual){
        double delta = (actual - desired);
        if ( delta > threshold){
            return -onOutput;
        }
        else if ( delta < -threshold){
            return onOutput;
        }
        return 0.0;
    }
}
