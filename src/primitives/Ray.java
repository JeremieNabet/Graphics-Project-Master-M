package primitives;

import geometries.Intersectable.GeoPoint;

import java.util.LinkedList;
import java.util.List;

import static primitives.Util.isZero;

/**
 * class Ray is the basic class representing a ray for Cartesian
 * coordinate system.
 */
public class Ray {
    /**
     * DELTA- represents a small move of rays point
     */
    private static final double DELTA = 0.1;

    /**
     * p0 begging point
     */
    private final Point3D p0;
    /**
     * dir direction
     */
    private final Vector dir;


    /**
     * ray class c-tor
     *
     * @param p0  point3d
     * @param dir vector
     */
    public Ray(Point3D p0, Vector dir) {
        this.p0 = p0;
        this.dir = dir.normalized();
    }

    /**
     * Ray c-tor receives a point and a vector and a normal vector
     * moves of rays point on normal vector by DELTA
     *
     * @param point
     * @param dir
     * @param n
     */
    public Ray(Point3D point, Vector dir, Vector n) {
        Vector delta = n.scale(n.dotProduct(dir) > 0 ? DELTA : -DELTA);
        Point3D p = point.add(delta);
        p0 = p;
        this.dir = dir.normalized();

    }

    /**
     * getter
     *
     * @return point po
     */
    public Point3D getP0() {
        return p0;
    }

    /**
     * getter
     *
     * @return vector Direction
     */
    public Vector getDirection() {
        return dir;
    }

    /**
     * @param delta
     * @return point po * scale
     */
    public Point3D getPoint(double delta) {
        if (isZero(delta)) {
            return p0;
        }
        return p0.add(dir.scale(delta));
    }

    /**
     * @param points
     * @return the closest geoPoint to the point _p0
     */
    public GeoPoint findClosestGeoPoint(List<GeoPoint> points) {
        GeoPoint minPoint = null;
        if (points == null) {
            return null;
        }
        double distance = Double.POSITIVE_INFINITY;
        for (GeoPoint geo : points) {
            double temp = geo.point.distance(p0);
            if (temp < distance) {
                distance = temp;
                minPoint = geo;
            }
        }
        return minPoint;
    }

    /**
     * @param points intersection points with the ray
     * @return the closest point to the point _p0
     */
    public Point3D findClosestPoint(List<Point3D> points) {
        Point3D minPoint = null;
        if (points == null) {
            return null;
        }
        double distance = Double.POSITIVE_INFINITY;
        for (Point3D p : points) {
            double temp = p.distance(p0);
            if (temp < distance) {
                distance = temp;
                minPoint = p;
            }
        }
        return minPoint;
    }

    /**
     * function that creates a beam of rays in random positions. all the rays point to one point
     *
     * @param center    - the center of the base of the beam
     * @param target    - the target point that all rays point to
     * @param rad       - the radius of the base of the beam
     * @param numOfRays - number of rays that will be created
     * @param vRight    - a vector orthogonal to the original ray
     * @param vUp       -  a vector orthogonal to the original ray and the right vector
     * @return - a list that contains all the new rays
     */
    public static List<Ray> rayRandomBeam(Point3D center, Point3D target, double rad, int numOfRays, Vector vRight, Vector vUp) {
        List<Ray> result = new LinkedList<>();
        for (int k = 0; k < numOfRays; k++) {
            double x = Math.random() * 2 * rad - rad;
            double cosX = Math.sqrt(rad - x * x);
            double y = Math.random() * 2 * cosX - cosX;
            Point3D pC = center.add(vRight.scale(x));//a point on view plane around the pixel
            pC = pC.add(vUp.scale(y));
            Ray focalRay = new Ray(pC, target.subtract(pC));
            result.add(focalRay);
        }
        return result;
    }


    /**
     * override func checks if two rays are equal
     *
     * @param o
     * @return true if its equal
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ray ray = (Ray) o;
        return p0.equals(ray.p0) && dir.equals(ray.dir);
    }
}