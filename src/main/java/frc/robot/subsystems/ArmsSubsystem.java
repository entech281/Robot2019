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
public class ArmsSubsystem extends BaseSubsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.

  public ArmsSubsystem() {
    super();
  }

  private DoubleSolenoid squeezeSolenoid;
  private DoubleSolenoid deploySolenoid;
  
  @Override
  public void initialize() {
    squeezeSolenoid = new DoubleSolenoid(RobotMap.CAN.PCM_ID, RobotMap.PNEUMATICS.ARMS_SOLENOID1_FORWARD, RobotMap.PNEUMATICS.ARMS_SOLENOID1_REVERSE);
    deploySolenoid = new DoubleSolenoid(RobotMap.CAN.PCM_ID, RobotMap.PNEUMATICS.ARMS_SOLENOID2_FORWARD, RobotMap.PNEUMATICS.ARMS_SOLENOID2_REVERSE);
  }

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }
  
  public void squeeze() {
    squeezeSolenoid.set(DoubleSolenoid.Value.kForward);
  }

  public void release() {
    squeezeSolenoid.set(DoubleSolenoid.Value.kReverse);
  }

  public void deploy() {
    deploySolenoid.set(DoubleSolenoid.Value.kForward);
  }
}
