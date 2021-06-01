package primitives;

import org.junit.jupiter.api.Test;

import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class RayTest {
    /**
     * Test method for {@link primitives.Ray#getPoint(double)}.
     */
    @Test
    void getTargetPoint() {

        // ============ Equivalence Partitions Tests ==============

        // TC01: regular point
        Ray ray1 = new Ray(new Point3D(0, 0, 1), new Vector(new Point3D(0, 0, 1)));
        assertEquals(
                new Point3D(0, 0, 2),
                ray1.getPoint(1),
                "ERROR - TC01: regular point. bad calculate for target point");

        // =============== Boundary Values Tests ==================

        // TC02: Zero-point
        Ray ray2 = new Ray(new Point3D(0, 0, 1), new Vector(new Point3D(0, 0, 1)));
        assertEquals(
                new Point3D(0, 0, 1),
                ray2.getPoint(0),
                "ERROR - TC02: Zero-point. bad calculate for target point");
    }

    Ray ray = new Ray(Point3D.ZERO, new Vector(0, 0, 1));

    /**
     * Test method for {@link primitives.Ray#findClosestPoint(List)}.
     */
    @Test
    void testGetClosestPoint() {

        // ============ Equivalence Partitions Tests ==============

        //TC01: the closest point is the middle point in the list
        List<Point3D> point3DS1 = new LinkedList<>();
        point3DS1.add(new Point3D(-1000, 90, 100));
        point3DS1.add(new Point3D(50, 48, 1000));
        point3DS1.add(new Point3D(0, .5, 1));
        point3DS1.add(new Point3D(-20, 60, 50));
        point3DS1.add(new Point3D(0, 0, -90));
        assertEquals(
                point3DS1.get(2),
                ray.findClosestPoint(point3DS1),
                "ERROR - TC01: the closest point is the middle point in the list");

        // =============== Boundary Values Tests ==================

        //TC02: empty/null list
        List<Point3D> point3DS2 = null;
        assertNull(ray.findClosestPoint(point3DS2), "ERROR - TC02: empty/null list");

        //TC03: the closest point is the first point in the list
        List<Point3D> point3DS3 = new LinkedList<>();
        point3DS3.add(new Point3D(0, .5, 1));
        point3DS3.add(new Point3D(-1000, 90, 100));
        point3DS3.add(new Point3D(50, 48, 1000));
        point3DS3.add(new Point3D(-20, 60, 50));
        point3DS3.add(new Point3D(0, 0, -90));
        assertEquals(
                point3DS3.get(0),
                ray.findClosestPoint(point3DS3),
                "ERROR - TC03: the closest point is the first point in the list");

        //TC04: the closest point is the last point in the list
        List<Point3D> point3DS4 = new LinkedList<>();
        point3DS4.add(new Point3D(-1000, 90, 100));
        point3DS4.add(new Point3D(50, 48, 1000));
        point3DS4.add(new Point3D(-20, 60, 50));
        point3DS4.add(new Point3D(0, 0, -90));
        point3DS4.add(new Point3D(0, .5, 1));
        assertEquals(
                point3DS4.get(4),
                ray.findClosestPoint(point3DS4),
                "ERROR - TC04: the closest point is the last point in the list");
    }

    /**
     * Test method for {@link primitives.Ray#findClosestGeoPoint(List)}.
     */
    @Test
    void testGetClosestGeoPoint() {

        // ============ Equivalence Partitions Tests ==============

        //TC01: the closest GeoPoint is the middle point in the list
        List<Point3D> point3DS1 = new LinkedList<>();
        point3DS1.add(new Point3D(-1000, 90, 100));
        point3DS1.add(new Point3D(50, 48, 1000));
        point3DS1.add(new Point3D(0, .5, 1));
        point3DS1.add(new Point3D(-20, 60, 50));
        point3DS1.add(new Point3D(0, 0, -90));
        assertEquals(
                point3DS1.get(2),
                ray.findClosestPoint(point3DS1),
                "ERROR - TC01: the closest point is the middle point in the list");

        // =============== Boundary Values Tests ==================

        //TC02: empty/null list
        List<Point3D> point3DS2 = null;
        assertNull(ray.findClosestPoint(point3DS2), "ERROR - TC02: empty/null list");

        //TC03: the closest GeoPoint is the first point in the list
        List<Point3D> point3DS3 = new LinkedList<>();
        point3DS3.add(new Point3D(0, .5, 1));
        point3DS3.add(new Point3D(-1000, 90, 100));
        point3DS3.add(new Point3D(50, 48, 1000));
        point3DS3.add(new Point3D(-20, 60, 50));
        point3DS3.add(new Point3D(0, 0, -90));
        assertEquals(
                point3DS3.get(0),
                ray.findClosestPoint(point3DS3),
                "ERROR - TC03: the closest point is the first point in the list");

        //TC04: the closest GeoPoint is the last point in the list
        List<Point3D> point3DS4 = new LinkedList<>();
        point3DS4.add(new Point3D(-1000, 90, 100));
        point3DS4.add(new Point3D(50, 48, 1000));
        point3DS4.add(new Point3D(-20, 60, 50));
        point3DS4.add(new Point3D(0, 0, -90));
        point3DS4.add(new Point3D(0, .5, 1));
        assertEquals(
                point3DS4.get(4),
                ray.findClosestPoint(point3DS4),
                "ERROR - TC04: the closest point is the last point in the list");
    }
}