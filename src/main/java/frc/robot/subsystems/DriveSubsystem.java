package frc.robot.subsystems;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.MecanumDrive;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
public class DriveSubsystem extends Subsystem{
    private WPI_TalonSRX m_frontLeft  = new WPI_TalonSRX(4);
    private WPI_TalonSRX m_rearLeft   = new WPI_TalonSRX(7);
    private WPI_TalonSRX m_frontRight = new WPI_TalonSRX(6);	
    private WPI_TalonSRX m_rearRight  = new WPI_TalonSRX(8);
    private MecanumDrive m_robotDrive = new MecanumDrive(m_frontLeft,m_rearLeft,m_frontRight,m_rearRight);
    @Override
    protected void initDefaultCommand() {
        m_robotDrive.driveCartesian(0.0, 0.0, 0.0);
    }
    public void drive(double x, double y, double theta){
        m_robotDrive.driveCartesian(x, y, theta);
    }
}