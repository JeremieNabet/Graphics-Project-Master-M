package geometries;

import primitives.*;

import java.awt.*;

import static primitives.Util.alignZero;
import static primitives.Util.isZero;

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
     * Constructor of Plane from 3 points on its surface
     * the points are ordered from right to left
     * forming on arc in right direction
     *
     * @param point1
     * @param point2
     * @param point3
     */
    //constructor 1
    public Plane(Point3D point1, Point3D point2, Point3D point3) {
        this.point = point1;
        Vector v1 = point2.subtract(point1); //vector from point1 towards point2
        Vector v2 = point3.subtract(point1); //vector from point1 towards point3
        Vector n = v1.crossProduct(v2);
        this.normal = n.normalize();
    }

    /**
     * Constructor 2
     *
     * @param p
     * @param normal vector for the normal(will be normalize automatically)
     */
    public Plane(Point3D p, Vector normal) {
        this.point = p;
        this.normal = normal.normalized();
    }

    /**
     * getNormal from implementation of Geometry interface
     *
     * @param p dummy point not use for flat geometries
     *          should be assigned null value
     * @return normal to the plane
     */
    @Override
    public Vector getNormal(Point3D p) {

        return normal;
    }

    /**
     * getter of normal field
     *
     * @return reference to normal vector to the plane
     */
    public Vector getNormal() {

        return normal;
    }

    /**
     * getter of point field
     *
     * @return Point3D point (reference) to the plane
     */
    public Point3D getPoint() {
        return point;
    }

    @Override
    public java.util.List<Point3D> findIntersections(Ray ray) {
        Point3D P0 = ray.getP0();
        Vector v = ray.getDir();
        Vector n = normal;

        if (point.equals(P0)) {
            return null;
        }

        Vector P0_Q0 = point.subtract(P0);
        double denominator = alignZero(normal.dotProduct(P0_Q0));

        if (isZero(denominator)) {
            return null;
        }

        //monet
        double nv = alignZero(n.dotProduct(v));

        // ray is lying in the plane axis
        if (isZero(nv)) {
            return null;
        }

        double t = alignZero(denominator/nv);
        Point3D P = P0.add(v.scale(t));
        return java.util.List.of(P);
    }
}
