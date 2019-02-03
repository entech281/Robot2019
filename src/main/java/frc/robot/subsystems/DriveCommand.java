/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package frc.robot.subsystems;

/**
 *
 * @author dcowden
 */
public class DriveCommand {

    private double x = 0;
    private double y = 0;
    private double z = 0;
    private double angle = 0;
    
    public DriveCommand(double x, double y, double z,  double angle){
        this.x = x;
        this.y = y;
        this.z = z;
        this.angle = angle;
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
    public double getAngle(){
        return angle;
    }
    
    @Override
    public String toString(){
        return String.format("x=%f,y=%f,z=%f,angle=%f",x,y,z,angle);
    }
    
}
