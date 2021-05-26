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

    /**
     * this one allows me to find the intersections of each
     * class which will inherit this interface
     *
     * @param ray
     * @return list of intersection points
     */
    List<Point3D> findIntersections(Ray ray);
    public static class GeoPoint{
        public Geometry _geometry;
        public Point3D _point3D;

        /**
         * ctor
         * @param geometry shape
         * @param point3D intersection point
         */
        public GeoPoint(Geometry geometry, Point3D point3D) {
            _geometry = geometry;
            _point3D = point3D;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            GeoPoint geoPoint = (GeoPoint) o;
            return Objects.equals(_geometry, geoPoint._geometry) && Objects.equals(_point3D, geoPoint._point3D);
        }
    }


    /**
     * find the Geo intersections of a ray
     * @param ray given ray
     * @return all the Geo intersections points with the body
     */
    public List<GeoPoint> findGeoIntersections(Ray ray);
}


