package geometries;

import primitives.*;

public class Tube {
    protected Ray axisRay;
    protected double radius;

    public Tube(Ray ray, double radius) {
        this.axisRay = ray;
        this.radius = radius;
    }

    public Vector getNormal(Point3D point) {
        return null;
    }
}
