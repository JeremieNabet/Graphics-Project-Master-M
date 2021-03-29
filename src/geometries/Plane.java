package geometries;

import primitives.*;

import java.awt.*;

public class Plane implements Geometry {
    /**
     * the plane is composed of a point and of vector
     *
     */
    Point3D point;
    /**
     * the vector normal of the plane
     */
    Vector normal;

    //constructor 1
    public Plane(Point3D point1, Point3D point2, Point3D point3) {
        this.point = point1;
        this.normal = null;
    }

    // constructor 2
    public Plane(Point3D p, Vector normal) {
        this.point = p;
        this.normal = normal.normalized();
    }

    public Vector getNormal(Point3D p) {
        return normal;
    }

    public Vector getNormal() {
        return normal;
    }
}
