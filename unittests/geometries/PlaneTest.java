package geometries;

import org.junit.jupiter.api.Test;
import primitives.Point3D;

import static org.junit.jupiter.api.Assertions.*;

class PlaneTest {

    Plane plane = new Plane(
            new Point3D(0, 0, 1),
            new Point3D(0, 2, 0),
            new Point3D(1, 0, 0));

    @Test
    void testPlane() {
        try {
            new Plane(
                    new Point3D(1, 0, 0),
                    new Point3D(2, 0, 0),
                    new Point3D(4, 0, 0));
            fail("constructor crate plane with 3 point on the same line");
        } catch (IllegalArgumentException e) {
        }
    }

    /**
     *tests for {@link geometries.Plane#getNormal(primitives.Point3D)}.
     * */
    @Test
    void getNormal1() {
        assertEquals(1, plane.getNormal(null).length());
    }

    @Test
    void getNormal2() {
        Plane p1 = new Plane(
                new Point3D(0,1,0),
                new Point3D(1,0,0),
                new Point3D(0,0,1));

        Plane p2 = new Plane(
                new Point3D(1,0,0),
                new Point3D(0,0,1),
                new Point3D(0,1,0));

        assertEquals(p1.getNormal(null), p2.getNormal(null));
    }

    @Test
    void testGetNormal() {

    }
}