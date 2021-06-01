package geometries;

import org.junit.jupiter.api.Test;
import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * unittest for cylinder class.
 * @author elchanan bloom & israel azoulay
 */
class CylinderTest {

    /**
     * Test method for {@link geometries.Cylinder#getNormal(Point3D)}.
     */
    @Test
    public void testGetNormal() {
        // ============ Equivalence Partitions Tests ==============

        // TC01: point on the top of the cylinder
        Cylinder pl = new Cylinder( new Ray(new Point3D(0, 0, 0),new Vector(0,0,1)),
                1d,
                1d);
        assertEquals(new Vector(0, 0, 1d),
                pl.getNormal(new Point3D(0, 0.5, 1)),
                "ERROR - TC01: bad normal to cylinder");
        // TC02: point on the base of the cylinder
        assertEquals(new Vector(0, 0, 1d),
                pl.getNormal(new Point3D(0, 0.5, 0)),
                "ERROR - TC02: bad normal to cylinder");
        // TC03: point on the surface of the cylinder
        assertEquals(new Vector(0, 1d, 0),
                pl.getNormal(new Point3D(0, 0.5, 0.5)),
                "ERROR - TC03: bad normal to cylinder");

        // =============== Boundary Values Tests ==================
        // TC04: between surface and base of cylinder
        assertNotEquals(pl.getNormal(new Point3D(0,1,0.00001)),
                pl.getNormal(new Point3D(0,0.99999999,0)),
                "ERROR - TC04: boundary test base and surface");
        // TC05: between surface and top of cylinder
        assertNotEquals(pl.getNormal(new Point3D(0,1,0.99999)),
                pl.getNormal(new Point3D(0,0.99999999,1)),
                "ERROR - TC05: boundary test top and surface");
    }

