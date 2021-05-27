package geometries;

import primitives.*;

/**
 * interface for all the geometries that have a normal from them
 */
public abstract class Geometry implements Intersectable {

    /**
     * color of shape
     */
    protected Color emission = Color.BLACK;

    /**
     * material of shape
     */
    private Material material = new Material();

    /**
     * emission getter
     *
     * @return emission
     */
    public Color getEmission() {
        return emission;
    }

    /**
     * material getter
     *
     * @return material
     */
    public Material getMaterial() {
        return material;
    }

    /**
     * emission setter
     *
     * @param emission given emission
     * @return this
     */
    public Geometry setEmission(Color emission) {
        this.emission = emission;
        return this;
    }

    /**
     * material setter
     *
     * @param material given material
     * @return this
     */
    public Geometry setMaterial(Material material) {
        this.material = material;
        return this;
    }

    /**
     * Calculate the normal vector at a point on body's
     * surface. Normal vector is a unit vector (size=1) orthogonal
     * to tangent plane at the point
     *
     * @param point point on the surface of geom.body
     * @return the normal to the geometry
     */
    public abstract Vector getNormal(Point3D point);

    @Override
    public String toString() {
        return super.toString() + " {" +
                "emission=" + emission +
                ", material=" + material +
                '}';
    }
}
