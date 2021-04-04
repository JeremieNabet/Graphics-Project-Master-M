package geometries;

import primitives.*;

public interface Geometry {
    /**
     *
     * @param point should be null for first geometries
     * @return the normal to the geometry
     */
    Vector getNormal(Point3D point);
}
