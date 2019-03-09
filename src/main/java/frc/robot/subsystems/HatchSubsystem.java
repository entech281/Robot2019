/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.RobotMap;

/**
 * Add your docs here.
 */
public class HatchSubsystem extends BaseSubsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.

  private DoubleSolenoid solenoid;
  //private DoubleSolenoid solenoid2;
  //private DoubleSolenoid solenoid3;

  private Timer timer = new Timer();
  private boolean isDeploying = false;

  public HatchSubsystem() {
    super();
    solenoid = new DoubleSolenoid(RobotMap.CAN.PCM_ID, RobotMap.PNEUMATICS.HATCH_FORWARD, RobotMap.PNEUMATICS.HATCH_REVERSE); 
    //solenoid2 = new DoubleSolenoid(RobotMap.CAN.PCM_ID_2, RobotMap.PNEUMATICS.HATCH_RELEASE_TOP);
    //solenoid3 = new DoubleSolenoid(RobotMap.CAN.PCM_ID_3, RobotMap.PNEUMATICS.HATCH_RELEASE_BOTTOM);

    //add 2 more like in flip subsustem ports 2 and 3 
  }

  @Override
  public void initialize() {
  }

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }
  
  @Override
  public void periodic() {
    periodicStopWatch.start("hatch Subsystem");
    SmartDashboard.putBoolean("Unsuctioning Hatch", isDeploying);
    if(isDeploying){
      release();
      System.out.println("Hatch Unsuctioning Timer" + timer.get());
    }
    periodicStopWatch.end("hatch Subsystem");
  }

  public void extend() {
    solenoid.set(DoubleSolenoid.Value.kForward);
  }

  public void retract() {
    solenoid.set(DoubleSolenoid.Value.kReverse);
  }

  public void release() {
    if(timer.get() <=0.01) {
      //solenoid2.set(DoubleSolenoid.Value.kForward);
      //solenoid3.set(DoubleSolenoid.Value.kForward);
    } else if(timer.get() <=0.02) {
      //solenoid2.set(DoubleSolenoid.Value.kReverse);
      //solenoid3.set(DoubleSolenoid.Value.kReverse);
    }
    else {
      isDeploying = false;
      timer.stop();
    }
  }

  public void deploy() {
    isDeploying = true;
    timer.reset();
    timer.start();
  }
}
