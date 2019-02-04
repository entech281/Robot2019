package frc.robot.subsystems;

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
    public DriveCommand filter(DriveCommand input){
        if ( this.enabled){
            return doFilter(input);
        }
        else{
            return input;
        }
    }
    abstract DriveCommand doFilter(DriveCommand input);
}
