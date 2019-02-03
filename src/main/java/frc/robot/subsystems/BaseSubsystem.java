package frc.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.navigation.NavigationManager;

/**
 *
 * @author dcowden
 */
public abstract class BaseSubsystem extends Subsystem {

    private NavigationManager navigationManager = null;

    
    public BaseSubsystem(NavigationManager navigationManager){
        this.navigationManager = navigationManager;
    }

    public NavigationManager getNavigationManager() {
        return navigationManager;
    }
    
    @Override
    protected void initDefaultCommand() {
        
    }
    
    public abstract void initialize();
    

}
