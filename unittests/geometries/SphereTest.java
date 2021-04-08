package geometries;

import org.junit.jupiter.api.Test;
import primitives.Point3D;
import primitives.Vector;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;


class SphereTest {

    @Test
    void getNormal() {
        Sphere sp = new Sphere(new Point3D(0, 0, 1), 1);
        var normal = sp.getNormal(new Point3D(1, 0, 1));
        assertEquals(new Vector(1,0,0), normal);
    }
}