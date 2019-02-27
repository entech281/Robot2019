/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import frc.robot.RobotMap;

/**
 * Add your docs here.
 */
public class PushPlateHatchSubsystem extends BaseSubsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.

  private DoubleSolenoid solenoidVertical;
  private DoubleSolenoid solenoidHorizontal;

  public PushPlateHatchSubsystem() {
    super();
    solenoidVertical = new DoubleSolenoid(RobotMap.CAN.VERT_PCM_ID, RobotMap.PNEUMATICS.PUSH_PLATE_VERTICAL_UP, RobotMap.PNEUMATICS.PUSH_PLATE_VERTICAL_DOWN);  
    solenoidHorizontal = new DoubleSolenoid(RobotMap.CAN.HORI_PCM_ID, RobotMap.PNEUMATICS.PUSH_PLATE_HORIZONTAL_OUT, RobotMap.PNEUMATICS.PUSH_PLATE_HORIZONTAL_IN);  
  }

  @Override
  public void initialize() {
  }

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }
  
  public void grabHold() {
    solenoidHorizontal.set(DoubleSolenoid.Value.kForward);
    solenoidVertical.set(DoubleSolenoid.Value.kForward);
  }

  public void release() {
    solenoidHorizontal.set(DoubleSolenoid.Value.kReverse);
    solenoidVertical.set(DoubleSolenoid.Value.kReverse);
  }
}
