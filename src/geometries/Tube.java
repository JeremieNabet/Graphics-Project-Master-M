package geometries;

import primitives.*;

public class Tube {
    /**
     * the axis ray about the tube
     */
    protected Ray axisRay;
    /**
     * this radius about the tube
     */
    protected double radius;

    public Tube(Ray ray, double radius) {
        this.axisRay = ray;
        this.radius = radius;
    }

    public Vector getNormal(Point3D point) {
        return null;
    }
}
