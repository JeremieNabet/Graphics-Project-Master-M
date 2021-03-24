package geometries;

import primitives.*;

public class Sphere implements Geometry {
    private Point3D center;
    private double radius;

    public Sphere(Point3D center, double radius) {
        this.center = center;
        this.radius = radius;
    }

    public Point3D getCenter() {
        return center;
    }

    public double getRadius() {
        return radius;
    }

    public Vector getNormal(Point3D point3D) {
        return null;
    }
}
