package geometries;

import primitives.*;

import java.util.List;

import static primitives.Util.*;


public class Tube extends Geometry {
    /**
     * the axis ray of the tube
     */
    Ray axisRay;
    /**
     * this radius of the tube
     */
    protected double radius;

    public Tube(Ray ray, double radius) {
        this.axisRay = ray;
        this.radius = radius;
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
        Vector P0_P = point.subtract(p0);
        double t = P0_P.dotProduct(v);

        if (isZero(t)) return P0_P.normalize();

        Point3D O = p0.add(v.scale(t));
        return point.subtract(O).normalize();
    }

    /**
     * function to string that give me the value type about my class
     *
     * @return value string about this class
     */
    @Override
    public String toString() {
        return "Tube{" + "axisRay=" + axisRay + ", radius=" + radius + '}';
    }

    /**
     * allows me to find the intersection points of my tube
     * if the points are not found, the function returns null
     *
     * @param ray
     * @return list of intersection points
     */
    @Override
    public List<Point3D> findIntersections(Ray ray) {
        //TODO
        return null;
    }

    @Override
    public List<GeoPoint> findGeoIntersections(Ray ray) {
        return null;
    }
}
