package frc.robot.drive;

/**
 *
 *          |
 *   maxOut +      +-----
 *          |     /
 *          |    /
 *   minOut +   +
 *          |   |
 *          +---+--+---
 *            tol  thres
 * @author mandrews
 */
public class CappedLinearControl {
    
    private double tolerance, threshold;
    private double minOutput, maxOutput;
    
    public CappedLinearControl( double tolerance, double threshold, double minOutput, double maxOutput){
        this.tolerance = tolerance;
        this.threshold = threshold;
        this.minOutput = minOutput;
        this.maxOutput = maxOutput;
    }
    
    public double control(double desired, double actual){
        double delta = (actual - desired);
        double scaledOut = ((maxOutput - minOutput)*((Math.abs(delta) - tolerance)/(threshold-tolerance))) + minOutput;
        if ( delta > threshold){
            return -maxOutput;
        } else if ( delta < -threshold){
            return maxOutput;
        } else if (delta > tolerance) {
            return -scaledOut;
        } else if (delta < -tolerance) {
            return scaledOut;
        } else {
            return 0.0;
        }
    }
}
