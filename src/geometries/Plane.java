package geometries;

import primitives.*;
import java.util.List;
import static primitives.Util.*;

public class Plane extends Geometry {
    /**
     * the plane is composed of a point and of vector
     */
    private final Point3D point;
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
     * @param point1 first point of my plane
     * @param point2 second point of my plane
     * @param point3 third point of my plane
     * @throws IllegalArgumentException if the points are on the same line
     */
    //constructor 1
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
     * getNormal from implementation of Geometry interface
     * the same at any point in the plane
     * @param p dummy point not use for flat geometries
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

    /**
     * allows me to find the intersection points of my Plane
     * if the points are not found, the function returns null
     *
     * @param ray the ray to check the intersection
     * @return list of intersection points
     */
    @Override
    public List<Point3D> findIntersections(Ray ray) {
        Point3D p0 = ray.getP0();
        Vector v = ray.getDirection();

        Vector p0MinusQ0;
        try {
            p0MinusQ0= point.subtract(p0);
        } catch (IllegalArgumentException e) {
            return null;
        }
        double denominator = normal.dotProduct(p0MinusQ0);
        if (isZero(denominator)) return null;

        //monet
        double nV = normal.dotProduct(v);
        // ray is lying in the plane axis
        if (isZero(nV))
            return null;

        double t = alignZero(denominator/nV);
        return t <= 0 ? null : List.of(ray.getPoint(t));
    }

    @Override
    public List<GeoPoint> findGeoIntersections(Ray ray) {
        return null;
    }

    @Override
    public String toString() {
        return super.toString() + " {" +
                ", Q=" + point +
                ", n=" + normal +
                '}';
    }
}
