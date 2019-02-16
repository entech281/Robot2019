package frc.robot.subsystems;
import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.drive.DriveInput;
import frc.robot.drive.GetDriveInput;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;


public class SensorSubsystem extends BaseSubsystem implements GetDriveInput {
    private double current_offset;

    final private static double INSIDE_SENSOR_WIDTH = 1.6;
    final private static double DISTANCE_BETWEEN_OUT_AND_IN_SENSORS = 1.8;
    private I2C i2c;
    NetworkTableInstance ntist;
    NetworkTableEntry lateral;
    NetworkTableEntry hasLine;

    public SensorSubsystem() {}

    @Override                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                     
    public void initialize(){
        i2c = new I2C( I2C.Port.kOnboard, 1);
        ntist = NetworkTableInstance.getDefault();
        lateral = ntist.getEntry("team281.Sensors.lateral");
        hasLine = ntist.getEntry("team281.Sensors.hasLine");
    }

    @Override
    public void periodic(){
        
        boolean gotValidResult = false; 
        byte[] i2cBuffer = new byte[1];

        gotValidResult = i2c.readOnly(i2cBuffer, 1);
        
        switch(i2cBuffer[0]){
            case 1:  current_offset = - 2 * INSIDE_SENSOR_WIDTH - DISTANCE_BETWEEN_OUT_AND_IN_SENSORS ; break;
            case 2:  current_offset = - 2 * INSIDE_SENSOR_WIDTH ; break;
            case 4:  current_offset = -INSIDE_SENSOR_WIDTH ; break;
            case 8:  current_offset = 0.0 ; break;
            case 16: current_offset = INSIDE_SENSOR_WIDTH ; break;
            case 32: current_offset = 2 * INSIDE_SENSOR_WIDTH; break;
            case 64: current_offset = 2 * INSIDE_SENSOR_WIDTH + DISTANCE_BETWEEN_OUT_AND_IN_SENSORS ; break;

            //two sensors on
            case 3: current_offset =  - (4 * INSIDE_SENSOR_WIDTH + DISTANCE_BETWEEN_OUT_AND_IN_SENSORS) / 2 ; break;
            case 6:  current_offset = - 3 * INSIDE_SENSOR_WIDTH / 2 ; break;
            case 12: current_offset = -INSIDE_SENSOR_WIDTH / 2 ; break;
            case 24: current_offset = INSIDE_SENSOR_WIDTH / 2 ; break;
            case 48: current_offset = 3 * INSIDE_SENSOR_WIDTH / 2 ; break;
            case 96: current_offset = (4 * INSIDE_SENSOR_WIDTH + DISTANCE_BETWEEN_OUT_AND_IN_SENSORS) / 2 ; break;
            default: gotValidResult = false;
        }
        
        SmartDashboard.putNumber("Arduino Value", current_offset);
        SmartDashboard.putNumber("Byte Value", i2cBuffer[0]);
        if(gotValidResult)
            lateral.forceSetDouble(current_offset);
        hasLine.forceSetBoolean(gotValidResult);    
        
    }

	@Override
	public DriveInput getDriveInput() {
		return new DriveInput(0, current_offset, 0);
	}

}