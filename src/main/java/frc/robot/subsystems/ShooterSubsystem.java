package frc.robot.subsystems;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.DoubleSolenoid;
public class ShooterSubsystem extends Subsystem{

    public ShooterSubsystem(){
        super();
    }


    private DoubleSolenoid solenoid;

    public void initialize(){
        solenoid= new DoubleSolenoid(10, 0, 1);
    }
    
  @Override
    protected void initDefaultCommand() {
    }
    public void extend(){
        solenoid.set(DoubleSolenoid.Value.kForward);
    }
    public void retract(){
        solenoid.set(DoubleSolenoid.Value.kReverse);
    }
}