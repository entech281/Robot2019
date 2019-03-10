
import frc.pid.CappedLinearControl;
import frc.pid.Controller;

import org.junit.Test;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author dcowden
 */
public class TestCappedLinearController {
    
    @Test
    public void testLateralSettings(){
    
        //the settings for lateral align filter
        //tolerance, threshold, minOutput, maxOutput
        Controller cappedLinear = new CappedLinearControl(0.1,15, 0.2, 1.0);
        
        //actual,desired
        double[] samples = { 100, 90, 80, 50, 20, 15, 10, 5, 4, 3, 2, 1 };
        
        for ( double s:samples){
            System.out.println("Desired=" + 0.0 +
                    ",actual=" + s +
                    ", output=" + cappedLinear.getOutput(s,0.0));
        }
        
    }
}
