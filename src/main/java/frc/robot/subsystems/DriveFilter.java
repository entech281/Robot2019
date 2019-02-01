package frc.robot.subsystems;

/**
 * Base Drive Filter
 * @author dcowden
 */
public abstract class DriveFilter {
    private boolean enabled;
    void setEnabled(boolean enabled){
        this.enabled = enabled;
    }
    boolean getEnabled(){
        return this.enabled;
    }
    DriveCommand filter(DriveCommand input, RobotPose state){
        if ( this.enabled){
            return doFilter(input, state);
        }
        else{
            return input;
        }
    }
    
    public abstract DriveCommand doFilter(DriveCommand input, RobotPose state);
}
