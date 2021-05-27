package geometries;

import primitives.*;

import java.util.List;
import java.util.Objects;

/**
 * Intersectable is a common interface for all geometries that are able
 * to intersect from a ray to their entity
 */
public interface Intersectable {

    /**
     * this one allows me to find the intersections of each
     * class which will inherit this interface
     *
     * @param ray of the intersection
     * @return list of intersection points
     */
    List<Point3D> findIntersections(Ray ray);
    public static class GeoPoint{
        public Geometry geometry;
        public Point3D point;

        /**
         * ctor
         * @param geometry shape
         * @param point intersection point
         */
        public GeoPoint(Geometry geometry, Point3D point) {
            this.geometry = geometry;
            this.point = point;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            GeoPoint geoPoint = (GeoPoint) o;
            return Objects.equals(geometry, geoPoint.geometry) && Objects.equals(point, geoPoint.point);
        }

        @Override
        public String toString() {
            return "GeoPoint{" + geometry + ": " + point + '}';
        }
    }


    /**
     * find the Geo intersections of a ray
     * @param ray given ray
     * @return all the Geo intersections points with the body
     */
    public List<GeoPoint> findGeoIntersections(Ray ray);
}


