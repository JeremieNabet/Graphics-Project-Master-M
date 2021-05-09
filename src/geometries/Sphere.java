package geometries;

import primitives.*;

import java.util.List;

import static primitives.Util.*;

public class Sphere implements Geometry {
    /**
     * the point center of the sphere
     */
    final Point3D center;
    /**
     * the radius of the sphere
     */
    final double radius;
    final double radiusSqr;

    public Sphere(Point3D center, double radius) {
        this.center = center;
        this.radius = radius;
        this.radiusSqr = radius * radius;
    }

    /**
     * this function give the center point of the sphere
     *
     * @return center
     */
    public Point3D getCenter() {
        return center;
    }

    /**
     * this function give the radius of the sphere
     *
     * @return radius
     */
    public double getRadius() {
        return radius;
    }

    /**
     * function which returns me the value of Sphere normalized
     *
     * @param point3D subtract center
     * @return the value normalized
     */
    @Override
    public Vector getNormal(Point3D point3D) {
        return point3D.subtract(center).normalize();
    }


    /**
     * allows me to find the intersection points of my sphere
     * if the points are not found, the function returns null
     *
     * @param ray the ray that enter in the excel table
     * @return list of intersection points
     */
    @Override
    public List<Point3D> findIntersections(Ray ray) {
        Point3D P0 = ray.getP0();

        Vector U;
        try {
            U = center.subtract(P0);
        } catch (IllegalArgumentException e) {
            return List.of(ray.getPoint(radius));
        }

        Vector v = ray.getDirection();
        double tm = alignZero(v.dotProduct(U));

        double dSqr = U.lengthSquared() - tm * tm;
        double thSqr = alignZero(radiusSqr - dSqr);
        if (thSqr <= 0)
            return null; //because there is not intersections : the ray direction is tangent or out of the sphere

        double th = Math.sqrt(thSqr);
        double t2 = alignZero(tm + th);
        // t1 < t2 ALWAYS
        if (t2 <= 0) return null; // both t1 and t2 are <=0

        double t1 = alignZero(tm - th);
        return t1 <= 0 ? List.of(ray.getPoint(t2)) : List.of(ray.getPoint(t1), ray.getPoint(t2));
    }

    /**
     * function to string that give me the value type about my class
     *
     * @return value string about this class
     */
    @Override
    public String toString() {
        return "Sphere{" + "center=" + center + ", radius=" + radius + '}';
    }
}
