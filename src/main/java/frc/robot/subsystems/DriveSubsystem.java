/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.drive.MecanumDrive;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.drive.DriveInput;
import frc.robot.drive.HoldYawFilter;
import frc.robot.drive.JoystickJitterFilter;
import frc.robot.drive.NudgeLeftFilter;
import frc.robot.drive.NudgeRightFilter;
import frc.robot.drive.RobotRelativeDriveFilter;
import frc.robot.drive.TwistFilter;
import frc.robot.Robot;
import frc.robot.RobotMap;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

/**
 * Add your docs here.
 */
public class DriveSubsystem extends BaseSubsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.
  private Robot robot;
  private WPI_TalonSRX m_frontLeft  = new WPI_TalonSRX(RobotMap.CAN.FRONT_LEFT_MOTOR);
  private WPI_TalonSRX m_rearLeft   = new WPI_TalonSRX(RobotMap.CAN.REAR_LEFT_MOTOR);
  private WPI_TalonSRX m_frontRight = new WPI_TalonSRX(RobotMap.CAN.FRONT_RIGHT_MOTOR);	
  private WPI_TalonSRX m_rearRight  = new WPI_TalonSRX(RobotMap.CAN.REAR_RIGHT_MOTOR);
  private MecanumDrive m_robotDrive = new MecanumDrive(m_frontLeft,m_rearLeft,m_frontRight,m_rearRight);
  
  private TwistFilter twistFilter = new TwistFilter();
  private JoystickJitterFilter joystickJitterFilter = new JoystickJitterFilter();
  private RobotRelativeDriveFilter robotRelativeDriveFilter = new RobotRelativeDriveFilter();

  private HoldYawFilter holdYawFilter = null;

  private NudgeRightFilter nudgeRightFilter = new NudgeRightFilter();
  private NudgeLeftFilter nudgeLeftFilter = new NudgeLeftFilter();

  public DriveSubsystem(Robot robot) {
    this.robot = robot;
    holdYawFilter = new HoldYawFilter(this.robot);
  }

  @Override
  public void initialize() {
    m_frontLeft.setInverted(false);
    m_rearLeft.setInverted(false);
    m_frontRight.setInverted(false);
    m_rearRight.setInverted(false);
    robotRelativeDriveFilter.disable();
    joystickJitterFilter.enable();
  }

  @Override
  public void periodic() {
      SmartDashboard.putBoolean("Robot Relative Drive:", robotRelativeDriveFilter.isEnabled());   
  }

  public void drive(DriveInput di) {
    m_robotDrive.driveCartesian(di.getX(), di.getY(), di.getZ(), di.getFieldAngle());
  }

  public DriveInput applyActiveFilters(DriveInput di) {
    // Add filters in here, be mindful of order!
    di = twistFilter.filter(di);
    di = joystickJitterFilter.filter(di);
    di = robotRelativeDriveFilter.filter(di);

    // Override filters go last
    di = nudgeRightFilter.filter(di);
    di = nudgeLeftFilter.filter(di);
    di = holdYawFilter.filter(di);

    return di;
  }

  public void toggleFieldAbsolute() {
    if (robotRelativeDriveFilter.isEnabled()) {
      robotRelativeDriveFilter.disable();
    } else {
      robotRelativeDriveFilter.enable();
    }
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

  public void holdYawAngle(double angle) {
    holdYawFilter.setRobotYaw(angle);
  }

  public void holdYawOn() {
    holdYawFilter.enable();
  }

  public void holdYawOff() {
    holdYawFilter.disable();
  }
}
