package geometries;

import primitives.*;

import java.util.List;

import static primitives.Util.alignZero;
import static primitives.Util.isZero;

public class Sphere implements Geometry {
    /**
     * the point center about the sphere
     */
    final Point3D center;
    /**
     * the radius about the sphere
     */
    final double radius;

    public Sphere(Point3D center, double radius) {
        this.center = center;
        this.radius = radius;
    }

    /**
     * this function give the center point about the sphere
     *
     * @return center
     */
    public Point3D getCenter() {
        return center;
    }

    /**
     * this function give the radius about the sphere
     *
     * @return radius
     */
    public double getRadius() {
        return radius;
    }

    /**
     * getNormal Override function
     *
     * @param point3D
     * @return
     */
    @Override
    public Vector getNormal(Point3D point3D) {
        if (point3D.equals(center)) {
            throw new IllegalArgumentException("Point cannot be the center");
        }
        Vector p0_p = point3D.subtract(center);
        return p0_p.normalize();
    }

    /**
     * toString override function
     *
     * @return toString about this class
     */
    @Override
    public String toString() {
        return "Sphere{" + "center=" + center + ", radius=" + radius + '}';
    }

    @Override
    public List<Point3D> findIntersections(Ray ray) {
        Point3D P0 = ray.getP0();
        Vector v = ray.getDir();

        if (P0.equals(center)) {
            return List.of(center.add(v.scale(radius)));
        }
        Vector U = center.subtract(P0);

        double tm = alignZero(v.dotProduct(U));

        double d = alignZero(Math.sqrt(U.lengthSquared() - tm * tm));
        if (d >= radius) {
            return null;
            //because there is not intersections : the ray direction is above the sphere
        }
        double th = alignZero(Math.sqrt(radius * radius - d * d));

        double t1 = alignZero(tm - th);
        double t2 = alignZero(tm + th);

        if (t1 > 0 && t2 > 0) {
            Point3D P1 = P0.add(v.scale(t1));
            Point3D P2 = P0.add(v.scale(t2));

            return List.of(P1,P2);
        }
        if(t1>0){
            Point3D P1 = P0.add(v.scale(t1));
            return List.of(P1);
        }
        if(t2>0){
            Point3D P2 = P0.add(v.scale(t2));
            return List.of(P2);
        }

        return null;
    }
}
