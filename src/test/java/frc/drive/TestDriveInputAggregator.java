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
    public void testDriveInputWithSkew(){
        
        DriveInput driver = new DriveInput();
        
        //we are looking at the targets ata 30 degree angle
        DriveInput navx = new DriveInput();
        navx.setFieldAngle(-60);
        
        DriveInput vision = new DriveInput();
        vision.setTargetLateral(-10);
        vision.setTargetDistance(100);
        
        DriveInput sensors = new DriveInput();
        
        DriveInputAggregator d = new DriveInputAggregator(true,true,true);
        
        DriveInput r = d.mergeTelemetry(driver,navx,vision,sensors);
        assertEquals(r.getTargetDistance(),86.6,1.0);
        assertEquals(r.getTargetLateral(),-58.6,1.0);
        
    }
    
    @Test
    public void testDriveInputWithVeryLittleSkew(){
        
        DriveInput driver = new DriveInput();
        
        //we are looking at the targets ata 30 degree angle
        DriveInput navx = new DriveInput();
        navx.setFieldAngle(-89);
        
        DriveInput vision = new DriveInput();
        vision.setTargetLateral(5);
        vision.setTargetDistance(40);
        
        DriveInput sensors = new DriveInput();
        
        DriveInputAggregator d = new DriveInputAggregator(true,true,true);
        
        DriveInput r = d.mergeTelemetry(driver,navx,vision,sensors);
        assertEquals(r.getTargetDistance(),40,1);
        assertEquals(r.getTargetLateral(),5,1);
        
    }    
    
    @Test
    public void testDriveInputWithSkewFromFarSide(){
        
        DriveInput driver = new DriveInput();
        
        //we are looking at the targets ata 30 degree angle
        DriveInput navx = new DriveInput();
        navx.setFieldAngle(45);
        
        DriveInput vision = new DriveInput();
        vision.setTargetLateral(10);
        vision.setTargetDistance(40);
        
        DriveInput sensors = new DriveInput();
        
        DriveInputAggregator d = new DriveInputAggregator(true,true,true);
        
        DriveInput r = d.mergeTelemetry(driver,navx,vision,sensors);
        assertEquals(r.getTargetDistance(),28,1);
        assertEquals(r.getTargetLateral(),-21,1);
        
    }     
    
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
        
        DriveInputAggregator dia = new DriveInputAggregator(true,true,true);
        assertEquals(S_LAT,
                dia.mergeTelemetry(driver,new DriveInput(),vision,sensors).getTargetLateral(),
                0.001);
        
        dia = new DriveInputAggregator(false,true,true);
        assertEquals(V_LAT,
                dia.mergeTelemetry(driver,new DriveInput(),vision,sensors).getTargetLateral(),
                0.001);
        
        dia = new DriveInputAggregator(true,false,true);
        assertEquals(S_LAT,
                dia.mergeTelemetry(driver,new DriveInput(),vision,sensors).getTargetLateral(),
                0.001);
        
        
        dia = new DriveInputAggregator(false,false,true);
        assertEquals(D_LAT,
                dia.mergeTelemetry(driver,new DriveInput(),vision,sensors).getTargetLateral(),
                0.001);

    }
}
