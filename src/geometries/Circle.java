package geometries;

import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

import java.util.List;

import static primitives.Util.alignZero;

/**
 * Circle class represents Euclidean 2D circle in 3D Cartesian coordinate system
 * represented by its center, radius and orthogonal unit vector
 *
 * @author Dan
 */
public class Circle extends RadialGeometry implements Geometry {
    private final Point3D _center;
    private final Plane _plane;

    /**
     * Circle constructor given its center, radius and orthogonal unit vector
     * (normal)
     *
     * @param center center point
     * @param radius
     * @param n      orthogonal vector (will be normalized)
     */
    public Circle(double radius, Point3D center, Vector n) {
        super(radius);
        _center = center;
        _plane = new Plane(center, n);
    }

    /**
     * Center getter for the circle
     *
     * @return the center point
     */
    public Point3D getCenter() {
        return _center;
    }

    @Override
    public Vector getNormal(Point3D p) {
        return _plane.getNormal(null);
    }

    /**
     * allows me to find the intersection points of my Circle
     * if the points are not found, the function returns null
     *
     * @param ray
     * @return list of intersection points
     */
    @Override
    public List<Point3D> findIntersections(Ray ray) {
        List<Point3D> intersections = _plane.findIntersections(ray);
        if (intersections == null) return null;

        if (alignZero(radius - _center.distance(intersections.get(0))) <= 0) return null;
        return intersections;
    }
}