    /**
     * Test method for {@link geometries.Cylinder#findIntersections(Ray)}.
     */
    @Test
    void findIntersections() {
        Cylinder cylinder = new Cylinder(new Ray(new Point3D(2,0,0), new Vector(0,0,1)), 1d, 2d);

        // ============ Equivalence Partitions Tests ==============

        //TC01 ray is outside and parallel to the cylinder's ray
        List<Point3D> result = cylinder.findIntersections(new Ray(new Point3D(5,0,0), new Vector(0,0,1)));
        assertNull(result, "ERROR - TC01: the number of points is incorrect");


        //TC02 ray starts inside and parallel to the cylinder's ray
        result = cylinder.findIntersections(new Ray(new Point3D(2.5,0,1), new Vector(0,0,1)));
        assertEquals(1, result.size(), "ERROR - TC02: the number of points is incorrect");
        assertEquals(List.of(new Point3D(2.5,0,2)), result, "ERROR - TC02: the intersection point is not good");

        //TC03 ray starts outside and parallel to the cylinder's ray and crosses the cylinder
        result = cylinder.findIntersections(new Ray(new Point3D(2.5,0,-1), new Vector(0,0,1)));
        assertEquals(2, result.size(), "ERROR - TC03: the number of points is incorrect");
        assertEquals(List.of(new Point3D(2.5, 0, 0), new Point3D(2.5,0,2)), result, "ERROR - TC03: the intersection point is not good");

        //TC04 ray starts from outside and crosses the cylinder
        result = cylinder.findIntersections(new Ray(new Point3D(-2,0,0.5), new Vector(1,0,0)));
        assertEquals(2, result.size(), "ERROR - TC04: the number of points is incorrect");
        assertEquals(List.of(new Point3D(3,0,0.5), new Point3D(1,0,0.5)), result, "ERROR - TC04: the intersection point is not goods");

        //TC05 ray starts from inside and crosses the cylinder
        result = cylinder.findIntersections(new Ray(new Point3D(1.5,0,0.5), new Vector(1,0,0)));
        assertEquals(1, result.size(), "ERROR - TC05: the number of points is incorrect");
        assertEquals(List.of(new Point3D(3,0,0.5)), result, "ERROR - TC05: the intersection point is not goods");

        //TC06 ray starts from outside the cylinder and doesn't cross the cylinder
        result = cylinder.findIntersections(new Ray(new Point3D(5,0,0), new Vector(1,0,0)));
        assertNull(result, "ERROR - TC06: the number of points is incorrect");

        //TC07 ray starts from outside and crosses base and surface
        result = cylinder.findIntersections(new Ray(new Point3D(1,0,-1), new Vector(1,0,1)));
        assertEquals(2,result.size(), "ERROR - TC07: the number of points is incorrect");
        assertEquals(List.of(new Point3D(3,0,1), new Point3D(2,0,0)), result, "ERROR - TC07: the intersection point is not goods");

        //TC08 ray starts from outside and crosses surface and base
        result = cylinder.findIntersections(new Ray(new Point3D(4,0,2), new Vector(-1,0,-1)));
        assertEquals(2,result.size(), "ERROR - TC08: the number of points is incorrect");
        assertEquals(List.of(new Point3D(3,0,1), new Point3D(2,0,0)), result, "ERROR - TC08: the intersection point is not goods");


        // =============== Boundary Values Tests ==================

        //TC09 ray is on the surface of the cylinder (not bases)
        result = cylinder.findIntersections(new Ray(new Point3D(3,0,0), new Vector(0,0,1)));
        assertNull(result, "ERROR - TC09: the number of points is incorrect");

        //TC10 ray is on the base of the cylinder and crosses 2 times
        result = cylinder.findIntersections(new Ray(new Point3D(-1,0,0), new Vector(1,0,0)));
        assertNull(result, "ERROR - TC10: the number of points is incorrect");

        //TC11 ray is in center of the cylinder
        result = cylinder.findIntersections(new Ray(new Point3D(2,0,0), new Vector(0,0,1)));
        assertEquals(1, result.size(), "ERROR - TC11: the number of points is incorrect");
        assertEquals(List.of(new Point3D(2,0,2)), result, "ERROR - TC11: the intersection point is not goods");

        //TC12 ray is perpendicular to cylinder's ray and starts from outside the tube
        result = cylinder.findIntersections(new Ray(new Point3D(-2,0,0.5), new Vector(1,0,0)));
        assertEquals(2, result.size(), "ERROR - TC12: the number of points is incorrect");
        assertEquals(List.of(new Point3D(3,0,0.5), new Point3D(1,0,0.5)), result, "ERROR - TC12: the intersection point is not goods");

        //TC13 ray is perpendicular to cylinder's ray and starts from inside cylinder (not center)
        result = cylinder.findIntersections(new Ray(new Point3D(1.5,0,0.5), new Vector(1,0,0)));
        assertEquals(1, result.size(), "ERROR - TC13: the number of points is incorrect");
        assertEquals(List.of(new Point3D(3,0,0.5)), result, "ERROR - TC13: the intersection point is not goods");

        //TC14 ray is perpendicular to cylinder's ray and starts from the center of cylinder
        result = cylinder.findIntersections(new Ray(new Point3D(2,0,0.5), new Vector(1,0,0)));
        assertEquals(1, result.size(), "ERROR - TC14: the number of points is incorrect");
        assertEquals(List.of(new Point3D(3,0,0.5)), result, "ERROR - TC14: the intersection point is not goods");

        //TC15 ray is perpendicular to cylinder's ray and starts from the surface of cylinder to inside
        result = cylinder.findIntersections(new Ray(new Point3D(1,0,0.5), new Vector(1,0,0)));
        assertEquals(1, result.size(), "ERROR - TC15: the number of points is incorrect");
        assertEquals(List.of(new Point3D(3,0,0.5)), result, "ERROR - TC15: the intersection point is not goods");

        //TC16 ray is perpendicular to cylinder's ray and starts from the surface of cylinder to outside
        result = cylinder.findIntersections(new Ray(new Point3D(3,0,0), new Vector(1,0,0)));
        assertNull(result, "ERROR - TC16: the number of points is incorrect");

        //TC17 ray starts from the surface to outside
        result = cylinder.findIntersections(new Ray(new Point3D(3,0,0), new Vector(1,1,1)));
        assertNull(result, "ERROR - TC17: the number of points is incorrect");

        //TC18 ray starts from the surface to inside
        result = cylinder.findIntersections(new Ray(new Point3D(3,0,0.5), new Vector(-1,0,0)));
        assertEquals(1, result.size(), "ERROR - TC18: the number of points is incorrect");
        assertEquals(List.of(new Point3D(1,0,0.5)), result, "ERROR - TC18: the intersection point is not good");

        //TC19 ray starts from the center
        result = cylinder.findIntersections(new Ray(new Point3D(2,0,0), new Vector(1,0,1)));
        assertEquals(1, result.size(), "ERROR - TC19: the number of points is incorrect");
        assertEquals(List.of(new Point3D(3,0,1)), result, "ERROR - TC19: the intersection point is not good");

        //TC20 prolongation of ray crosses cylinder
        result = cylinder.findIntersections(new Ray(new Point3D(3,0,0), new Vector(1,0,0)));
        assertNull(result, "ERROR - TC20: the number of points is incorrect");

        //TC21 ray is on the surface starts before cylinder
        result = cylinder.findIntersections(new Ray(new Point3D(3,0,-1), new Vector(0,0,1)));
        assertNull(result, "ERROR - TC21: the number of points is incorrect");

        //TC22 ray is on the surface starts at bottom's base
        result = cylinder.findIntersections(new Ray(new Point3D(3,0,0), new Vector(0,0,1)));
        assertNull(result, "ERROR - TC22: the number of points is incorrect");

        //TC23 ray is on the surface starts on the surface
        result = cylinder.findIntersections(new Ray(new Point3D(3,0,1), new Vector(0,0,1)));
        assertNull(result, "ERROR - TC23: the number of points is incorrect");

        //TC24 ray is on the surface starts at top's base
        result = cylinder.findIntersections(new Ray(new Point3D(3,0,2), new Vector(0,0,1)));
        assertNull(result, "ERROR - TC24: the number of points is incorrect");
    }
}