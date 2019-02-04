package frc.robot.subsystems;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import frc.robot.navigation.NavigationManager;
public class ShooterSubsystem extends BaseSubsystem{



    private DoubleSolenoid solenoid;


    @Override
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