package frc.robot.subsystems;
import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.navigation.NavigationManager;

public class SensorSubsystem extends BaseSubsystem {

    private NavigationManager navigation;
    private I2C i2c;

    public SensorSubsystem(NavigationManager navigationManager) {
        this.navigation = navigationManager;
    }

    @Override                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                     
    public void initialize(){
        i2c = new I2C( I2C.Port.kOnboard, 1);
    }

    @Override
    public void periodic(){
        byte[] b = new byte[1];
        b[0]=0;
        if (i2c.readOnly(b, 1)){
            navigation.acceptSensorPoseUpdate( b );
        }
        
        SmartDashboard.putString("Does this Work", "Yes");
        SmartDashboard.putRaw("Arduino Values", new byte[]{3});
        
    }

}