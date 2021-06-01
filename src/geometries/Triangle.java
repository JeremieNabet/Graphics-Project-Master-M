package geometries;

import primitives.*;

import static primitives.Util.*;

import java.util.List;

/**
 * this is the class definitions and in fact it inherits from the Polygon class.
 */
public class Triangle extends Polygon {
    /**
     * Constructor a of my triangle
     *
     * @param pointA point a of my triangle
     * @param pointB point a of my triangle
     * @param pointC point a of my triangle
     */
    public Triangle(Point3D pointA, Point3D pointB, Point3D pointC) {
        super(pointA, pointB, pointC);
    }

//    /**
//     * allows me to find the intersection points of my triangle
//     * if the points are not found, the function returns null
//     *
//     * @param ray the ray that may be the vector that enter in the table
//     * @return list of intersection points
//     */
//    @Override
//    public List<Point3D> findIntersections(Ray ray) {
//        var planeInts = plane.findIntersections(ray);
//        if (planeInts == null) return null;
//
//        Point3D p0 = ray.getP0();
//        Vector v1 = vertices.get(0).subtract(p0);
//        Vector v2 = vertices.get(1).subtract(p0);
//
//        Vector v = ray.getDirection();
//        Vector n1 = v1.crossProduct(v2);
//        double s1 = n1.dotProduct(v);
//        if (isZero(s1)) return null;
//
//        Vector v3 = vertices.get(2).subtract(p0);
//        Vector n2 = v2.crossProduct(v3);
//        double s2 = n2.dotProduct(v);
//        if (alignZero(s1 * s2) <= 0) return null;
//
//        Vector n3 = v3.crossProduct(v1);
//        double s3 = n3.dotProduct(v);
//        if (alignZero(s1 * s3) <= 0) return null;
//
//        return planeInts;
//    }

    @Override
    public String toString() {
        return "Triangle{" + "vertices=" + vertices + ", plane=" + plane + '}';
    }
}
