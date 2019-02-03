package frc.robot;

import frc.robot.oi.OperatorInterface;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Scheduler;


import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.wpilibj.Compressor;
import frc.robot.navigation.NavigationManager;
import frc.robot.navigation.RobotPose;
import frc.robot.oi.OperatorDriveCommand;
import frc.robot.subsystems.DriveCommand;
import frc.robot.subsystems.DriveController;
import frc.robot.subsystems.SubsystemManager;
/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the TimedRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {
   private Compressor compressor;
   
   private final NavigationManager navigation = new NavigationManager();
   private final SubsystemManager subsystems = new SubsystemManager(navigation);
   private final OperatorInterface operatorInterface = new OperatorInterface(subsystems);
   private final DriveController driveController = new DriveController();
  

   @Override
   public void robotInit() {
    compressor = new Compressor(10);
    compressor.start();

    //everything is initialized.
    subsystems.initializeAllSubsystems();
    
    CameraServer.getInstance().startAutomaticCapture();
    operatorInterface.startUp();

  }

   @Override
     public void teleopPeriodic(){
         
          RobotPose robotPose = navigation.getEstimatedPose();
          
          //an operator drive command is what the operator WANTS to do
          OperatorDriveCommand operatorCommand = operatorInterface.getDriveCommand();

          //what _Should_ we do?
          DriveCommand driveCommand = driveController.getDriveCommand(operatorCommand, robotPose);
          
          //do it!
          subsystems.getDrive().drive(driveCommand);
          
          Scheduler.getInstance().run();

     }
  
}
