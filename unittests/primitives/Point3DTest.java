package primitives;

import org.junit.jupiter.api.Test;

import static java.lang.Math.sqrt;
import static org.junit.jupiter.api.Assertions.*;
import static primitives.Point3D.ZERO;

/**
 * unittests for point3D class
 *
 * @author Jeremie Nabet and Israel Bellaiche
 */
class Point3DTest {
    Point3D p1 = new Point3D(1.0d, 2.0d, 3.0d);
    Point3D p2 = new Point3D(1.0000000000000001, 2, 3);

    /**
     * Test method for {@link primitives.Point3D#equals(Object)}.
     */
    @Test
    void testEquals() {
        assertEquals(p1, p2);
    }

    /**
     * Test method for {@link primitives.Point3D#distanceSquared(Point3D)}.
     */
    @Test
    void testDistanceSquared() {
        Point3D p3 = new Point3D(-1, -1.52, -3);
        double result = p1.distanceSquared(p3);
        assertEquals(52.39, result, 0.001);
    }

    /**
     * Test method for {@link primitives.Point3D#distance(Point3D)}.
     */
    @Test
    void testDistance() {
        Point3D p3 = new Point3D(-1, -1.52, -3);
        double result = p1.distance(p3);
        assertEquals(sqrt(52.39), result, 0.001);
    }

    /**
     * Test method for {@link primitives.Point3D#add(Vector)}.
     */

    @Test
    void testAdd() {
        // TC01: operations with points and vectors
        assertEquals(ZERO,
                (new Point3D(1, 2, 3)).add(new Vector(-1, -2, -3)),
                "ERROR - TC01: Point + Vector does not work correctly");
    }

    /**
     * Test method for {@link primitives.Point3D#subtract(Point3D)}.
     */
    @Test
    void testSubtract() {
        // TC01: operations with points and vectors
        assertEquals(new Vector(1, 1, 1),
                new Point3D(2, 3, 4).subtract(new Point3D(1, 2, 3)),
                "ERROR - TC01: Point - Point does not work correctly");
    }

}