package frc.robot.subsystems;

import frc.robot.core.RobotPose;

/**
 * Base Drive Filter
 * @author dcowden
 */
public abstract class DriveFilter {
    private boolean enabled;
    
    public void setEnabled(boolean enabled){
        this.enabled = enabled;
    }
    public boolean getEnabled(){
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