package frc.robot.drive;

import frc.pid.Controller;

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
public class CappedLinearControl implements Controller{
    
    private double tolerance, threshold;
    private double minOutput, maxOutput;
    
    public CappedLinearControl( double tolerance, double threshold, double minOutput, double maxOutput){
        this.tolerance = tolerance;
        this.threshold = threshold;
        this.minOutput = minOutput;
        this.maxOutput = maxOutput;
    }
    
    public double getOutput(double actual, double desired){
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
