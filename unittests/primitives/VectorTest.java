package primitives;

import org.junit.Test;

import static org.junit.Assert.*;
import static primitives.Util.*;

public class VectorTest {

    @Test
    public void testAdd() {
    }

    @Test
    public void testSubtract() {
    }

    @Test
    public void testCrossProduct() {
        Vector v1 = new Vector(1, 2, 3);

        // ============ Equivalence Partitions Tests ==============
        Vector v2 = new Vector(0, 3, -2);
        Vector vr = v1.crossProduct(v2);

        // TC01: Test that length of cross-product is proper (orthogonal vectors taken
        // for simplicity)
        assertEquals("crossProduct() wrong result length", v1.length() * v2.length(), vr.length(), 0.00001);

        // TC02: Test cross-product result orthogonality to its operands
        assertTrue("crossProduct() result is not orthogonal to 1st operand", isZero(vr.dotProduct(v1)));
        assertTrue("crossProduct() result is not orthogonal to 2nd operand", isZero(vr.dotProduct(v2)));

        // =============== Boundary Values Tests ==================
        // TC11: test zero vector from cross-productof co-lined vectors
        Vector v3 = new Vector(-2, -4, -6);
        assertThrows("crossProduct() for parallel vectors does not throw an exception",
                IllegalArgumentException.class, () -> v1.crossProduct(v3));
        // try {
        //     v1.crossProduct(v2);
        //     fail("crossProduct() for parallel vectors does not throw an exception");
        // } catch (Exception e) {}

    }

    @Test
    public void testDotProduct() {

    }

    @Test
    public void testLengthSquared() {
    }

    @Test
    public void testLength() {
    }

    @Test
    public void testNormalize() {
    }

    @Test
    public void testScale() {
    }

    @Test
    public void testNormalized() {
    }
}