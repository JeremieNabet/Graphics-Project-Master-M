package geometries;

import primitives.*;

import java.util.List;

import static primitives.Util.*;

/**
 * this is the class definitions with it's fields - one Ray,
 * and one double type for the radius.
 *
 * @author Jeremie NAbet and Israel bellaiche
 */
public class Tube extends Geometry {
    /**
     * the axis ray of the tube
     */
    protected Ray axisRay;
    /**
     * this radius of the tube
     */
    protected double radius;

    /**
     * this is the class ctor.
     *
     * @param ray    the ray.
     * @param radius the _radius.
     */
    public Tube(Ray ray, double radius) {
        this.axisRay = ray;
        this.radius = alignZero(radius);
    }

    /**
     * the func is for getting the axisRay.
     *
     * @return the ray of my tube.
     */
    public Ray getAxisRay() {
        return axisRay;
    }

    /**
     * the func is for getting the radius.
     *
     * @return the radius of my tube.
     */
    public double getRadius() {
        return radius;
    }

    /**
     * function which returns me the value of Tube normalize
     *
     * @param point subtract 0
     * @return the value normalized
     */
    public Vector getNormal(Point3D point) {
        Point3D p0 = axisRay.getP0();
        Vector v = axisRay.getDirection();
        Vector p0P = point.subtract(p0);
        double t = p0P.dotProduct(v);

        if (isZero(t)) return p0P.normalize();

        Point3D o = p0.add(v.scale(t));
        return point.subtract(o).normalize();
    }

    /**
     * find the intersections of a ray with the tube.
     *
     * @param ray
     * @return all the intersections points with the tube
     */
    @Override
    public List<GeoPoint> findGeoIntersections(Ray ray, double maxDistance) {

        /*
        The equation for a tube of radius r oriented along a line pa + vat:
        (q - pa - (va,q - pa)va)2 - r2 = 0
        get intersections using formula : (p - pa + vt - (va,p - pa + vt)va)^2 - r^2 = 0
        reduces to at^2 + bt + c = 0
        with a = (v - (v,va)va)^2
             b = 2 * (v - (v,va)va,∆p - (∆p,va)va)
             c = (∆p - (∆p,va)va)^2 - r^2
        where  ∆p = p - pa
        */

        Vector v = ray.getDirection();
        Vector va = this.getAxisRay().getDirection();

        //vectors are parallel and there is no intersections.
        if (v.normalize().equals(va.normalize()))
            return null;

        //use of calculated variables to avoid vector ZERO
        double vva;
        double pva;
        double a;
        double b;
        double c;

        //check every variables to avoid ZERO vector
        if (ray.getP0().equals(this.getAxisRay().getP0())) {
            vva = v.dotProduct(va);
            if (vva == 0) {
                a = v.dotProduct(v);
            } else {
                a = (v.subtract(va.scale(vva))).dotProduct(v.subtract(va.scale(vva)));
            }
            b = 0;
            c = -getRadius() * getRadius();
        } else {
            Vector deltaP = ray.getP0().subtract(this.getAxisRay().getP0());
            vva = v.dotProduct(va);
            pva = deltaP.dotProduct(va);

            if (vva == 0 && pva == 0) {
                a = v.dotProduct(v);
                b = 2 * v.dotProduct(deltaP);
                c = deltaP.dotProduct(deltaP) - getRadius() * getRadius();
            } else if (vva == 0) {
                a = v.dotProduct(v);
                if (deltaP.equals(va.scale(deltaP.dotProduct(va)))) {
                    b = 0;
                    c = -getRadius() * getRadius();
                } else {
                    b = 2 * v.dotProduct(deltaP.subtract(va.scale(deltaP.dotProduct(va))));
                    c = (deltaP.subtract(va.scale(deltaP.dotProduct(va))).dotProduct(deltaP.subtract(va.scale(deltaP.dotProduct(va))))) - this.getRadius() * this.getRadius();
                }
            } else if (pva == 0) {
                a = (v.subtract(va.scale(vva))).dotProduct(v.subtract(va.scale(vva)));
                b = 2 * v.subtract(va.scale(vva)).dotProduct(deltaP);
                c = (deltaP.dotProduct(deltaP)) - this.getRadius() * this.getRadius();
            } else {
                a = (v.subtract(va.scale(vva))).dotProduct(v.subtract(va.scale(vva)));
                if (deltaP.equals(va.scale(deltaP.dotProduct(va)))) {
                    b = 0;
                    c = -getRadius() * getRadius();
                } else {
                    b = 2 * v.subtract(va.scale(vva)).dotProduct(deltaP.subtract(va.scale(deltaP.dotProduct(va))));
                    c = (deltaP.subtract(va.scale(deltaP.dotProduct(va))).dotProduct(deltaP.subtract(va.scale(deltaP.dotProduct(va))))) - this.getRadius() * this.getRadius();
                }
            }
        }

        //calculate delta for result of equation
        double delta = b * b - 4 * a * c;
        // no intersections
        if (delta <= 0) {
            return null;
        } else {
            //calculate points taking only those with t > 0
            double t1 = alignZero((-b - Math.sqrt(delta)) / (2 * a));
            double t2 = alignZero((-b + Math.sqrt(delta)) / (2 * a));
            if (t1 > 0 && t2 > 0) {
                Point3D p1 = ray.getPoint(t1);
                Point3D p2 = ray.getPoint(t2);
                return List.of(new GeoPoint(this, p1), new GeoPoint(this, p2));
            } else if (t1 > 0) {
                Point3D p1 = ray.getPoint(t1);
                return List.of(new GeoPoint(this, p1));
            } else if (t2 > 0) {
                Point3D p2 = ray.getPoint(t2);
                return List.of(new GeoPoint(this, p2));
            }
        }
        return null;
    }

    @Override
    public String toString() {
        return "Tube{" + "axisRay=" + axisRay + ", radius=" + radius + '}';
    }

}
