/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.subsystems.BaseSubsystem;
import frc.robot.OperatorInterface;
import frc.robot.subsystems.DriveSubsystem;
import frc.robot.subsystems.FlipSubsystem;
import frc.robot.subsystems.HatchSubsystem;
import frc.robot.subsystems.NavXSubsystem;
import frc.robot.subsystems.ArmsSubsystem;
import frc.robot.drive.DriveInput;
import frc.robot.RobotMap;
import edu.wpi.cscore.UsbCamera;
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
  private NavXSubsystem navX;
  private ArmsSubsystem arms;
  private FlipSubsystem flip;
  private HatchSubsystem hatch;

  private boolean inFieldAbsolute = false;

  private OperatorInterface oi;

  public void toggleFieldAbsolute() {
    inFieldAbsolute = !inFieldAbsolute;
  }

  public DriveSubsystem getDriveSubsystem() {
    return robotDrive;
  }

  public HatchSubsystem getHatchSubsystem() {
    return hatch;
  }

  public NavXSubsystem getNavXSubsystem() {
    return navX;
  }

  public ArmsSubsystem getArmsSubsystem() {
    return arms;
  }

  public FlipSubsystem getFlipSubsystem() {
    return flip;
  }

  @Override
  public void robotInit() {
    compressor = new Compressor(RobotMap.CAN.PCM_ID);
    compressor.start();

    robotDrive = new DriveSubsystem();
    arms = new ArmsSubsystem();
    navX = new NavXSubsystem();
    flip = new FlipSubsystem();
    hatch = new HatchSubsystem();

    BaseSubsystem.initializeList();

    this.oi = new OperatorInterface(this);

    UsbCamera camera = CameraServer.getInstance().startAutomaticCapture();
    camera.setResolution(640, 480);
    camera.setFPS(60);
  }

  public void teleopPeriodic(){
    DriveInput di = mergeOIandNavDriveInput(this.oi.getDriveInput(), navX.getDriveInput());
    di = robotDrive.applyActiveFilters(di);
    robotDrive.drive(di);

    SmartDashboard.putNumber("Joystick X", di.getX());
    SmartDashboard.putNumber("Joystick Y", di.getY());
    SmartDashboard.putNumber("Joystick Z", di.getZ());
    SmartDashboard.putNumber("Gyro Angle", di.getFieldAngle());

    Scheduler.getInstance().run();
  }

  private DriveInput mergeOIandNavDriveInput(DriveInput oi_di, DriveInput nav_di) {
    return new DriveInput(oi_di.getX(), oi_di.getY(), oi_di.getZ(), nav_di.getFieldAngle());
  }
}
