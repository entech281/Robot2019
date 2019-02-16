/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DoubleSolenoid;

/**
 * Add your docs here.
 */
public class ArmsSubsystem extends BaseSubsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.

  public ArmsSubsystem() {
    super();
  }

  private DoubleSolenoid solenoid;
  private DoubleSolenoid solenoid2;
  
  @Override
  public void initialize() {
    solenoid = new DoubleSolenoid(10, 0, 1);
    solenoid2 = new DoubleSolenoid (10, 4, 5);
  }

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }
  
  public void extend() {
    solenoid.set(DoubleSolenoid.Value.kForward);
  }

  public void retract() {
    solenoid.set(DoubleSolenoid.Value.kReverse);
  }

  public void deploy() {
    solenoid2.set(DoubleSolenoid.Value.kForward);
  }
}
