/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package frc.robot.oi;

/**
 *
 * @author dcowden
 */
public class OperatorDriveInput {

    public OperatorDriveInput(double x, double y, double z){
        this.x = x;
        this.y = y;
        this.z = z;
    }
    
    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getZ() {
        return z;
    }

    private double x = 0;
    private double y = 0;
    private double z = 0;

    public boolean isFieldAbsolute() {
        return fieldAbsolute;
    }

    public void setFieldAbsolute(boolean fieldAbsolute) {
        this.fieldAbsolute = fieldAbsolute;
    }

    public boolean isCanTwist() {
        return canTwist;
    }

    public void setCanTwist(boolean canTwist) {
        this.canTwist = canTwist;
    }
    private boolean fieldAbsolute = false;
    private boolean canTwist = false;
    
}
