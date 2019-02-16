/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.drive.MecanumDrive;
import frc.robot.drive.DriveInput;
import frc.robot.drive.JoystickJitterFilter;
import frc.robot.drive.NudgeLeftFilter;
import frc.robot.drive.NudgeRightFilter;
import frc.robot.drive.TwistFilter;
import frc.robot.RobotMap;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

/**
 * Add your docs here.
 */
public class DriveSubsystem extends BaseSubsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.
  private WPI_TalonSRX m_frontLeft  = new WPI_TalonSRX(RobotMap.CAN.FRONT_LEFT_MOTOR);
  private WPI_TalonSRX m_rearLeft   = new WPI_TalonSRX(RobotMap.CAN.REAR_LEFT_MOTOR);
  private WPI_TalonSRX m_frontRight = new WPI_TalonSRX(RobotMap.CAN.FRONT_RIGHT_MOTOR);	
  private WPI_TalonSRX m_rearRight  = new WPI_TalonSRX(RobotMap.CAN.REAR_RIGHT_MOTOR);
  private MecanumDrive m_robotDrive = new MecanumDrive(m_frontLeft,m_rearLeft,m_frontRight,m_rearRight);
  
  private TwistFilter twistFilter = new TwistFilter();
  private JoystickJitterFilter joystickJitterFilter = new JoystickJitterFilter();

  private NudgeRightFilter nudgeRightFilter = new NudgeRightFilter();
  private NudgeLeftFilter nudgeLeftFilter = new NudgeLeftFilter();

  @Override
  public void initialize() {
    m_frontLeft.setInverted(true);
    m_rearLeft.setInverted(true);
    m_frontRight.setInverted(true);
    m_rearRight.setInverted(true);
  }

  public void drive(DriveInput di) {
    m_robotDrive.driveCartesian(di.getX(), di.getY(), di.getZ(), di.getFieldAngle());
  }

  public DriveInput applyActiveFilters(DriveInput di) {
    // Add filters in here, be mindful of order!
    di = twistFilter.filter(di);
    di = joystickJitterFilter.filter(di);

    // Override filters go last
    di = nudgeRightFilter.filter(di);
    di = nudgeLeftFilter.filter(di);

    return di;
  }

  public void twistOn(boolean enabled) {
    // THIS IS NOT BACKWARDS
    // The twist filter turns off the twist
    if ( enabled ) {
      twistFilter.disable();
    } else {
      twistFilter.enable();
    }
  }

  public void nudgeRight() {
    nudgeRightFilter.enable();
    nudgeLeftFilter.disable();
  }

  public void nudgeLeft() {
    nudgeLeftFilter.enable();
    nudgeRightFilter.disable();
  }
}
