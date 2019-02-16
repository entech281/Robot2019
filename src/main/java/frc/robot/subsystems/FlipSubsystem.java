/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * Add your docs here.
 */
public class FlipSubsystem extends BaseSubsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.

  private double desiredSpeed = 0;

  private WPI_TalonSRX motor  = new WPI_TalonSRX(2);

  @Override
  public void initialize() {
    
  }
  
  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }

  @Override
  public void periodic() {
    SmartDashboard.putNumber("Flip Motor Speed", getDesiredSpeed());
  }

  public void forward() {
    desiredSpeed = 1;
    motor.set(ControlMode.PercentOutput, desiredSpeed);
  }

  public void backward() {
    desiredSpeed = -1;
    motor.set(ControlMode.PercentOutput, desiredSpeed);
  }
  
  public void stop() {
    motor.set(ControlMode.PercentOutput, 0);
  }

  /**
   * @return the desiredSpeed
   */
  public double getDesiredSpeed() {
    return desiredSpeed;
  }

  /**
   * @param desiredSpeed the desiredSpeed to set
   */
  public void setDesiredSpeed(double desiredSpeed) {
    this.desiredSpeed = desiredSpeed;
  }

  /**
   * @return the motor
   */
  public WPI_TalonSRX getMotor() {
    return motor;
  }

  /**
   * @param motor the motor to set
   */
  public void setMotor(WPI_TalonSRX motor) {
    this.motor = motor;
  }
}