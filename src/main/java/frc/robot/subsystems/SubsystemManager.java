package frc.robot.subsystems;

import frc.robot.navigation.NavigationManager;

/**
 * Keeps track of all the subsystems,
 * so we can initialize them.
 * Allows keeping robot lean and easy to follow
 * Allows giving operator interface access to subsystems
 * without mixing it all into the robot
 * 
 * different year to year
 * @author dcowden
 */
public class SubsystemManager {

    public SubsystemManager(NavigationManager navigation){
        this.navigation = navigation;
        shooter = new ShooterSubsystem(navigation);
        drive = new DriveSubsystem(navigation);
        thumbs = new ThumbsSubsystem(navigation);
        grabber = new GrabberSubsystem(navigation);
        navx = new NavXSubsystem(navigation);
    }
    
    public void initializeAllSubsystems(){
        //leaving this as hard-coded allows flexibility for init order,
        //if that matters
        shooter.initialize();
        drive.initialize();
        thumbs.initialize();
        navx.initialize();
        grabber.initialize();
    }
    
    public ShooterSubsystem getShooter() {
        return shooter;
    }

    public GrabberSubsystem getGrabber() {
        return grabber;
    }

    public DriveSubsystem getDrive() {
        return drive;
    }

    public ThumbsSubsystem getThumbs() {
        return thumbs;
    }

    public NavXSubsystem getNavx() {
        return navx;
    }
   
    public NavigationManager getNavigation(){
        return navigation;
    }

    
    private NavigationManager navigation = null;
    private ShooterSubsystem shooter = null;
    private GrabberSubsystem grabber = null;
    private DriveSubsystem drive = null;
    private ThumbsSubsystem thumbs = null;
    private NavXSubsystem navx = null;
    
}
