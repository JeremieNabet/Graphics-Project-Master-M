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

    public Sphere(Point3D center, double radius) {
        this.center = center;
        this.radius = radius;
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
     * @param ray
     * @return list of intersection points
     */
    @Override
    public List<Point3D> findIntersections(Ray ray) {
        Point3D P0 = ray.getP0();
        Vector v = ray.getDirection();

        if (P0.equals(center)) {
            return List.of(ray.getPoint(radius));
        }
        Vector U = center.subtract(P0);

        double tm = alignZero(v.dotProduct(U));

        double d = alignZero(Math.sqrt(U.lengthSquared() - tm * tm));
        if (alignZero(d - radius) >= 0)
            return null; //because there is not intersections : the ray direction is tangent or out of the sphere

        double th = Math.sqrt(radius * radius - d * d);
        if (isZero(th)) return null; // ray's line is tangent to sphere
        double t1 = alignZero(tm - th);
        double t2 = alignZero(tm + th);

        // t1 < t2 ALWAYS
        if (t2 <= 0) return null; // both t1 and t2 are <=0
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
