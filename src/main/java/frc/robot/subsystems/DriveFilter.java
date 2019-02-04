package frc.robot.subsystems;

import frc.robot.core.RobotPose;

/**
 * Base Drive Filter
 * @author dcowden
 */
public abstract class DriveFilter {
    private boolean enabled = true;;
    
    public void enable(){
        this.enabled = true;
    }
    public void disable(){
        this.enabled = false;
    }
    public boolean isEnabled(){
        return this.enabled;
    }
    public DriveCommand filter(DriveCommand input, RobotPose state){
        if ( this.enabled){
            return doFilter(input, state);
        }
        else{
            return input;
        }
    }
    abstract DriveCommand doFilter(DriveCommand input, RobotPose state);
}
