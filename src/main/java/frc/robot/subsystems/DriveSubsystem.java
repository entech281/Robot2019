package frc.robot.subsystems;
import edu.wpi.first.wpilibj.drive.MecanumDrive;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import frc.robot.navigation.NavigationManager;


public class DriveSubsystem extends BaseSubsystem{
    private WPI_TalonSRX m_frontLeft  = new WPI_TalonSRX(4);
    private WPI_TalonSRX m_rearLeft   = new WPI_TalonSRX(7);
    private WPI_TalonSRX m_frontRight = new WPI_TalonSRX(6);	
    private WPI_TalonSRX m_rearRight  = new WPI_TalonSRX(8);
    private MecanumDrive m_robotDrive = new MecanumDrive(m_frontLeft,m_rearLeft,m_frontRight,m_rearRight);
    private DriveInstruction currentCommand = new DriveInstruction(0,0,0,0);
    private NavigationManager navigation;

    public NavigationManager getNavigation() {
        return navigation;
    }
    public DriveSubsystem(NavigationManager navigationManager) {
        this.navigation = navigationManager;
    }

    @Override
    protected void initDefaultCommand() {
        
    }
    public void drive(DriveInstruction command){
        currentCommand = command;
    }

    @Override
    public void initialize() {
        
    }

    @Override
    public void periodic() {
        m_robotDrive.driveCartesian(currentCommand.getX(),
                currentCommand.getY(), 
                currentCommand.getZ(), 
                currentCommand.getAngle());
    }
    
    
}
