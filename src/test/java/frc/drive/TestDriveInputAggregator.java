/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package frc.drive;

import frc.robot.drive.DriveInput;
import frc.robot.drive.DriveInputAggregator;
import static org.junit.Assert.assertEquals;
import org.junit.Test;

/**
 *
 * @author dcowden
 */
public class TestDriveInputAggregator {
    
    @Test
    public void testDriveInputAggregatorUsesBoth(){
        double V_LAT = 2.1;
        double S_LAT = 1.6;
        double D_LAT = 0.8;
        
        DriveInput driver = new DriveInput();
        driver.setTargetLateral(D_LAT);
        
        DriveInput sensors = new DriveInput();
        sensors.setTargetLateral(S_LAT);
        
        DriveInput vision = new DriveInput();
        vision.setTargetLateral(V_LAT);
        
        DriveInputAggregator dia = new DriveInputAggregator(true,true);
        assertEquals(S_LAT,
                dia.mergeTelemetry(driver,new DriveInput(),vision,sensors).getTargetLateral(),
                0.001);
        
        dia = new DriveInputAggregator(false,true);
        assertEquals(V_LAT,
                dia.mergeTelemetry(driver,new DriveInput(),vision,sensors).getTargetLateral(),
                0.001);
        
        dia = new DriveInputAggregator(true,false);
        assertEquals(S_LAT,
                dia.mergeTelemetry(driver,new DriveInput(),vision,sensors).getTargetLateral(),
                0.001);
        
        
        dia = new DriveInputAggregator(false,false);
        assertEquals(D_LAT,
                dia.mergeTelemetry(driver,new DriveInput(),vision,sensors).getTargetLateral(),
                0.001);
                
                
        
    }
}
