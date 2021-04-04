package primitives;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Point3DTest {

    /**
     * Test method for{@link primitives.Point3D#subtract(primitives.Point3D)}
     */
    @Test
    void testSubtract() {
        
        Point3D p1 = new Point3D(1,2,3);
        Vector result = new Point3D(2,3,4).subtract(p1);
        assertEquals(new Vector(1,1,1), result, "ERROR: Point - Point does not work correctly");
    }
}