/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

import frc.robot.subsystems.SnapController;

public class TestSnapController {
    @Test
    public void testThatRounderWorks(){
        SnapController snap = new SnapController();
        assertEquals(0.0, snap.findNearestQuadrant(337.5));
        assertEquals(0.0, snap.findNearestQuadrant(22.5));

        assertEquals(45.0, snap.findNearestQuadrant(67.5));

        assertEquals(90.0, snap.findNearestQuadrant(112.5));

        assertEquals(180.0, snap.findNearestQuadrant(157.5));
        assertEquals(180.0, snap.findNearestQuadrant(202.5));

        assertEquals(270.0, snap.findNearestQuadrant(247.5));

        assertEquals(315.0, snap.findNearestQuadrant(292.5));
    }

    @Test
    public void test() {
        SnapController snap = new SnapController();
        assertEquals(0.0, snap.findNearestQuadrant(337.5));
    }
}