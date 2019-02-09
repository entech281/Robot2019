/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.drive.MecanumDrive;
import frc.robot.DriveCommand;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

/**
 * Add your docs here.
 */
public class DriveSubsystem extends BaseSubsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.
  private WPI_TalonSRX m_frontLeft  = new WPI_TalonSRX(4);
  private WPI_TalonSRX m_rearLeft   = new WPI_TalonSRX(7);
  private WPI_TalonSRX m_frontRight = new WPI_TalonSRX(6);	
  private WPI_TalonSRX m_rearRight  = new WPI_TalonSRX(8);
  private MecanumDrive m_robotDrive = new MecanumDrive(m_frontLeft,m_rearLeft,m_frontRight,m_rearRight);
    
  @Override
  public void initialize() {

  }

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
    m_robotDrive.driveCartesian(0.0, 0.0, 0.0, 0.0);
  }

  public void drive(DriveCommand dc) {
    m_robotDrive.driveCartesian(dc.getX(), dc.getY(), dc.getZ(), dc.getFieldAngle());
  }
}
