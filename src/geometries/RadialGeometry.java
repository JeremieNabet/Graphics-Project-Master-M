package geometries;

/**
 * abstract class which implements Geometry
 */
public abstract class RadialGeometry implements Geometry {
    /**
     * Radius fields of my geometry forms which will inherit from my class
     */
    final double radius;

    /**
     * Copy Constructor
     *
     * @param radius
     */
    public RadialGeometry(double radius) {
        this.radius = radius;
    }

    /**
     * Allows me to have the radius value
     *
     * @return radius
     */
    public double getRadius() {
        return radius;
    }
}
