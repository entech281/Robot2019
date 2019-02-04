package frc.robot.subsystems;

import frc.robot.Initable;
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
public class SubsystemManager implements Initable{

    
    @Override
    public void initialize(){
        //leaving this as hard-coded allows flexibility for init order,
        //if that matters
        compressor.initialize();
        shooter.initialize();
        drive.initialize();
        thumbs.initialize();
        navx.initialize();
        grabber.initialize();
        camera.initialize();
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
  

    public void setShooter(ShooterSubsystem shooter) {
        this.shooter = shooter;
    }

    public void setGrabber(GrabberSubsystem grabber) {
        this.grabber = grabber;
    }

    public void setDrive(DriveSubsystem drive) {
        this.drive = drive;
    }

    public void setThumbs(ThumbsSubsystem thumbs) {
        this.thumbs = thumbs;
    }

    public void setNavx(NavXSubsystem navx) {
        this.navx = navx;
    }
   

    public CameraSubsystem getCamera() {
        return camera;
    }

    public void setCamera(CameraSubsystem camera) {
        this.camera = camera;
    }
    private ShooterSubsystem shooter = null;
    private GrabberSubsystem grabber = null;
    private DriveSubsystem drive = null;
    private ThumbsSubsystem thumbs = null;
    private NavXSubsystem navx = null;
    private CameraSubsystem camera = null;
    private CompressorSubsystem compressor = null;

    public CompressorSubsystem getCompressor() {
        return compressor;
    }

    public void setCompressor(CompressorSubsystem compressor) {
        this.compressor = compressor;
    }
}
