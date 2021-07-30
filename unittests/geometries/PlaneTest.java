package geometries;

import org.junit.jupiter.api.Test;
import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

import java.util.LinkedList;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * unittests for plane class
 */
class PlaneTest {
    /**
     * Test method for {@link.geometries.Plane#getNormal(geometries.Plane)}.
     */
    @Test
    void getNormal() {
        // ============ Equivalence Partitions Tests ==============

        // TC01: simple test
        Plane pl1 = new Plane(
                new Point3D(0, 0, 1),
                new Point3D(1, 0, 1),
                new Point3D(0, 1, 1));
        assertTrue(
                (new Vector(new Point3D(0, 0, 1))).equals(pl1.getNormal(new Point3D(0, 0, 1))) ||
                        (new Vector(new Point3D(0, 0, -1))).equals(pl1.getNormal(new Point3D(0, 0, 1))),
                "ERROR - TC01: bad normal to plane");
    }

    /**
     * Test method for {@link Plane#findIntersections(Ray)}.
     */
    @Test
    public void testFindIntersections() {
        Plane plane = new Plane(new Point3D(0, 0, 1), new Vector(0, 0, 1));

        // ============ Equivalence Partitions Tests ==============

        // TC01: Ray intersect the plane (1 points)
        Ray ray1 = new Ray(new Point3D(1, 0, 0), new Vector(-1, 0, 1).normalize());
        LinkedList<Point3D> list1 = new LinkedList<>();
        list1.add(new Point3D(0, 0, 1));
        assertEquals(list1, plane.findIntersections(ray1), "ERROR - TC01: ray intersect the plane");

        // TC02: Ray does not intersect the plane (no points)
        Ray ray2 = new Ray(new Point3D(1, 0, 0), new Vector(-1, 0, -1).normalize());
        assertNull(plane.findIntersections(ray2), "ERROR - TC02: ray does not intersect the plane");

        // =============== Boundary Values Tests ==================

        // TC03: Ray is included in the plane (no point)
        Ray ray3 = new Ray(new Point3D(0, 1, 1), new Vector(1, 0, 0).normalize());
        assertNull(plane.findIntersections(ray3), "ERROR - TC03: ray is included in the plane");

        // TC04: Ray is parallel to the plane (no point)
        Ray ray4 = new Ray(new Point3D(0, 0, 2), new Vector(2, 0, 0).normalize());
        assertNull(plane.findIntersections(ray4), "ERROR - TC04: ray is parallel to the plane");

        // TC05: Ray is orthogonal to the plane and start before the plane (1 point)
        Ray ray5 = new Ray(new Point3D(0, 0, 2), new Vector(0, 0, -1).normalize());
        LinkedList<Point3D> list5 = new LinkedList<>();
        list5.add(new Point3D(0, 0, 1));
        assertEquals(list5, plane.findIntersections(ray5), "ERROR - TC05: ray is orthogonal to the plane and start before the plane");

        // TC06: Ray is orthogonal to the plane and start in the plane (no point)
        Ray ray6 = new Ray(new Point3D(0, 1, 1), new Vector(0, 0, 1).normalize());
        assertNull(plane.findIntersections(ray6), "ERROR - ray is orthogonal to the plane and start in the plane");

        // TC07: Ray is orthogonal to the plane and start after the plane ( no point)
        Ray ray7 = new Ray(new Point3D(0, 0, -1), new Vector(0, 0, -1).normalize());
        assertNull(plane.findIntersections(ray7), "ERROR - TC07: ray is orthogonal to the plane and start after the plane");

        // TC08: Ray is neither orthogonal nor parallel to and begins at the plane (p0 is in the plane , but not the ray) (no point)
        Ray ray8 = new Ray(new Point3D(0, 1, 1), new Vector(0, 1, 1).normalize());
        assertNull(plane.findIntersections(ray8), "ERROR - TC08: ray is neither orthogonal nor parallel to and begins at the plane (p0 is in the plane , but not the ray)");

        // TC09: Ray is neither orthogonal nor parallel to the plane and begins in
        //the same point which appears as reference point in the plane (q0) (no point)
        Ray ray9 = new Ray(new Point3D(0, 0, 1), new Vector(0, 1, 1).normalize());
        assertNull(plane.findIntersections(ray9), "ERROR - TC09: ray is neither orthogonal nor parallel to the plane and begins in the same point which appears as reference point in the plane (q0)");
    }
}
