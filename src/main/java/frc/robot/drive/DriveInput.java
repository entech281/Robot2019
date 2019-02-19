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
  private double targetX, targetY;
  
  public DriveInput() {
    this.valid = false;
  }

  public DriveInput(double x, double y, double z) {
    this.x = x;
    this.y = y;
    this.z = z;
    this.fieldAngle = 0.0;    
    this.targetX = 0.0;
    this.targetY = 0.0;
    this.valid = true;
  }

  public DriveInput(double x, double y, double z, double angle) {
    this.x = x;
    this.y = y;
    this.z = z;
    this.fieldAngle = angle;    
    this.targetX = 0.0;
    this.targetY = 0.0;
    this.valid = true;
  }

  public DriveInput(double x, double y, double z, double angle, double fieldX, double fieldY) {
    this.x = x;
    this.y = y;
    this.z = z;
    this.fieldAngle = angle;    
    this.targetX = fieldX;
    this.targetY = fieldY;
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

  public void setTargetX(double x) {
    this.targetX = x;
    this.valid = true;
  }

  public void setTargetY(double y) {
    this.targetY = y;
    this.valid = true;
  }

  public double getFieldAngle() {
    return fieldAngle;
  }

  public double getTargetX() {
    return targetX;
  }

  public double getTargetY() {
    return targetY;
  }

  public void mergeAllSensorData(DriveInput sensor_di) {
    if (sensor_di.isValid()) {
      setFieldAngle(sensor_di.getFieldAngle());
      setTargetX(sensor_di.getTargetX());
      setTargetY(sensor_di.getTargetY());
    }
  }

  public void mergeNavXSensorData(DriveInput navx_di) {
    if (navx_di.isValid()) {
      setFieldAngle(navx_di.getFieldAngle());
    }
  }

  public void mergeVisionSensorData(DriveInput vision_di) {
    if (vision_di.isValid()) {
      setTargetX(vision_di.getTargetX());
      setTargetY(vision_di.getTargetY());
    }
  }
}
