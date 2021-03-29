package geometries;

import primitives.*;

public class Sphere implements Geometry {
    /**
     * the point center about the sphere
     */
    private Point3D center;
    /**
     * the radius about the sphere
     */
    private double radius;

    public Sphere(Point3D center, double radius) {
        this.center = center;
        this.radius = radius;
    }

    /**
     * this function give the center point about the sphere
     * @return center
     */
    public Point3D getCenter() {
        return center;
    }

    /**
     *
     * this function give the radius about the sphere
     * @return radius
     */
    public double getRadius() {
        return radius;
    }

    public Vector getNormal(Point3D point3D) {
        return null;
    }
}
