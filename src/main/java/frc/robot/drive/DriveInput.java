/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.drive;

/**
 * Add your docs here.
 */
public class DriveInput {
  private boolean valid = false;
  private double x,y,z;
  private double fieldAngle;
  private double targetLateral, targetDistance;
  
  public DriveInput() {
    this.valid = false;
  }

  public DriveInput(double x, double y, double z) {
    this.x = x;
    this.y = y;
    this.z = z;
    this.fieldAngle = 0.0;    
    this.targetDistance = 0.0;
    this.targetLateral = 0.0;
    this.valid = true;
  }

  public DriveInput(double x, double y, double z, double angle) {
    this.x = x;
    this.y = y;
    this.z = z;
    this.fieldAngle = angle;    
    this.targetDistance = 0.0;
    this.targetLateral = 0.0;
    this.valid = true;
  }

  public DriveInput(double x, double y, double z, double angle, double distance, double lateral) {
    this.x = x;
    this.y = y;
    this.z = z;
    this.fieldAngle = angle;    
    this.targetLateral = lateral;
    this.targetDistance = distance;
    this.valid = true;
  }

  public boolean isValid() {
    return this.valid;
  }
  
  public double getX(){
    return x;
  }
    
  public double getY(){
    return y;
  }
    
  public double getZ(){
    return z;
  }

  public void setValid(boolean valid) {
    this.valid = valid;
  }

  public void setFieldAngle(double angle) {
    this.fieldAngle = angle;
    this.valid = true;
  }

  public void setTargetDistance(double x) {
    this.targetDistance = x;
    this.valid = true;
  }

  public void setTargetLateral(double y) {
    this.targetLateral = y;
    this.valid = true;
  }

  public double getFieldAngle() {
    return fieldAngle;
  }

  public double getTargetDistance() {
    return targetDistance;
  }

  public double getTargetLateral() {
    return targetLateral;
  }

  public DriveInput copy(){
      return new DriveInput(this.x,this.y,this.z,this.fieldAngle,this.targetDistance,this.targetLateral);
  }
}
