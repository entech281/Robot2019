package frc.robot.subsystems;
import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class SensorSubsystem extends BaseSubsystem implements  {

    final private double INSIDE_SENSOR_WIDTH = 1.6;
    final private double DISTANCE_BETWEEN_OUT_AND_IN_SENSORS = 1.8;
    private I2C i2c;

    public SensorSubsystem() {}

    @Override                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                     
    public void initialize(){
        i2c = new I2C( I2C.Port.kOnboard, 1);
    }

    @Override
    public void periodic(){

        byte[] b = new byte[1];
        b[0] = 0;
        i2c.readOnly(b, 1);
        double offset = 17.3;
            
        
        
        switch(b[0]){
            case 1:  offset = - 2 * INSIDE_SENSOR_WIDTH - DISTANCE_BETWEEN_OUT_AND_IN_SENSORS ; break;
            case 2:  offset = - 2 * INSIDE_SENSOR_WIDTH ; break;
            case 4:  offset = -INSIDE_SENSOR_WIDTH ; break;
            case 8:  offset = 0.0 ; break;
            case 16: offset = INSIDE_SENSOR_WIDTH ; break;
            case 32: offset = 2 * INSIDE_SENSOR_WIDTH; break;
            case 64: offset = 2 * INSIDE_SENSOR_WIDTH + DISTANCE_BETWEEN_OUT_AND_IN_SENSORS ; break;

            //two sensors on
            case 3: offset =  - (4 * INSIDE_SENSOR_WIDTH + DISTANCE_BETWEEN_OUT_AND_IN_SENSORS) / 2 ; break;
            case 6:  offset = - 3 * INSIDE_SENSOR_WIDTH / 2 ; break;
            case 12: offset = -INSIDE_SENSOR_WIDTH / 2 ; break;
            case 24: offset = INSIDE_SENSOR_WIDTH / 2 ; break;
            case 48: offset = 3 * INSIDE_SENSOR_WIDTH / 2 ; break;
            case 96: offset = (4 * INSIDE_SENSOR_WIDTH + DISTANCE_BETWEEN_OUT_AND_IN_SENSORS) / 2 ; break;
        }
        
        SmartDashboard.putNumber("Arduino Value", offset);
        SmartDashboard.putNumber("Byte Value", b[0]);
    }

}