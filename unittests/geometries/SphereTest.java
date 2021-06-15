package geometries;

import org.junit.jupiter.api.Test;
import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
/**
 * unittests for sphere class
 */
class SphereTest {
    /**
     * Test method for {@link geometries.Sphere#getNormal(Point3D)}.
     */
    @Test
    void getNormal() {
        // ============ Equivalence Partitions Tests ==============

        // TC01: simple test
        Sphere sphere = new Sphere(new Point3D(0,0,0), 1);
        assertEquals(
                new Vector(new Point3D(0,0,1)),
                sphere.getNormal(new Point3D(0,0,1)),
                "ERROR - TC01: bad normal to sphere");
    }

    /**
     * Test method for {@link geometries.Sphere#findIntersections(Ray)}.
     */
    @Test
    public void testFindIntersections() {
        Sphere sphere = new Sphere(new Point3D(1, 0, 0), 1d);

        // ============ Equivalence Partitions Tests ==============

        // TC01: Ray's line is outside the sphere (no points)
        assertNull(
                sphere.findIntersections(new Ray(new Point3D(-1, 0, 0), new Vector(1, 1, 0))),
                "ERROR - TC01: ray's line out of sphere");

        // TC02: Ray starts before and crosses the sphere (2 points)
        Point3D p1 = new Point3D(0.0651530771650466, 0.355051025721682, 0);
        Point3D p2 = new Point3D(1.53484692283495, 0.844948974278318, 0);
        List<Point3D> result2 =
                sphere.findIntersections(new Ray(new Point3D(-1, 0, 0), new Vector(3, 1, 0)));
        assertEquals(2, result2.size(), "ERROR - TC02: the number of points is incorrect");
        if (result2.get(0).getX() > result2.get(1).getX()) {
            result2 = List.of(result2.get(1), result2.get(0));
        }
        assertEquals(List.of(p1, p2), result2, "ERROR - TC02: ray crosses sphere");

        // TC03: Ray starts inside the sphere (1 point)
        Point3D p3 = new Point3D(1, 0, 1);
        List<Point3D> result3 =
                sphere.findIntersections(new Ray(new Point3D(1.5, 0, 0), new Vector(-0.5, 0, 1)));
        assertEquals(1, result3.size(), "ERROR - TC03: the number of points is incorrect");
        result3 = List.of(result3.get(0));
        assertEquals(List.of(p3), result3, "ERROR - TC03: ray is exiting sphere");

        // TC04: Ray starts after the sphere (no points)
        assertNull(
                sphere.findIntersections(new Ray(new Point3D(1, 0, 2), new Vector(1, 1, 1))),
                "ERROR - TC04: ray is off the sphere");

        // =============== Boundary Values Tests ==================

        // Ray's line crosses the sphere but not the center

        // TC05: Ray starts at sphere and goes inside (1 points)
        Point3D p5 = new Point3D(1, 0, 1);
        List<Point3D> result5 =
                sphere.findIntersections(new Ray(new Point3D(0, 0, 0), new Vector(1, 0, 1)));
        assertEquals(1, result5.size(), "ERROR - TC05: the number of points is incorrect");
        assertEquals(List.of(p5),
                result5,
                "ERROR - TC05: ray is start on the sphere and exiting via the sphere");

        // TC06: Ray starts at sphere and goes outside (no points)
        assertNull(
                sphere.findIntersections(new Ray(new Point3D(1, 0, 1), new Vector(1, 1, 1))),
                "ERROR - TC06: ray is start on the sphere and exiting off the sphere");

        // Ray's line goes through the center

        // TC07: Ray starts before the sphere (2 points)
        Point3D p7a = new Point3D(0, 0, 0);
        Point3D p7b = new Point3D(2, 0, 0);
        List<Point3D> result7 =
                sphere.findIntersections(new Ray(new Point3D(-1, 0, 0), new Vector(1, 0, 0)));
        assertEquals(2, result7.size(), "ERROR - TC07: the number of points is incorrect");
        if (result7.get(0).getX() > result2.get(1).getX()) {
            result7 = List.of(result7.get(1), result7.get(0));
        }
        assertEquals(List.of(p7a, p7b), result7, "ERROR - TC07: ray crosses sphere's center");

        // TC08: Ray starts at sphere and goes inside (1 points)
        Point3D p8 = new Point3D(2, 0, 0);
        List<Point3D> result8 =
                sphere.findIntersections(new Ray(new Point3D(0, 0, 0), new Vector(1, 0, 0)));
        assertEquals(1, result8.size(), "ERROR - TC08: the number of points is incorrect");
        assertEquals(List.of(p8), result8, "ERROR - TC08: ray is start on the sphere and crosses sphere's center");

        // TC09: Ray starts inside (1 points)
        Point3D p9 = new Point3D(2, 0, 0);
        List<Point3D> result9 =
                sphere.findIntersections(new Ray(new Point3D(0.5, 0, 0), new Vector(1, 0, 0)));
        assertEquals(1, result9.size(), "ERROR - TC09: the number of points is incorrect");
        assertEquals(List.of(p9), result9, "ERROR - TC09: ray is start on the sphere and crosses sphere's center");

        // TC10: Ray starts at the center (1 points)
        Point3D p10 = new Point3D(2, 0, 0);
        List<Point3D> result10 =
                sphere.findIntersections(new Ray(new Point3D(1, 0, 0), new Vector(1, 0, 0)));
        assertEquals(1, result10.size(), "ERROR - TC10: the number of points is incorrect");
        assertEquals(List.of(p10), result10, "ERROR - TC10: ray is start on the sphere's center");

        // TC11: Ray starts at sphere and goes outside (no points)
        assertNull(
                sphere.findIntersections(new Ray(new Point3D(1, 0, 1), new Vector(0, 0, 1))),
                "ERROR - TC11: ray is start on the sphere and exiting off the sphere");

        // TC12: Ray starts after sphere (no points)
        assertNull(
                sphere.findIntersections(new Ray(new Point3D(1, 0, 1.5), new Vector(0, 0, 1))),
                "ERROR - TC12: ray is start out of the sphere and exiting off the sphere");

        // Ray's line is tangent to the sphere (all tests no points)

        // TC13: Ray starts before the tangent point
        assertNull(
                sphere.findIntersections(new Ray(new Point3D(0, 0, 1), new Vector(1, 0, 0))),
                "ERROR - TC13: ray is start out of the sphere and tangent to the sphere");

        // TC14: Ray starts at the tangent point
        assertNull(
                sphere.findIntersections(new Ray(new Point3D(1, 0, 1), new Vector(1, 0, 0))),
                "ERROR - TC14: ray is start on the sphere and tangent to the sphere");

        // TC15: Ray starts after the tangent point
        assertNull(
                sphere.findIntersections(new Ray(new Point3D(1.5, 0, 1), new Vector(1, 0, 0))),
                "ERROR - TC15: ray is start out of the sphere and tangent to the sphere");

        // Special cases

        // TC16: Ray's line is outside, ray is orthogonal to ray start to sphere's center line
        assertNull(
                sphere.findIntersections(new Ray(new Point3D(3, 0, 0), new Vector(1, 0, 0))),
                "ERROR - TC16: ray's line is outside, ray is orthogonal to ray start to sphere's center line");
    }
}