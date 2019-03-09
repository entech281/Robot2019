/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.RobotMap;

/**
 * Add your docs here.
 */
public class HatchSubsystem extends BaseSubsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.

  private DoubleSolenoid pusherSolenoid;
  private Solenoid topReleaseSolonoid;
  private Solenoid bottomReleaseSolonoid;

  private Timer timer = new Timer();
  private boolean isDeploying = false;

  public HatchSubsystem() {
    super();
    pusherSolenoid = new DoubleSolenoid(RobotMap.CAN.PCM_ID, RobotMap.PNEUMATICS.HATCH_FORWARD,
        RobotMap.PNEUMATICS.HATCH_REVERSE);
    topReleaseSolonoid = new Solenoid(RobotMap.CAN.PCM_ID,
      RobotMap.PNEUMATICS.HATCH_RELEASE_TOP);
    bottomReleaseSolonoid = new Solenoid(RobotMap.CAN.PCM_ID, 
      RobotMap.PNEUMATICS.HATCH_RELEASE_BOTTOM);

    //add 2 more like in flip subsustem ports 2 and 3 
  }

  @Override
  public void initialize() {
    pusherSolenoid.set(Value.kReverse);
    topReleaseSolonoid.set(false);
    bottomReleaseSolonoid.set(false);
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
    pusherSolenoid.set(DoubleSolenoid.Value.kForward);
  }

  public void retract() {
    pusherSolenoid.set(DoubleSolenoid.Value.kReverse);
  }

  public void release() {
    if(timer.get() <=0.1) {
      topReleaseSolonoid.set(true);
      bottomReleaseSolonoid.set(true);
    } else if(timer.get() <=0.2) {
      topReleaseSolonoid.set(false);
      bottomReleaseSolonoid.set(false);
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
