package primitives;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static primitives.Util.isZero;

/**
 * Unit tests for primitives.Vector class
 *
 * @author Israel Bellaiche and Jeremie Nabet
 */
class VectorTest {
    final Vector v1 = new Vector(1, 2, 3);
    final Vector v2 = new Vector(-2, -4, -6);

    /**
     * Test method for {@link primitives.Vector#add(primitives.Vector)}.
     */
    @Test
    void add() {
        // ============ Equivalence Partitions Tests ==============
        Vector v1 = new Vector(1.0, 1.0, 1.0);
        Vector v2 = new Vector(-1.0, -1.0, -1.5);
        //test add function
        v1 = v1.add(v2);
        assertEquals(new Vector(0.0, 0.0, -0.5), v1);
        v2 = v2.add(v1);
        assertEquals(new Vector(-1.0, -1.0, -2.0), v2);
    }

    /**
     * Test method for {@link primitives.Vector#subtract(primitives.Vector)}.
     */
    @Test
    void subtract() {
        // ============ Equivalence Partitions Tests ==============

        Vector v1 = new Vector(1.0, 1.0, 1.0);
        Vector v2 = new Vector(-1.0, -1.0, -1.5);
        //test subtract function
        v1 = v1.subtract(v2);
        assertEquals(new Vector(2.0, 2.0, 2.5), v1);
        v2 = v2.subtract(v1);
        assertEquals(new Vector(-3.0, -3.0, -4.0), v2);
    }

    /**
     * Test method for {@link primitives.Vector#scale(double)}.
     */
    @Test
    void scale() {
        // ============ Equivalence Partitions Tests ==============

        Vector v = new Vector(1.0, 1.0, 1.0);
        int num = 2;
        //test scale function
        v = v.scale(num);
        assertEquals(new Vector(2.0, 2.0, 2.0), v);
    }

    /**
     * Test method for {@link primitives.Vector#dotProduct(primitives.Vector)}.
     */
    @Test
    void dotProduct() {
        // ============ Equivalence Partitions Tests ==============

        Vector v1 = new Vector(1, 2, 3);
        Vector v2 = new Vector(-2, -4, -6);
        //test dotProduct function
        assertEquals(-28.0, v1.dotProduct(v2), "dotProduct() wrong result");
    }

    /**
     * Test method for {@link primitives.Vector#crossProduct(primitives.Vector)}.
     */
    @Test
    public void testCrossProduct() {


        // ============ Equivalence Partitions Tests ==============

        Vector v3 = new Vector(0, 3, -2);
        Vector vr = v1.crossProduct(v3);

        // Test that length of cross-product is proper (orthogonal vectors taken for simplicity)
        assertEquals(v1.length() * v3.length(), vr.length(), 0.00001, "crossProduct() wrong result length");

        // Test cross-product result orthogonality to its operands
        assertTrue(isZero(vr.dotProduct(v1)), "crossProduct() result is not orthogonal to 1st operand");
        assertTrue(isZero(vr.dotProduct(v3)), "crossProduct() result is not orthogonal to 2nd operand");

        // =============== Boundary Values Tests ==================
        // test zero vector from cross-productof co-lined vectors
        try {
            v1.crossProduct(v2);
            fail("crossProduct() for parallel vectors does not throw an exception");
        } catch (Exception e) {
        }
    }

    /**
     * Test method for {@link Vector#lengthSquared()}.
     */
    @Test
    void lengthSquared() {
        // ============ Equivalence Partitions Tests ==============

        Vector v1 = new Vector(1, 2, 3);
        assertEquals(v1.lengthSquared(), 14.0, 0.00001, "lengthSquared() wrong result ");
    }

    /**
     * Test method for {@link Vector#length()}.
     */
    @Test
    void length() {
        // ============ Equivalence Partitions Tests ==============

        Vector v1 = new Vector(1, 2, 2);
        assertEquals(v1.length(), 3.0, 0.00001, "length() wrong result ");
    }

    /**
     * Test method for {@link Vector#normalize()}.
     */
    @Test
    void normalize() {
        // ============ Equivalence Partitions Tests ==============

        Vector v1 = new Vector(1, 2, 2);
        v1.normalize();
        assertEquals(v1, new Vector(1d / 3, 2d / 3, 2d / 3), "normalize() wrong result ");
    }

    /**
     * Test method for {@link Vector#normalized()}.
     */
    @Test
    void normalized() {
        // ============ Equivalence Partitions Tests ==============

        Vector v1 = new Vector(1, 2, 2);
        assertEquals(v1.normalized(), new Vector(1d / 3, 2d / 3, 2d / 3), "normalized() wrong result ");
    }
}