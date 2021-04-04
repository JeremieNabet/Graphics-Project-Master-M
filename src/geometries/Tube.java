package geometries;

import primitives.*;

import java.util.List;

import static primitives.Util.*;


public class Tube implements Geometry {
    /**
     * the axis ray about the tube
     */
    protected Ray axisRay;
    /**
     * this radius about the tube
     */
    protected double radius;

    public Tube(Ray ray, double radius) {
        this.axisRay = ray;
        this.radius = radius;
    }

    /**
     * @param point Point3D point
     * @return
     */
    public Vector getNormal(Point3D point) {
        Point3D p0 = axisRay.getP0();
        Vector v = axisRay.getDir();
        Vector P0_P = point.subtract(p0);
        double t = alignZero(P0_P.dotProduct(v));

        if (isZero(t)) {
            return P0_P.normalize();
        }
        Point3D O = p0.add(v.scale(t));

        if (point.equals(0)) {
            throw new IllegalArgumentException("Point cannot be on the tube axis");
        }
        Vector O_P = point.subtract(O);
        return O_P.normalize();

    }

    /**
     * toString override function
     *
     * @return
     */
    @Override
    public String toString() {
        return "Tube{" + "axisRay=" + axisRay + ", radius=" + radius + '}';
    }

    @Override
    public List<Point3D> findIntersections(Ray ray) {
        return null;
    }
}
