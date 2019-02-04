/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package frc.robot.subsystems;

import edu.wpi.first.cameraserver.CameraServer;

/**
 *
 * @author dcowden
 */
public class CameraSubsystem extends BaseSubsystem{

    @Override
    public void initialize() {
        CameraServer.getInstance().startAutomaticCapture();
    }
    
}
