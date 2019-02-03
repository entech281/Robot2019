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

    public DriveSubsystem(NavigationManager navigationManager) {
        super(navigationManager);
    }

    @Override
    protected void initDefaultCommand() {
        m_robotDrive.driveCartesian(0.0, 0.0, 0.0, 0.0);
    }
    public void drive(DriveCommand command){
        m_robotDrive.driveCartesian(command.getX(),command.getY(), command.getZ(), command.getAngle());
    }

    @Override
    public void initialize() {
        
    }
}
