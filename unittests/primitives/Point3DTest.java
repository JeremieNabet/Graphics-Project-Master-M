package primitives;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
/**
 * Testing Point3D
 *
 * @author Jeremie and Israel
 *
 */
class Point3DTest {


    Point3D p1 = new Point3D(0.0d, 0.5d, 1.0d);
    Point3D p2 = new Point3D(0.00000000000001d, 0.49999999999999999d, 1d);
    Point3D p3 = new Point3D(1, 1, 1);

    /**
     * Test method for{@link primitives.Point3D#add(Vector)}
     */

    @Test
    void testAdd() {
        Point3D p3 = new Point3D(1, 2, 3);
        assertTrue(!Point3D.ZERO.equals(p1.add(new Vector(-1, -2, -3))),
                "ERROR: Point + Vector does not work correctly");
    }

    /**
     * Test method for{@link primitives.Point3D#subtract(Point3D)}
     */

    @Test
    void testSubtract() {

        Point3D p1 = new Point3D(1, 2, 3);
        Vector result = new Point3D(2, 3, 4).subtract(p1);
        assertEquals(new Vector(1, 1, 1), result, "ERROR: Point - Point does not work correctly");
    }

    /**
     * Test method for{@link primitives.Point3D#distance(Point3D)}
     */

    @Test
    void testDistance() {
       // Point3D p3 = new Point3D(0, 0.5, 2.5);
       // assertEquals(1.5, p3.distance(p2));

        Point3D point0 = new Point3D(1, 1, -100);
        Point3D point1 = new Point3D(-1, 1, -99);
        Point3D point2 = new Point3D(0, 0, -100);
        Point3D point3 = new Point3D(0.5, 0, -100);
        double resultsquared;
        double result;

        resultsquared = point3.distanceSquared(new Point3D(0,0,-100));
        System.out.println(resultsquared);
        result = point3.distance(new Point3D(0,0,-100));
        System.out.println(result);
    }

    /**
     * Test method for{@link primitives.Point3D#distanceSquared(Point3D)}
     */

    @Test
    void testDistanceSquared() {
        assertEquals(0, p1.distanceSquared(p2));
    }

    /**
     * Test method for{@link primitives.Point3D#equals(Object)}
     */

    @Test
    void testEquals() {
        assertEquals(p1, p2);
    }

    /**
     * Test method for{@link Point3D#toString()}
     */

    @Test
    void testToString() {
        System.out.println("p1 = " + p1);
        System.out.println("p2 = " + p2);
    }


}