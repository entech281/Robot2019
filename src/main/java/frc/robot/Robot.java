package frc.robot;

import frc.robot.oi.OperatorInterface;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Scheduler;


import frc.robot.commands.DriveCommand;
import frc.robot.commands.NormalDriveController;
import frc.robot.navigation.NavigationManager;
import frc.robot.subsystems.CameraSubsystem;
import frc.robot.subsystems.CompressorSubsystem;
import frc.robot.subsystems.DriveSubsystem;
import frc.robot.subsystems.GrabberSubsystem;
import frc.robot.subsystems.NavXSubsystem;
import frc.robot.subsystems.ShooterSubsystem;
import frc.robot.subsystems.SubsystemManager;
import frc.robot.subsystems.ThumbsSubsystem;
/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the TimedRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.gradle file in the
 * project.
 * 
 * THis class is all about wiring all the parts of the system together.
 * There shouldnt be any logic, just connecting things
 */
public class Robot extends TimedRobot {

   //provides access to the robot pose
   private final NavigationManager navigation = new NavigationManager();
   
   //this just provides a place where we can stash all the subsystems
   //to keep code in this class shorter
   private final SubsystemManager subsystems = new SubsystemManager();
   private final OperatorInterface operatorInterface = new OperatorInterface(subsystems);


   @Override
   public void robotInit() {

    subsystems.setGrabber(new GrabberSubsystem());
    subsystems.setShooter(new ShooterSubsystem());
    subsystems.setNavx(new NavXSubsystem(navigation));
    subsystems.setThumbs(new ThumbsSubsystem() );
    subsystems.setCamera(new CameraSubsystem());
    subsystems.setCompressor(new CompressorSubsystem());
    
    //drive is a little more complex
    DriveSubsystem drive = new DriveSubsystem(navigation);
    drive.setDefaultCommand( 
            new DriveCommand (drive, operatorInterface, new NormalDriveController()
    ));
    subsystems.setDrive(drive);
    
    //everything is initialized.
    subsystems.initialize();    
    operatorInterface.initialize();

  }

   @Override
     public void teleopPeriodic(){          
          Scheduler.getInstance().run();
     }
  
}
