/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.subsystems.ThumbsSubsystem;

public class ThumbsUp extends Command {
  // Use requires() here to declare subsystem dependencies
  private ThumbsSubsystem thumbs;
  
  public ThumbsUp(ThumbsSubsystem thumbs) {
    this.thumbs=thumbs;
    requires(thumbs);
}

  public ThumbsUp(Object thumbs2) {
}

// Called just before this Command runs the first time
  @Override
  protected void initialize() {
    thumbs.up();
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    thumbs.up();
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return true;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
    end();
  }
}
