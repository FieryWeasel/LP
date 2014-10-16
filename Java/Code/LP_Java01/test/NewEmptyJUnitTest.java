/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import Tests.Point;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Kazuya
 */
public class NewEmptyJUnitTest {
    private final static Point ORIGIN = new Point(0, 0);
    private final static double EPSILON = 1e-6;
     @Test
     public void testEquals() {
         Point other = new Point(0, 0);
         assertEquals(ORIGIN, other);
         assertEquals(ORIGIN.hashCode(), other.hashCode());
     }
     
     @Test
     public void testDistance(){
         distanceOrigin(ORIGIN, 0.0);
         distanceOrigin(new Point(1, 0), 1.0);
         distanceOrigin(new Point(1, 1), Math.sqrt(2.0));
     }
     
     private void distanceOrigin(Point p, double distance){
         assertEquals(distance, ORIGIN.distance(p), EPSILON);
     }
}
