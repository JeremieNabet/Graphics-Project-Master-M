package primitives;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import static org.junit.jupiter.api.Assertions.*;
import static primitives.Util.isZero;

class VectorTest {

    @Test
    void testAdd() {
    }

    @Test
    void testSubtract() {
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
    }

    @Test
    void testLengthSquared() {
    }

    @Test
    void testLength() {
    }

    @Test
    void testNormalize() {

        Vector v = new Vector(3.5, -5, 10);
        v.normalize();
        assertEquals(1, v.length(), 1e-10);

        assertThrows(IllegalArgumentException.class,
                ()-> newVectorZero(v),
                "cannot be head (0,0,0)");

    }

    private Executable newVectorZero(Vector v){
        v = new Vector(0, 0, 0);
        return null;
    }
    /*
     *   Vector v = new Vector(1, 2, 3);
     *         Vector vCopy = new Vector(v.getHead());
     *         Vector vCopyNormalize = vCopy.normalize();
     *         assertEquals (vCopy, vCopyNormalize,"ERROR: normalize() function creates a new vector");
     *
     *         if (!isZero(vCopyNormalize.length() - 1))
     *             out.println("ERROR: normalize() result is not a unit vector");
     *         Vector u = v.normalized();
     *         if (u == v)
     *             out.println("ERROR: normalizated() function does not create a new vector");
     */
    @Test
    void testScale() {
    }

    @Test
    void testNormalized() {
    }
}