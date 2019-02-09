/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.subsystems.BaseSubsystem;
import frc.robot.navigation.NavigationManager;
import frc.robot.OperatorInterface;
import frc.robot.subsystems.DriveSubsystem;
import frc.robot.subsystems.GrabberSubsystem;
import frc.robot.subsystems.ShooterSubsystem;
import frc.robot.subsystems.ThumbsSubsystem;
import frc.robot.subsystems.VisionSubsystem;
import frc.robot.drive.DriveInput;
import frc.robot.RobotMap;

import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.wpilibj.Compressor;
/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the TimedRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {
  private Compressor compressor;

  private DriveSubsystem robotDrive;
  private ShooterSubsystem shooter;
  private ThumbsSubsystem thumbs;
  private GrabberSubsystem grabber;

  private boolean inFieldAbsolute = false;

  private static AHRS navX = new AHRS(SPI.Port.kMXP);

  //Define joystick being used at USB port 1 on the Driver Station
  private OperatorInterface m_oi;

  public void toggleFieldAbsolute() {
    inFieldAbsolute = !inFieldAbsolute;
  }

  public DriveSubsystem getDriveSubsystem() {
    return robotDrive;
  }

  public GrabberSubsystem getGrabberSubsystem() {
    return grabber;
  }

  public ShooterSubsystem getShooterSubsystem() {
    return shooter;
  }

  public ThumbsSubsystem getThumbsSubsystem() {
    return thumbs;
  }

  public double getRobotAngle() {
    return navX.getAngle();
  }

  @Override
  public void robotInit() {
    compressor = new Compressor(RobotMap.CAN.PCM_ID);
    compressor.start();

    robotDrive = new DriveSubsystem();
    shooter = new ShooterSubsystem();
    thumbs = new ThumbsSubsystem();
    grabber = new GrabberSubsystem();

    BaseSubsystem.initializeList();

    m_oi = new OperatorInterface(this);

    CameraServer.getInstance().startAutomaticCapture();
  }

  public void teleopPeriodic(){

    DriveInput di = m_oi.getDriveInput();
    robotDrive.drive(di);
    SmartDashboard.putNumber("Joystick X", di.getX());
    SmartDashboard.putNumber("Joystick Y", di.getY());
    SmartDashboard.putNumber("Joystick Z", di.getZ());
    SmartDashboard.putNumber("Gyro Angle", di.getFieldAngle());

    Scheduler.getInstance().run();

    SmartDashboard.putNumber("Thumb Speed", thumbs.getDesiredSpeed());
  }
}
