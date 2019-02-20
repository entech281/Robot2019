package frc.robot.subsystems;
import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.drive.DriveInput;
import frc.robot.drive.GetDriveInput;
public class SensorSubsystem extends BaseSubsystem implements GetDriveInput {
    private double current_offset;
    private boolean offset_valid;

    final private static double INSIDE_SENSOR_WIDTH = 1.6;
    final private static double DISTANCE_BETWEEN_OUT_AND_IN_SENSORS = 1.8;
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
        
            
        
        
        switch(b[0]){    
            case 1:
              current_offset = - 2 * INSIDE_SENSOR_WIDTH - DISTANCE_BETWEEN_OUT_AND_IN_SENSORS ;
              offset_valid = true;
              break;
            case 2:  
              current_offset = - 2 * INSIDE_SENSOR_WIDTH ; 
              offset_valid = true;
              break;
            case 4:  current_offset = -INSIDE_SENSOR_WIDTH ;
            offset_valid = true;
            break;
            case 8:  current_offset = 0.0 ; 
            offset_valid = true;
            break;
            case 16: current_offset = INSIDE_SENSOR_WIDTH ; 
            offset_valid = true;
            break;
            case 32: current_offset = 2 * INSIDE_SENSOR_WIDTH; 
            offset_valid = true;
            break;
            case 64: current_offset = 2 * INSIDE_SENSOR_WIDTH + DISTANCE_BETWEEN_OUT_AND_IN_SENSORS ; 
            offset_valid = true;
            break;

            //two sensors on
            case 3: current_offset =  - (4 * INSIDE_SENSOR_WIDTH + DISTANCE_BETWEEN_OUT_AND_IN_SENSORS) / 2 ; 
            offset_valid = true;
            break;
            case 6:  current_offset = - 3 * INSIDE_SENSOR_WIDTH / 2 ; 
            offset_valid = true;
            break;
            case 12: current_offset = -INSIDE_SENSOR_WIDTH / 2 ; 
            offset_valid = true;
            break;
            case 24: current_offset = INSIDE_SENSOR_WIDTH / 2 ; 
            offset_valid = true;
            break;
            case 48: current_offset = 3 * INSIDE_SENSOR_WIDTH / 2 ; 
            offset_valid = true;
            break;
            case 96: current_offset = (4 * INSIDE_SENSOR_WIDTH + DISTANCE_BETWEEN_OUT_AND_IN_SENSORS) / 2 ; 
            offset_valid = true;
            break;
            default: 
            offset_valid = false;
              break;
        }
        
        SmartDashboard.putNumber("Arduino Value", current_offset);
        SmartDashboard.putNumber("Byte Value", b[0]);
    }

	@Override
	public DriveInput getDriveInput() {
        DriveInput di = new DriveInput();
        if (offset_valid) {
            di.setTargetY(current_offset);
        }
		return di;
	}

}