package geometries;

import org.junit.jupiter.api.Test;
import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

import static org.junit.jupiter.api.Assertions.*;
/**
 * Testing Tube
 *
 * @author Jeremie and Israel
 *
 */

class TubeTest {

    /**
     * Test method for {@link Tube#getNormal(Point3D)}.
     */
    @Test
    void testGetNormal() {
        Ray ray = new Ray(new Point3D(0,1,0), new Vector(0,1,0));
        Tube tb = new Tube(ray, 2);

        assertEquals(tb.getNormal(new Point3D(2,0,0)) ,new Vector(1,0,0));

        assertEquals(tb.getNormal(new Point3D(2,1,0)) ,new Vector(1,0,0));

    }
}