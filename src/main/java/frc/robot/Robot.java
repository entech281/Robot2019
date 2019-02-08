/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.commands.ExtendCommand;
import frc.robot.commands.GrabberIn;
import frc.robot.commands.GrabberOut;
import frc.robot.commands.RetractCommand;
import frc.robot.commands.ThumbsDown;
import frc.robot.commands.ThumbsStop;
import frc.robot.commands.ThumbsUp;
import frc.robot.commands.ToggleFieldAbsoluteCommand;
import frc.robot.navigation.NavigationManager;
import frc.robot.subsystems.DriveSubsystem;
import frc.robot.subsystems.GrabberSubsystem;
import frc.robot.subsystems.ShooterSubsystem;
import frc.robot.subsystems.ThumbsSubsystem;
import frc.robot.subsystems.VisionSubsystem;

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
  private final NavigationManager navigation = new NavigationManager();

  private Compressor compressor;
  DriveSubsystem robotDrive = new DriveSubsystem();
  ShooterSubsystem shooter = new ShooterSubsystem();
  ThumbsSubsystem thumbs = new ThumbsSubsystem();
  GrabberSubsystem grabber = new GrabberSubsystem();
  VisionSubsystem vision = new VisionSubsystem(navigation);
  

   boolean inFieldAbsolute = false;

   private AHRS navX = new AHRS(SPI.Port.kMXP);

  //Define joystick being used at USB port 1 on the Driver Station
   Joystick m_driveStick = new Joystick(0);
   JoystickButton turnButton = new JoystickButton(m_driveStick, 1);

   public void toggleFieldAbsolute() {
     inFieldAbsolute = !inFieldAbsolute;
   }

   @Override
   public void robotInit() {
    compressor = new Compressor(10);
    compressor.start();
    shooter.initialize();
    grabber.initialize();
    vision.initialize();
    
    
    CameraServer.getInstance().startAutomaticCapture();

    // Shooter Subsystem
    JoystickButton shootButton = new JoystickButton(m_driveStick, 11);
    JoystickButton retractButton = new JoystickButton(m_driveStick, 12);

    // Grabber Subsystem
    JoystickButton grabInButton = new JoystickButton(m_driveStick, 8);
    JoystickButton grabOutButton = new JoystickButton(m_driveStick, 10);

    // Thumbs Subsystem
    JoystickButton thumbsUpButton = new JoystickButton(m_driveStick, 7);
    JoystickButton thumbsDownButton = new JoystickButton(m_driveStick, 9);

    // Shooter Subsystem
    shootButton.whenPressed(new ExtendCommand(shooter));
    retractButton.whenPressed(new RetractCommand(shooter));

    // Grabber Subsystem
    grabInButton.whenPressed(new GrabberIn(grabber));
    grabOutButton.whenPressed(new GrabberOut(grabber));
    
    // Thumbs Subsystem
    thumbsUpButton.whileHeld(new ThumbsUp(thumbs));
    thumbsUpButton.whenReleased(new ThumbsStop(thumbs));
    thumbsDownButton.whileHeld(new ThumbsDown(thumbs));
    thumbsDownButton.whenReleased(new ThumbsStop(thumbs));

    // Field Absolute
    JoystickButton toggleFieldAbsoluteButton = new JoystickButton(m_driveStick, 6);
    
    // Field Absolute
    toggleFieldAbsoluteButton.whenPressed(new ToggleFieldAbsoluteCommand(this));
  }

     public void teleopPeriodic(){
          SmartDashboard.putNumber("Joystick X", m_driveStick.getX());
          SmartDashboard.putNumber("Joystick Y", m_driveStick.getY());
          SmartDashboard.putNumber("Joystick Z", m_driveStick.getZ());
          SmartDashboard.putNumber("Gyro Angle", navX.getAngle());
          SmartDashboard.putNumber("Distance_to_target:", navigation.getEstimatedRobotPose().getDistanceToTarget());

          double z = 0.0;
          if (turnButton.get()) {
            z = m_driveStick.getZ();
          }

          double angle = 0.0;
          if (inFieldAbsolute) {
            angle = navX.getAngle();
          }

          robotDrive.drive(m_driveStick.getX(), -m_driveStick.getY(), z, angle);
          Scheduler.getInstance().run();
          SmartDashboard.putNumber("Get Z", m_driveStick.getZ());

          SmartDashboard.putNumber("Thumb Speed", thumbs.getDesiredSpeed());

          SmartDashboard.putData(thumbs);
     }
  
}
