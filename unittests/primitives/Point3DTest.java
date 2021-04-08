package primitives;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Point3DTest {

    /**
     * Test method for{@link primitives.Point3D#subtract(primitives.Point3D)}
     */
    Point3D p1 = new Point3D(0.0d, 0.5d, 1.0d);
    Point3D p2 = new Point3D(0.00000000000001d, 0.49999999999999999d, 1d);
    Point3D p3 = new Point3D(1, 1, 1);
    @Test
    void testAdd() {
        Point3D p3 = new Point3D(1, 2, 3);
        assertTrue(!Point3D.ZERO.equals(p1.add(new Vector(-1, -2, -3))),
                "ERROR: Point + Vector does not work correctly");
    }
    @Test
    void testSubtract() {

        Point3D p1 = new Point3D(1,2,3);
        Vector result = new Point3D(2,3,4).subtract(p1);
        assertEquals(new Vector(1,1,1), result, "ERROR: Point - Point does not work correctly");
    }
    @Test
    void testDistance() {
        Point3D p3 = new Point3D(0, 0.5, 2.5);
        assertEquals(1.5, p3.distance(p2));
    }

    @Test
    void testDistanceSquared() {
        assertEquals(0, p1.distanceSquared(p2));
    }

    @Test
    void testEquals() {
        assertEquals(p1, p2);
    }

    @Test
    void testToString() {
        System.out.println("p1 = " + p1);
        System.out.println("p2 = " + p2);
    }


}