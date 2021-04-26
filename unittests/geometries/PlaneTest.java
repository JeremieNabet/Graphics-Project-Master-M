package geometries;

import org.junit.jupiter.api.Test;
import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

import java.util.List;

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
        // ============ Equivalence Partitions Tests ==============
        // TC01: There is a simple single test here
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
        // ============ Equivalence Partitions Tests ==============
        // TC01: There is a simple single test here
        assertEquals(1, plane.getNormal(null).length());
    }

    /**
     * Test method for {@link Plane#getNormal(Point3D)}.
     */
    @Test
    void testGetNormal2() {
        // ============ Equivalence Partitions Tests ==============
        // TC01: There is a simple single test here
        Plane p1 = new Plane(
                new Point3D(0, 1, 0),
                new Point3D(1, 0, 0),
                new Point3D(0, 0, 1));

        // TC02: There is a simple single test here
        Plane p2 = new Plane(
                new Point3D(1, 0, 0),
                new Point3D(0, 0, 1),
                new Point3D(0, 1, 0));

        assertEquals(p1.getNormal(null), p2.getNormal(null));
    }
    /**
     * Test method for {@link Plane#findIntersections(Ray)}.
     */
    @Test
    public void testfindIntersectionsRay() {
        Plane pl = new Plane(new Point3D(0, 0, 1), new Vector(1, 1, 1));
        // ============ Equivalence Partitions Tests ==============
        // TC01: Ray into plane
        assertEquals(List.of(new Point3D(1, 0, 0)),
                pl.findIntersections(new Ray(new Point3D(0.5, 0, 0), new Vector(1, 0, 0))),
                "Bad plane intersection");

        // TC02: Ray out of plane
        assertNull(pl.findIntersections(new Ray(new Point3D(2, 0, 0), new Vector(1, 0, 0))),
                "Must not be plane intersection");

        // =============== Boundary Values Tests ==================
        // TC11: Ray parallel to plane
        assertNull(pl.findIntersections(new Ray(new Point3D(1, 1, 1), new Vector(0, 1, -1))),
                "Must not be plane intersection");

        // TC12: Ray in plane
        assertNull(pl.findIntersections(new Ray(new Point3D(0, 0.5, .5), new Vector(0, 1, -1))),
                "Must not be plane intersection");


        // TC13: Orthogonal ray into plane
        assertEquals(List.of(new Point3D(1d / 3, 1d / 3, 1d / 3)),
                pl.findIntersections(new Ray(new Point3D(1, 1, 1), new Vector(-1, -1, -1))),
                "Bad plane intersection");

        // TC14: Orthogonal ray out of plane
        assertNull(pl.findIntersections(new Ray(new Point3D(1, 1, 1), new Vector(1, 1, 1))),
                "Must not be plane intersection");

        // TC15: Orthogonal ray out of plane
        assertNull(pl.findIntersections(new Ray(new Point3D(1, 1, 1), new Vector(1, 1, 1))),
                "Must not be plane intersection");

        // TC16: Orthogonal ray from plane
        assertNull(pl.findIntersections(new Ray(new Point3D(0, 0.5, 0.5), new Vector(1, 1, 1))),
                "Must not be plane intersection");

        // TC17: Ray from plane
        assertNull(pl.findIntersections(new Ray(new Point3D(0, 0.5, 0.5), new Vector(1, 1, 0))),
                "Must not be plane intersection");

        // TC18: Ray from plane's Q point
        assertNull(pl.findIntersections(new Ray(new Point3D(0, 0, 1), new Vector(1, 1, 0))),
                "Must not be plane intersection");

    }

}