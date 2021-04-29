package geometries;

import org.junit.jupiter.api.Test;
import primitives.*;

import static org.junit.jupiter.api.Assertions.*;




class GeometriesTest {

        /**
         * Test method for
         * {@link geometries.Geometries#findIntersections(primitives.Ray)}.
         */
        @Test
        public void testFindIntersections() {
            Geometries g = new Geometries();

            // =============== Boundary Values Tests ==================

            // TC01 empty list (0 point)
            assertNull( g.findIntersections(null),"Empty list");

            Point3D e = new Point3D(1, 0, 2);
            Plane pln = new Plane(e, new Vector(3, 3, 0));
            Sphere sphere = new Sphere(new Point3D(1, 0, 0), 1d);
            Triangle t = new Triangle(e, new Point3D(-3, -1, 2), new Point3D(-2, 3, 2));
            g.add(pln, sphere, t);

            // ============ Equivalence Partitions Tests ==============

            Ray r = new Ray(new Point3D(0.5,0.5,3.2), new Vector(2.5,-4.5,-3.2));

            //TC02 There are no intersections with any shape(0 point)
            assertNull( g.findIntersections(r),"There are no intersections with any shape");

            Point3D p = new Point3D(-2, 0, 3.2);
            r = new Ray(p, new Vector(-1, -1, -4.2));

            //TC03 There are intersection with one shape(1 point)
            assertEquals(1 ,g.findIntersections(r).size(),"There are intersection with one shape");

            r = new Ray(new Point3D(2, 3, 3.2), new Vector(-0.2, -4 , -4.2));
            // TC04 There are intersections with some shapes(3 point)
            assertEquals( 3 , g.findIntersections(r).size(),"There are intersections with same shapes");

            r = new Ray(p, new Vector(3.8, -1 , -4.2));
            // TC05 There are intersections with all the shapes(4 point)
            assertEquals( 4 , g.findIntersections(r).size(),"There are intersections with all the shapes");

        }



    }

