package frc.robot.commands;

/**
 * Handles the math of a nudge from side to side
 * Ideally, gives output proportional to travel remaining
 * @author dcowden
 */
public class NudgeController {
    
    public static final double TOLERANCE = 0.5;
    
    private double currentValue = 0.0;
    private double gain = 0.0;
    private double targetValue = 0.0;
    public NudgeController( double currentValue, double targetValue, double gain){
        this.targetValue = targetValue;
        this.currentValue = currentValue;
        this.gain = gain;
    }
    
    protected double computeError(){
        return targetValue - currentValue;
    }
    public double computeOutput(double currentValue){
         this.currentValue = currentValue;
        return gain*computeError();
    }
    
    public boolean isFinished(){
        return Math.abs( computeError()) < TOLERANCE;
    }
    
}
