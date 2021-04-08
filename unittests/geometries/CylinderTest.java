package geometries;

import org.junit.jupiter.api.Test;
import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

import static org.junit.jupiter.api.Assertions.*;
/**
 * Testing Cylinders
 *
 * @author Jeremie and Israel
 *
 */

class CylinderTest {
    /**
     * Test method for {@link Cylinder#getNormal(Point3D)}.
     */
    @Test
    void testGetNormal() {

        Cylinder cld = new Cylinder(1,
                new Ray(new Point3D(1,0,0), new Vector(0,0,3)),
                9.93);

        assertEquals(cld.axisRay.getDir(), cld.getNormal(new Point3D(0.56, 0,0)));

        assertEquals(cld.axisRay.getDir(), cld.getNormal(new Point3D(0,0.5,3)));
    }
}