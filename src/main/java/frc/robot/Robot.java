/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.command.Scheduler;
import frc.robot.subsystems.DriveSubsystem;
import frc.robot.subsystems.FlipSubsystem;
import frc.robot.subsystems.HatchSubsystem;
import frc.robot.subsystems.NavXSubsystem;
import frc.robot.subsystems.ArmsSubsystem;
import frc.robot.subsystems.SensorSubsystem;
import frc.robot.subsystems.VisionSubsystem;
import frc.robot.subsystems.BaseSubsystem;
import frc.robot.drive.DriveInput;
import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.TimedRobot;


/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the TimedRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {


  private Compressor compressor;

  private NavXSubsystem navX;
  private DriveSubsystem robotDrive;
  private ArmsSubsystem arms;
  private FlipSubsystem flip;
  private HatchSubsystem hatch;

  private VisionSubsystem vision;
  private SensorSubsystem sensors;

  private OperatorInterface oi;
  //private RobotPreferences prefs = new RobotPreferences();
  public DriveSubsystem getDriveSubsystem() {
    return robotDrive;
  }

  public HatchSubsystem getHatchSubsystem() {
    return hatch;
  }

  public SensorSubsystem getSensorSubsystem(){
    return sensors;
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

  public VisionSubsystem getVisionSubsystem(){
    return vision;
  }

//  public RobotPreferences getPreferences(){
//      return prefs;
//  }
  
  @Override
  public void robotInit() {
    //prefs = RobotPreferences.loadPreferences();
    
    //would be really nice to put this into the mian loop
    //Logging.setEnableDebug(prefs.isDebug());
    
    compressor = new Compressor(RobotMap.CAN.PCM_ID);
    compressor.start();

    // These must be created in this order since the different sensors are used by the Drive
    sensors = new SensorSubsystem();
    navX = new NavXSubsystem();
    vision = new VisionSubsystem();
    robotDrive = new DriveSubsystem(this);

    arms = new ArmsSubsystem();
    flip = new FlipSubsystem();
    hatch = new HatchSubsystem();
    
    BaseSubsystem.initializeList();

    oi = new OperatorInterface(this);

    UsbCamera camera = CameraServer.getInstance().startAutomaticCapture();
    camera.setResolution(320, 240);
    camera.setFPS(30);
  }

  @Override
  public void autonomousInit() {
    robotDrive.setFieldAbsolute(false);
    navX.zeroYaw();
  }

  @Override
  public void autonomousPeriodic() {
    teleopPeriodic();
  }

  @Override
  public void teleopInit() {
    //Logging.setEnableDebug(prefs.isDebug());
    robotDrive.setFieldAbsolute(false);
  }

    @Override
    public void disabledInit() {
        
    }

  @Override
  public void teleopPeriodic(){
    BaseSubsystem.periodicStopWatch.start("MAIN LOOP");

    robotDrive.drive(oi.getDriveInput());
    Scheduler.getInstance().run();

    if(RobotMap.IS_LOGGING_ENABLED){
      System.out.println(BaseSubsystem.periodicStopWatch.toString());
    }
    BaseSubsystem.periodicStopWatch.end("MAIN LOOP");
  }

  @Override
  public void disabledPeriodic() {
    robotDrive.drive(new DriveInput(0.0,0.0,0.0));
    Scheduler.getInstance().run();
  }
}
