package geometries;

import primitives.*;

import java.util.List;

import static primitives.Util.*;

public class Sphere extends Geometry {
    /**
     * the point center of the sphere
     */
    final Point3D center;
    /**
     * the radius of the sphere
     */
    final double radius;
    /**
     * the radius in square
     */
    final double radiusSqr;

    /**
     * Constructor sphere that need point center and radius (double)
     * @param center center of my sphere
     * @param radius radius of my sphere
     */
    public Sphere(Point3D center, double radius) {
        this.center = center;
        this.radius = radius;
        this.radiusSqr = radius * radius;
    }

    /**
     * this function give the center point of the sphere
     *
     * @return the center of my sphere
     */
    public Point3D getCenter() {
        return center;
    }

    /**
     * this function give the radius of the sphere
     *
     * @return the radius of my sphere
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
        Point3D p0 = ray.getP0();

        Vector u;
        try {
            u = center.subtract(p0);
        } catch (IllegalArgumentException e) {
            return List.of(ray.getPoint(radius));
        }

        Vector v = ray.getDirection();
        double tM = alignZero(v.dotProduct(u));

        double dSqr = u.lengthSquared() - tM * tM;
        double thSqr = alignZero(radiusSqr - dSqr);
        if (thSqr <= 0)
            return null; //because there is not intersections : the ray direction is tangent or out of the sphere

        double tH = Math.sqrt(thSqr);
        double t2 = alignZero(tM + tH);
        // t1 < t2 ALWAYS
        if (t2 <= 0) return null; // both t1 and t2 are <=0

        double t1 = alignZero(tM - tH);
        return t1 <= 0 ? List.of(ray.getPoint(t2)) : List.of(ray.getPoint(t1), ray.getPoint(t2));
    }

    @Override
    public List<GeoPoint> findGeoIntersections(Ray ray) {
        return null;
    }

    /**
     * function to string that give me the value type about my class
     *
     * @return value string about this class
     */
    @Override
    public String toString() {
        return super.toString() + " {" + "o=" + center + ", r=" + radius + '}';
    }
}
