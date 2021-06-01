package primitives;

import java.util.List;
import java.util.stream.Collectors;

import static geometries.Intersectable.GeoPoint;


import static primitives.Util.alignZero;
import static primitives.Util.isZero;

/**
 * Ray class. with one point and one vector.
 *
 * @author Jeremie Nabet and Israel Bellaiche
 */

public class Ray {
    /**
     * the point p0 of the ray
     */
    private final Point3D p0;
    /**
     * the direction vector of the ray
     */
    private final Vector dir;

    public Ray(Point3D p0, Vector dir) {
        this.p0 = p0;
        this.dir = dir.normalized();
    }

    /**
     * function get p0 of the Ray
     *
     * @return p0
     */
    public Point3D getP0() {
        return p0;
    }

    /**
     * function get direction of the Vector
     *
     * @return dir
     */
    public Vector getDirection() {
        return dir;
    }

    /**
     * Calculate all the point on the Ray
     * Take start point and additional his direction vector multiplication
     * by scalar
     *
     * @param t he scalar - must be a positive value
     * @return the all point that is on the Ray
     */
    public Point3D getPoint(double t) {
        if (!isZero(alignZero(t))){
            return p0.add(dir.scale(alignZero(t)));
        }
        return p0;
    }

    /**
     * Find the closest Point to Ray origin
     * Find the point with minimal distance from the
     * ray head point and return it
     *
     * @param pointsList intersections point List
     * @return closest point
     */
    public Point3D findClosestPoint(List<Point3D> pointsList) {
        if (pointsList == null)
            return null;
        return findClosestGeoPoint( //
                pointsList.stream().map(p -> new GeoPoint(null, p)).collect(Collectors.toList()) //
        ).point;
    }

    /**
     * Find the closest Point to list of point origin
     * Find the point with minimal distance from the
     * ray head point and return it
     *
     * @param geoPointList intersections point List
     * @return closest point
     */
    public GeoPoint findClosestGeoPoint(List<GeoPoint> geoPointList) {
        if (geoPointList == null) return null;
        GeoPoint minPoint = null;
        double distance = Double.POSITIVE_INFINITY;
        for (var geoPoint : geoPointList) {
            double temp = geoPoint.point.distance(p0);
            if (temp < distance) {
                distance = temp;
                minPoint = geoPoint;
            }
        }
        return minPoint;
    }

    /*************** Admin *****************/
    @Override
    public boolean equals(Object o) {
        if (o == null) return false;
        if (this == o) return true;
        if (!(o instanceof Ray)) return false;
        Ray ray = (Ray) o;
        return p0.equals(ray.p0) && dir.equals(ray.dir);
    }

    /**
     * Function toString of the class Ray
     *
     * @return string
     */
    @Override
    public String toString() {
        return "Ray{" + "p0=" + p0 + ", dir=" + dir + '}';
    }

}
