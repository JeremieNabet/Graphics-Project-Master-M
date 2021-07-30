package geometries;

import primitives.*;

import java.util.List;

import static primitives.Util.*;

/**
 * class definitions with it's fields - one for the point
 * and one for the vector.
 *
 * @author Jeremie Nabet and Israel Bellaiche
 */
public class Plane extends Geometry {
    /**
     * the plane is composed of a point and of vector
     */
    private final Point3D point;
    /**
     * the vector normal of the plane
     */
    private final Vector normal;

    /**
     * Constructor 1
     * Constructor of Plane from 3 points on its surface
     * the points are ordered from right to left
     * forming on arc in right direction
     *
     * @param point1 first point of my plane
     * @param point2 second point of my plane
     * @param point3 third point of my plane
     * @throws IllegalArgumentException if the points are on the same line
     */

    public Plane(Point3D point1, Point3D point2, Point3D point3) {
        this.point = point1;
        Vector v1 = point2.subtract(point1); //vector from point1 towards point2
        Vector v2 = point3.subtract(point1); //vector from point1 towards point3
        this.normal = v1.crossProduct(v2).normalize();
    }

    /**
     * Constructor 2
     * formed from p and the vector normal
     *
     * @param p
     * @param normal vector for the normal(will be normalize automatically)
     */
    public Plane(Point3D p, Vector normal) {
        this.point = p;
        this.normal = normal.normalized();
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
    public Vector getNormal(Point3D p) {
        return normal;
    }

    @Override
    public List<GeoPoint> findGeoIntersections(Ray ray, double maxDistance) {
        Point3D p0 = ray.getP0();
        Vector v = ray.getDirection();

        //ray is the starting point.
        if (point.equals(p0)) {
            return null;
        }

        double nv = alignZero(normal.dotProduct(v));

        //the ray is lying on the plane
        if (isZero(nv)) {
            return null;
        }

        double t = alignZero(normal.dotProduct(point.subtract(p0)));
        t = alignZero(t / nv);

        //t is on the exiting point on the plane or outside of it.
        if (t <= 0) {
            return null;
        }

        if (alignZero(t - maxDistance) <= 0) {
            Point3D p = ray.getPoint(t);
            return List.of(new GeoPoint(this, p));
        }
        return null;
    }

    @Override
    public String toString() {
        return super.toString() + " {" + ", Q=" + point + ", n=" + normal + '}';
    }
}
