package geometries;

import primitives.*;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * Intersectable is a common interface for all geometries that are able
 * to intersect from a ray to their entity
 */
public interface Intersectable {


    class GeoPoint {
        /**
         * geometry
         */
        public Geometry geometry;
        /**
         * point
         */
        public Point3D point;

        /**
         * ctor
         *
         * @param geometry shape
         * @param point    intersection point
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
     * this one allows me to find the intersections of each
     * class which will inherit this interface
     *
     * @param ray of the intersection
     * @return list of intersection points
     */
   default List<Point3D> findIntersections(Ray ray){
       var geoList = findGeoIntersections(ray);
       return geoList == null ? null
               : geoList.stream()
               .map(gp -> gp.point)
               .collect(Collectors.toList());
   }
    /**
     * find the Geo intersections of a ray
     * @param ray given ray
     * @return all the Geo intersections points with the body
     */
    default List<GeoPoint> findGeoIntersections(Ray ray){
        return findGeoIntersections(ray,Double.POSITIVE_INFINITY);
    }

    /**
     * find the Geo intersections of a ray
     *
     * @param ray given ray
     * @return all the Geo intersections points with the body
     */
    List<GeoPoint> findGeoIntersections(Ray ray,double maxDistance);
}


