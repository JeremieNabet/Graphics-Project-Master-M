package elements;

import geometries.*;
import org.junit.jupiter.api.Test;
import primitives.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Class that tests integration of rays coming out of a camera
 * and their intersection with the view-plane and the geometric shapes
 */
public class CameraRayIntersectionsIntegrationTests {
    /**
     * The function create the camera location, the view plan data, and location and geometric body size of each test
     *
     * @param camera          camera for testing
     * @param geo             interface that will contain the geometric shapes
     * @param expected        value we expect to receive as a result
     * @param outputException string printed in case the test fails
     */
    private void assertCountIntersections(Camera camera, Intersectable geo, int expected, String outputException) {
        int count = 0;

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                var intersections = geo.findIntersections(camera.constructRayThroughPixel(3, 3, j, i));
                count += intersections == null ? 0 : intersections.size();
            }
        }
        assertEquals(expected, count, outputException);
    }

    /**
     * Integration tests of Camera Ray construction with Ray-Sphere Intersections
     */
    @Test
    public void cameraRaySphereIntegration() {
        Camera.CameraBuilder cameraBuilder1 = new Camera.CameraBuilder(Point3D.PointZERO,
                new Vector(0, 0, -1),
                new Vector(0, -1, 0))
                .setViewPlaneSize(3, 3)
                .setDistance(1);
        Camera cam1 = cameraBuilder1.build();
        Camera.CameraBuilder cameraBuilder2 =
                new Camera.CameraBuilder(
                        new Point3D(0, 0, 0.5),
                        new Vector(0, 0, -1),
                        new Vector(0, -1, 0))
                        .setViewPlaneSize(3, 3)
                        .setDistance(1);
        Camera cam2 = cameraBuilder2.build();

        //TC01: Small sphere (2 points)
        assertCountIntersections(
                cam1,
                new Sphere(new Point3D(0, 0, -3), 1),
                2,
                "ERROR - TC01: small sphere (2 points)");

        //TC02: Big sphere (18 points)
        assertCountIntersections(
                cam2,
                new Sphere(new Point3D(0, 0, -100), 90),
                18,
                "ERROR - TC02: big sphere (18 points)");

        //TC03: Medium sphere (10 points)
        assertCountIntersections(
                cam2,
                new Sphere(new Point3D(0, 0, -2), 2),
                10,
                "ERROR - TC03: medium sphere (10 points)");

        //TC04: Inside sphere (9 points)
        assertCountIntersections(
                cam2,
                new Sphere(new Point3D(0, 0, -1), 4),
                9,
                "ERROR - TC04: inside sphere (9 points)");

        //TC05: Beyond Sphere (no points)
        assertCountIntersections(
                cam1,
                new Sphere(new Point3D(0, 0, 1), 0.5),
                0,
                "ERROR - TC05: beyond Sphere (no points)");
    }

    /**
     * Integration tests of Camera Ray construction with Ray-Plane Intersections
     */
    @Test
    public void cameraRayPlaneIntegration() {
        Camera.CameraBuilder cameraBuilder =
                new Camera.CameraBuilder(
                        Point3D.PointZERO,
                        new Vector(0, 0, -1),
                        new Vector(0, -1, 0))
                        .setViewPlaneSize(3, 3)
                        .setDistance(1);
        Camera cam = cameraBuilder.build();
        //TC01: Plane against camera (9 points)
        assertCountIntersections(
                cam,
                new Plane(new Point3D(0, 0, -5), new Vector(0, 0, 1)),
                9,
                "ERROR - TC01: plane against camera (9 points)");

        //TC02: Plane with small angle (9 points)
        assertCountIntersections(
                cam,
                new Plane(new Point3D(0, 0, -5), new Vector(0, 1, 2)),
                9,
                "ERROR - TC02: plane with small angle (9 points)");

        //TC03: Plane parallel to lower rays (6 points)
        assertCountIntersections(
                cam,
                new Plane(new Point3D(0, 0, -5), new Vector(0, 1, 1)),
                6,
                "ERROR - TC03: plane parallel to lower rays (6 points)");

        //TC04: Beyond plane (no points)
        assertCountIntersections(
                cam,
                new Plane(new Point3D(0, 0, 5), new Vector(0, 1, 1)),
                0,
                "ERROR - TC04: Beyond plane (no points)");
    }

    /**
     * Integration tests of Camera Ray construction with Ray-Triangle Intersections
     */
    @Test
    public void cameraRayTriangleIntegration() {
        Camera.CameraBuilder cameraBuilder =
                new Camera.CameraBuilder(
                        Point3D.PointZERO,
                        new Vector(0, 0, -1),
                        new Vector(0, -1, 0))
                        .setViewPlaneSize(3, 3)
                        .setDistance(1);
        Camera cam = cameraBuilder.build();
        //TC01: small triangle (1 point)
        assertCountIntersections(
                cam,
                new Triangle(new Point3D(1, 1, -2), new Point3D(-1, 1, -2), new Point3D(0, -1, -2)),
                1,
                "ERROR - TC01: small triangle (1 point)");

        //TC02: medium triangle (2 points)
        assertCountIntersections(
                cam,
                new Triangle(new Point3D(1, 1, -2), new Point3D(-1, 1, -2), new Point3D(0, -20, -2)),
                2,
                "ERROR - TC02: medium triangle (2 points)");
    }
}