package geometries;

import org.junit.jupiter.api.Test;

import primitives.*;

import static org.junit.Assert.assertEquals;
/**
 * Testing Triangle
 *
 * @author Jeremie and Israel
 *
 */

class TriangleTest {
    /**
     * Test method for {@link Triangle#getNormal(Point3D)}.
     */
    @Test
    void testGetNormal() {
        Triangle tr = new Triangle(new Point3D(0, 0, 1),
                new Point3D(1, 1, 1),
                new Point3D(1, 0, 1));
        assertEquals(tr.getNormal(null), new Vector(0d, 0d, -1d));

    }

}