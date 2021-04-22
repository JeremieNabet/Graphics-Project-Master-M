package geometries;

import org.junit.jupiter.api.Test;
import primitives.Point3D;
import primitives.Vector;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Testing Plane
 *
 * @author Jeremie and Israel
 */

class PlaneTest {

    Plane plane = new Plane(
            new Point3D(0, 0, 1),
            new Point3D(0, 2, 0),
            new Point3D(1, 0, 0));

    /**
     * Test method for {@link Plane#Plane(Point3D, Vector)}.
     */

    @Test
    void testPlane() {
        assertThrows(IllegalArgumentException.class, () -> new Plane(
                                new Point3D(1, 0, 0),
                                new Point3D(2, 0, 0),
                                new Point3D(4, 0, 0)),
                "constructor crate plane with 3 point on the same line");
    }

    /**
     * Test method for {@link Plane#getNormal()}.
     */
    @Test
    void testGetNormal1() {
        assertEquals(1, plane.getNormal(null).length());
    }

    /**
     * Test method for {@link Plane#getNormal(Point3D)}.
     */
    @Test
    void testGetNormal2() {
        Plane p1 = new Plane(
                new Point3D(0, 1, 0),
                new Point3D(1, 0, 0),
                new Point3D(0, 0, 1));

        Plane p2 = new Plane(
                new Point3D(1, 0, 0),
                new Point3D(0, 0, 1),
                new Point3D(0, 1, 0));

        assertEquals(p1.getNormal(null), p2.getNormal(null));
    }


}