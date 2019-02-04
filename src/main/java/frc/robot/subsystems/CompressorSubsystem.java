/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Compressor;

/**
 *
 * @author dcowden
 */
public class CompressorSubsystem extends BaseSubsystem{

    private Compressor compressor;
    
    @Override
    public void initialize() {
        compressor = new Compressor(10);
        compressor.start();
    }
    
}
