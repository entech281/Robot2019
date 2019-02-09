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
  double x,y,z;
  double field_angle, field_x, field_y;
  
  public DriveInput(double x, double y, double z) {
    this.x = x;
    this.y = y;
    this.z = z;
    this.field_angle = 0.0;    
    this.field_x = 0.0;
    this.field_y = 0.0;
  }

  public DriveInput(double x, double y, double z, double angle) {
    this.x = x;
    this.y = y;
    this.z = z;
    this.field_angle = angle;    
    this.field_x = 0.0;
    this.field_y = 0.0;
  }

  public DriveInput(double x, double y, double z, double angle, double fieldX, double fieldY) {
    this.x = x;
    this.y = y;
    this.z = z;
    this.field_angle = angle;    
    this.field_x = fieldX;
    this.field_y = fieldY;
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

  public double getFieldAngle() {
    return field_angle;
  }

  public double getFieldX() {
    return field_x;
  }

  public double getFieldY() {
    return field_y;
  }
}
