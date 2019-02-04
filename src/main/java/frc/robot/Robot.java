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
import frc.robot.commands.ToggleFieldAbsoluteCommand;;
import frc.robot.commands.NudgeLeftCommand;;
import frc.robot.commands.NudgeRightCommand;;
import frc.robot.subsystems.DriveSubsystem;
import frc.robot.subsystems.GrabberSubsystem;
import frc.robot.subsystems.ShooterSubsystem;
import frc.robot.subsystems.ThumbsSubsystem;

import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.wpilibj.Compressor;
import frc.robot.subsystems.DeadbandDriveFilter;
import frc.robot.subsystems.DisableFieldAbsDriveFilter;
import frc.robot.subsystems.HoldYawDriveFilter;
import frc.robot.subsystems.NudgeLeftDriveFilter;
import frc.robot.subsystems.NudgeRightDriveFilter;
import frc.robot.subsystems.DriveCommand;
import frc.robot.subsystems.TwistLockDriveFilter;
/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the TimedRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {
   private Compressor compressor;
   DriveSubsystem robotDrive = new DriveSubsystem();
   ShooterSubsystem shooter = new ShooterSubsystem();
   ThumbsSubsystem thumbs = new ThumbsSubsystem();
   GrabberSubsystem grabber = new GrabberSubsystem();
   TwistLockDriveFilter twistLockFilter;
   DeadbandDriveFilter deadbandDriveFilter;
   DisableFieldAbsDriveFilter disableFieldAbsDriveFilter;
   HoldYawDriveFilter holdYawDriveFilter;
   NudgeLeftDriveFilter nudgeLeftDriveFilter;
   NudgeRightDriveFilter nudgeRightDriveFilter;
   AHRS m_navx;
   
  //Define joystick being used at USB port 1 on the Driver Station
   Joystick m_driveStick = new Joystick(0);
   JoystickButton turnButton = new JoystickButton(m_driveStick, 1);

   @Override
   public void robotInit() {
    compressor = new Compressor(10);
    compressor.start();
    shooter.initialize();
    grabber.initialize();

    // Create our drive filters - filters are enabled by default
    m_navx = new AHRS(SPI.Port.kMXP);
    twistLockFilter = new TwistLockDriveFilter();
    deadbandDriveFilter = new DeadbandDriveFilter();
    holdYawDriveFilter = new HoldYawDriveFilter(m_navx);
    holdYawDriveFilter.disable();
    nudgeLeftDriveFilter = new NudgeLeftDriveFilter();
    nudgeLeftDriveFilter.disable();
    nudgeRightDriveFilter = new NudgeRightDriveFilter();
    nudgeRightDriveFilter.disable();
    disableFieldAbsDriveFilter = new DisableFieldAbsDriveFilter();
    
    CameraServer.getInstance().startAutomaticCapture();

    // Shooter Subsystem
    JoystickButton shootButton = new JoystickButton(m_driveStick, 11);
    JoystickButton retractButton = new JoystickButton(m_driveStick, 12);

    // Grabber Subsystem
    JoystickButton inButton = new JoystickButton(m_driveStick, 8);
    JoystickButton outButton = new JoystickButton(m_driveStick, 10);

    // Thumbs Subsystem
    JoystickButton thumbUp = new JoystickButton(m_driveStick, 7);
    JoystickButton thumbDown = new JoystickButton(m_driveStick, 9);

    // Shooter Subsystem
    shootButton.whenPressed(new ExtendCommand(shooter));
    retractButton.whenPressed(new RetractCommand(shooter));

    // Drive Subsystem
    JoystickButton button = new JoystickButton(m_driveStick, 8);
    button.whenPressed(new ToggleFieldAbsoluteCommand(disableFieldAbsDriveFilter));
    button = new JoystickButton(m_driveStick, 3);
    button.whenPressed(new NudgeLeftCommand(nudgeLeftDriveFilter));
    button = new JoystickButton(m_driveStick, 4);
    button.whenPressed(new NudgeRightCommand(nudgeRightDriveFilter));


    // Grabber Subsystem
    inButton.whenPressed(new GrabberIn(grabber));
    outButton.whenPressed(new GrabberOut(grabber));
    
    // Thumbs Subsystem
    thumbUp.whileHeld(new ThumbsUp(thumbs));
    thumbUp.whenReleased(new ThumbsStop(thumbs));
    thumbDown.whileHeld(new ThumbsDown(thumbs));
    thumbDown.whenReleased(new ThumbsStop(thumbs));
    }

   //i think this belongs in another class, like the OI, but
   //i dont want to get into that now
    protected DriveCommand readJoystickDriveCommand(){
        return new DriveCommand(m_driveStick.getX(),-m_driveStick.getY(),m_driveStick.getZ(),m_navx.getAngle());
    }
    
    // Sandstorm reports as autonomous
    @Override
    public void autonomousPeriodic() {
      teleopPeriodic();
    }

    @Override
     public void teleopPeriodic(){
         ///this stuff doesnt go here
          SmartDashboard.putNumber("Joystick X", m_driveStick.getX());
          SmartDashboard.putNumber("Joystick Y", m_driveStick.getY());
          SmartDashboard.putNumber("Joystick Z", m_driveStick.getZ());

          //lock out z twist unless button is pressed
          //i can imagine having a filter chain instead of hard-coding it like
          //this.
          if (turnButton.get()) {
            twistLockFilter.disable();
          } else {
            twistLockFilter.enable();
          }

        // note here that robotDrive.drive(readJoystickDriveCommand()) would work too!
          DriveCommand dc = readJoystickDriveCommand();
          dc = deadbandDriveFilter.filter(dc);
          dc = twistLockFilter.filter(dc);
          dc = disableFieldAbsDriveFilter.filter(dc);
          dc = nudgeLeftDriveFilter.filter(dc);
          dc = nudgeRightDriveFilter.filter(dc);
          robotDrive.drive(dc);
          
          Scheduler.getInstance().run();
          
          //neither does this stuff
          SmartDashboard.putNumber("Get Z", m_driveStick.getZ());
          SmartDashboard.putNumber("Thumb Speed", thumbs.getDesiredSpeed());

          SmartDashboard.putData(thumbs);
     }
  
}
