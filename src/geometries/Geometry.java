package geometries;

import primitives.*;

/**
 * interface for all the geometries that have a normal from them
 */
public interface Geometry extends Intersectable {
    /**
     *
     * @param point should be null for first geometries
     * @return the normal to the geometry
     */
    Vector getNormal(Point3D point);
}
