/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.drive.MecanumDrive;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.drive.AlignLateralFilter;
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
  private WPI_TalonSRX frontLeftTalon  = new WPI_TalonSRX(RobotMap.CAN.FRONT_LEFT_MOTOR);
  private WPI_TalonSRX rearLeftTalon   = new WPI_TalonSRX(RobotMap.CAN.REAR_LEFT_MOTOR);
  private WPI_TalonSRX frontRightTalon = new WPI_TalonSRX(RobotMap.CAN.FRONT_RIGHT_MOTOR);	
  private WPI_TalonSRX rearRightTalon  = new WPI_TalonSRX(RobotMap.CAN.REAR_RIGHT_MOTOR);
  private MecanumDrive robotDrive = new MecanumDrive(frontLeftTalon,rearLeftTalon,frontRightTalon,rearRightTalon);
  
  private TwistFilter twistFilter = new TwistFilter();
  private JoystickJitterFilter joystickJitterFilter = new JoystickJitterFilter();
  private RobotRelativeDriveFilter robotRelativeDriveFilter = new RobotRelativeDriveFilter();

  private HoldYawFilter holdYawFilter = null;
  private AlignLateralFilter alignLateralFilter = null;

  private NudgeRightFilter nudgeRightFilter = new NudgeRightFilter();
  private NudgeLeftFilter nudgeLeftFilter = new NudgeLeftFilter();

  public DriveSubsystem(Robot robot) {
    this.robot = robot;
  }

  @Override
  public void initialize() {
    frontLeftTalon.setInverted(false);
    rearLeftTalon.setInverted(false);
    frontRightTalon.setInverted(false);
    rearRightTalon.setInverted(false);

    holdYawFilter = new HoldYawFilter(this.robot);
    holdYawFilter.disable();

    alignLateralFilter = new AlignLateralFilter(this.robot);
    alignLateralFilter.disable();

    // Use drive subsystem to filter Joytsick not our own filter
    robotDrive.setDeadband(0.1);
    joystickJitterFilter.disable();
    robotRelativeDriveFilter.disable();
  }

  @Override
  public void periodic() {
      SmartDashboard.putBoolean("Robot Relative Drive:", robotRelativeDriveFilter.isEnabled());   
  }

  public void drive(DriveInput di) {
    SmartDashboard.putNumber("DriveInput FieldAngle", di.getFieldAngle());
    robotDrive.driveCartesian(di.getX(), di.getY(), di.getZ(), -di.getFieldAngle());
  }

  public DriveInput applyActiveFilters(DriveInput di) {
    // Add filters in here, be mindful of order!
    di = twistFilter.filter(di);
    di = joystickJitterFilter.filter(di);
    di = robotRelativeDriveFilter.filter(di);

    if (nudgeLeftFilter.isEnabled() || nudgeRightFilter.isEnabled()) {
      di = nudgeRightFilter.filter(di);
      di = nudgeLeftFilter.filter(di);
    } else {
      di = holdYawFilter.filter(di);
      di = alignLateralFilter.filter(di);
    }
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
    // When enabled, the twist filter turns off the twist
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

  public void setHoldYawAngle(double angle) {
    holdYawFilter.setRobotYaw(angle);
  }

  public void holdYaw(boolean enable) {
    if (enable) {
      holdYawFilter.enable();
    } else {
      holdYawFilter.disable();
    }
  }

  public void alignWithTarget(boolean enable) {
    if (enable) {
      alignLateralFilter.enable();
    } else {
      alignLaterFilter.disable();
    }
  }
}
