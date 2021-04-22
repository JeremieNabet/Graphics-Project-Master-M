package geometries;

import primitives.*;

/**
 * interface for all the geometries that have a normal from them
 */
public interface Geometry extends Intersectable {
    /**
     * Calculate the normal vector at a point on body's
     * surface. Normal vector is a unit vector (size=1) orthogonal
     * to tangent plane at the point
     * @param point point on the surface of geom.body
     * @return the normal to the geometry
     */
    Vector getNormal(Point3D point);
}
