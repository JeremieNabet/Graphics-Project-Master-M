package primitives;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import static primitives.Util.isZero;


/**
 * Testing Vector
 *
 * @author Jeremie and Israel
 */

class VectorTest {

    Vector v1 = new Vector(1, 2, 3);
    Vector v2 = new Vector(-2, -4, -6);
    Vector v3 = new Vector(0, 3, -2);

    /**
     * Test method for {@link primitives.Vector#add(Vector)}.
     */

    @Test
    void testAdd() {
        Vector v1 = new Vector(1, 2, 3);
        Vector v2 = new Vector(-2, -4, -6);
        // ============ Equivalence Partitions Tests ==============
        Vector v3 = new Vector(0, 3, -2);
        Vector vr = v1.add(v3);
        // =============== Boundary Values Tests =================

        try { //vector Zero
            Vector vector = new Vector(0, 0, 0);
            Vector temp = v1.add(vector);
            fail("salut");
        } catch (Exception e) {
        }
    }

    /**
     * Test method for {@link primitives.Vector#subtract(Vector)}.
     */
    @Test
    void testSubtract() {
        Vector v1 = new Vector(1, 2, 3);
        Vector v2 = new Vector(-2, -4, -6);
        // ============ Equivalence Partitions Tests ==============
        Vector v3 = new Vector(0, 3, -2);
        Vector vr = v1.subtract(v3);
        // TODO missing test

        // =============== Boundary Values Tests =================
        try {/** verrifie about vector ZERO
         */
            Vector vector = new Vector(0, 0, 0);
            Vector temp = v1.subtract(vector);
            fail("exception for vector 0 dosnt works");
        } catch (Exception e) {
        }
    }

    /**
     * Test method for {@link primitives.Vector#crossProduct(Vector)}.
     */

    @Test
    void testCrossProduct() {
        Vector v1 = new Vector(1, 2, 3);

        // ============ Equivalence Partitions Tests ==============
        Vector v2 = new Vector(0, 3, -2);
        Vector vr = v1.crossProduct(v2);

        // TC01: Test that length of cross-product is proper (orthogonal vectors taken
        // for simplicity)
        assertEquals(v1.length() * v2.length(), vr.length(), 0.00001, "crossProduct() wrong result length");

        // TC02: Test cross-product result orthogonality to its operands
        assertTrue(isZero(vr.dotProduct(v1)), "crossProduct() result is not orthogonal to 1st operand");
        assertTrue(isZero(vr.dotProduct(v2)), "crossProduct() result is not orthogonal to 2nd operand");

        // =============== Boundary Values Tests ==================
        // TC11: test zero vector from cross-productof co-lined vectors
        Vector v3 = new Vector(-2, -4, -6);
        assertThrows(
                IllegalArgumentException.class, () -> v1.crossProduct(v3),
                "crossProduct() for parallel vectors does not throw an exception");
    }

    /**
     * Test method for {@link primitives.Vector#dotProduct(Vector)}.
     */
    @Test
    void testDotProduct() {
        if (!isZero(v1.dotProduct(v3)))
            fail("ERROR: dotProduct() for orthogonal vectors is not zero");
        if (!isZero(v1.dotProduct(v2) + 28))
            fail("ERROR: dotProduct() wrong value");
    }

    /**
     * Test method for {@link Vector#lengthSquared()}.
     */
    @Test
    void testLengthSquared() {
        if (!isZero(v1.lengthSquared() - 14))
            fail("ERROR: wrong value");
    }

    /**
     * Test method for {@link Vector#length()}.
     */
    @Test
    void testLength() {
        assertEquals(5.0, new Vector(0, 3, 4).length(), 0.00001, "ERROR: length() wrong value");
    }

    /**
     * Test method for {@link Vector#normalize()}.
     */
    @Test
    void testNormalize() {
        Vector v = new Vector(3.5, -5, 10);
        Vector vCopy = new Vector(v.getHead());
        Vector u = v.normalize();
        // Check that no new vector is generated!
        assertSame(u, v);
        // Check the result is a unit vector
        assertEquals(1.0, u.length(), 0.00001, "not length 1");
        // check the result is co-lined with the original vector
        assertThrows(IllegalArgumentException.class, () -> u.crossProduct(vCopy), "not co-directed");
        // check the result is co-directed with the original vector
        assertTrue(u.dotProduct(vCopy) > 0);

        // TODO move to function testing the constructor!
        assertThrows(IllegalArgumentException.class,
                () -> new Vector(0, 0, 0),
                "cannot be head (0,0,0)");
    }

    /**
     * Test method for {@link primitives.Vector#scale(double)}.
     */
    @Test
    void testScale() {
        double scale = 12;
        Vector vector = new Vector(1, 2, 3);

        // ============ Equivalence Partitions Tests ==============

        vector = vector.scale(scale);

        // =============== Boundary Values Tests ==================

        try {
            vector.scale(0);
            fail("exception for vector 0 doesn't works");
        } catch (Exception e) {
        }
    }

    /**
     * Test method for {@link Vector#normalized()}.
     */
    @Test
    void testNormalized() {
        Vector v = new Vector(1, 2, 3);
        Vector u = v.normalized();
        // Check that new vector is generated!
        assertNotSame(u, v);
        // Check the result is a unit vector
        assertEquals(1.0, u.length(), 0.00001, "not length 1");
        // check the result is co-lined with the original vector
        assertThrows(IllegalArgumentException.class, () -> u.crossProduct(v), "not co-directed");
        // check the result is co-directed with the original vector
        assertTrue(u.dotProduct(v) > 0);
    }
}