package primitives;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import static org.junit.jupiter.api.Assertions.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import static primitives.Util.isZero;
import static org.junit.Assert.fail;

/**
 * Test method for {@link primitives.Vector#crossProduct(primitives.Vector)}.
 */


class VectorTest {

    Vector v1 = new Vector(1, 2, 3);
    Vector v2 = new Vector(-2, -4, -6);
    Vector v3 = new Vector(0, 3, -2);

    @Test
    public void testCtorVector() {

    }

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

    @Test
    void testSubtract() {
        Vector v1 = new Vector(1, 2, 3);
        Vector v2 = new Vector(-2, -4, -6);
        // ============ Equivalence Partitions Tests ==============
        Vector v3 = new Vector(0, 3, -2);
        Vector vr = v1.subtract(v3);
        // =============== Boundary Values Tests =================


        try {/** verrifie about vector ZERO
         */
            Vector vector = new Vector(0, 0, 0);
            Vector temp = v1.subtract(vector);
            fail("exception for vector 0 dosnt works");
        } catch (Exception e) {
        }
    }

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
        // try {
        //     v1.crossProduct(v2);
        //     fail("crossProduct() for parallel vectors does not throw an exception");
        // } catch (Exception e) {}

    }

    @Test
    void testDotProduct() {
        if (!isZero(v1.dotProduct(v3)))
            fail("ERROR: dotProduct() for orthogonal vectors is not zero");
        if (!isZero(v1.dotProduct(v2) + 28))
            fail("ERROR: dotProduct() wrong value");
    }

    @Test
    void testLengthSquared() {
        if (!isZero(v1.lengthSquared() - 14))
            fail("ERROR: wrong value");

    }

    @Test
    void testLength() {
        if (!isZero(new Vector(0, 3, 4).length() - 5))
            fail("ERROR: length() wrong value");

    }

    @Test
    void testNormalize() {

        Vector v = new Vector(3.5, -5, 10);
        v.normalize();
        assertEquals(1, v.length(), 1e-10);

        assertThrows(IllegalArgumentException.class,
                () -> newVectorZero(v),
                "cannot be head (0,0,0)");

    }

    private Executable newVectorZero(Vector v) {
        v = new Vector(0, 0, 0);
        return null;
    }

    @Test
    void testScale() {

        double scale = 12;
        Vector vector = new Vector(1, 2, 3);

        // ============ Equivalence Partitions Tests ==============

        vector = vector.scale(scale);
        double lenght = vector.length();
        System.out.println("the length of the new vector is :");
        System.out.println(lenght);

        // =============== Boundary Values Tests ==================

        try {
            vector.scale(0);
            fail("exception for vector 0 doesn't works");
        } catch (Exception e) {
        }
    }

    @Test
    void testNormalized() {

        Vector v = new Vector(1, 2, 3);
        Vector vCopy = new Vector(v.head);
        Vector u = v.normalized();
        assertFalse("ERROR: normalizated() function does not create a new vector", u == v);


    }
}