package geometries;

import primitives.*;

import java.awt.*;

public class Plane implements Geometry {
    /**
     * the plane is composed of a point and of vector
     */
    final Point3D point;
    /**
     * the vector normal of the plane
     */
    final Vector normal;

    /**
     * Constructor 1
     * Point in Plane
     *
     * @param point1
     * @param point2
     * @param point3
     */
    //constructor 1
    public Plane(Point3D point1, Point3D point2, Point3D point3) {
        this.point = point1;
        Vector v1 = point2.subtract(point1); //vector from point1 towards point2
        Vector v2 = point3.subtract(point1); //vector from point1 towards point2
        Vector n = v1.crossProduct(v2);
        this.normal = n.normalize();
    }

    /**
     * Constructor 2
     *
     * @param p
     * @param normal
     */
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
