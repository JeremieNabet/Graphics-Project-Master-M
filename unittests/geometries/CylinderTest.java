package geometries;

import org.junit.jupiter.api.Test;
import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;


import static org.junit.jupiter.api.Assertions.*;

/**
 * unittest for cylinder class.
 *
 * @author israel bellaiche and jeremie nabet
 */
class CylinderTest {

    /**
     * Test method for {@link geometries.Cylinder#getNormal(Point3D)}.
     */
    @Test
    public void testGetNormal() {
        // ============ Equivalence Partitions Tests ==============

        // TC01: point on the top of the cylinder
        Cylinder cylinder = new Cylinder(new Ray(new Point3D(0, 0, 0), new Vector(0, 0, 1)),
                1d,
                1d);
        assertEquals(new Vector(0, 0, 1d),
                cylinder.getNormal(new Point3D(0, 0.5, 1)),
                "ERROR - TC01: bad normal to cylinder");
        // TC02: point on the base of the cylinder
        assertEquals(new Vector(0, 0, 1d),
                cylinder.getNormal(new Point3D(0, 0.5, 0)),
                "ERROR - TC02: bad normal to cylinder");
        // TC03: point on the surface of the cylinder
        assertEquals(new Vector(0, 1d, 0),
                cylinder.getNormal(new Point3D(0, 0.5, 0.5)),
                "ERROR - TC03: bad normal to cylinder");

        // =============== Boundary Values Tests ==================
        // TC04: between surface and base of cylinder
        assertNotEquals(cylinder.getNormal(new Point3D(0, 1, 0.00001)),
                cylinder.getNormal(new Point3D(0, 0.99999999, 0)),
                "ERROR - TC04: boundary test base and surface");
        // TC05: between surface and top of cylinder
        assertNotEquals(cylinder.getNormal(new Point3D(0, 1, 0.99999)),
                cylinder.getNormal(new Point3D(0, 0.99999999, 1)),
                "ERROR - TC05: boundary test top and surface");
    }

}