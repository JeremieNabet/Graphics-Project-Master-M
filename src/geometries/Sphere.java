package geometries;

import primitives.*;

import java.util.List;

import static primitives.Util.*;

/**
 * this is the class definitions with it's fields - one point3D for
 * the center point, and one double type for the radius.
 *
 * @author Israel Bellaiche and Jeremie Nabet
 */
public class Sphere extends Geometry {
    /**
     * the point center of the sphere
     */
    private final Point3D center;
    /**
     * the radius of the sphere
     */
    private final double radius;
    /**
     * the radius in square
     */
    private final double radiusSqr;

    /**
     * Constructor sphere that need point center and radius (double)
     *
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

    @Override
    public Vector getNormal(Point3D point3D) {
        return point3D.subtract(center).normalized();
    }

    @Override
    public List<GeoPoint> findGeoIntersections(Ray ray,double maxDistance) {
        //exit the center point of the sphere.
        if (getCenter().equals(ray.getP0())) {
            return List.of(new GeoPoint(this,ray.getPoint(getRadius())));
        }
        //from top of the ray to center of the sphere.
        Vector u = center.subtract(ray.getP0());
        //ray vector.
        Vector v = ray.getDirection();

        double tm = alignZero(u.dotProduct(v));
        double d = alignZero(Math.sqrt(u.lengthSquared()-tm*tm));

        //ray goes out of the sphere.
        if(alignZero(d - getRadius())>=0){
            return  null;
        }

        double th = alignZero(Math.sqrt(radiusSqr-d*d));

        //p is on the surface of the sphere.
        if(isZero(th)){
            return null;
        }

        double t1=alignZero(tm+th);
        double t2=alignZero(tm-th);

        //two intersections points.
        if(t1>0&&t2>0){
            if (alignZero(maxDistance - t1) > 0 && alignZero(maxDistance - t2) > 0) {
                Point3D P1 = ray.getP0().add(v.scale(t2));
                Point3D P2 = ray.getP0().add(v.scale(t1));
                return List.of(new GeoPoint(this, P1), new GeoPoint(this, P2));
            }        }
        //one intersection point.
        if(t1>0 && alignZero(maxDistance-t1)> 0){
            return List.of(new GeoPoint(this,ray.getPoint(t1)));
        }
        //one intersection point.
        if(t2>0 && alignZero(maxDistance-t2)> 0){
            return List.of(new GeoPoint(this,ray.getPoint(t2)));
        }
        return null;
    }


    @Override
    public String toString() {
        return super.toString() + " {" + "o=" + center + ", r=" + radius + '}';
    }
}
