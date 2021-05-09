package geometries;

import primitives.*;

import java.util.List;

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
    public List<Point3D> findIntersections(Ray ray);

}
