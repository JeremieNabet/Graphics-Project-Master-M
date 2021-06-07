package elements;

import primitives.Color;
import primitives.Point3D;
import primitives.Vector;

public class DirectionalLight extends Light implements LightSource {
    private final Vector direction;

    /**
     * ctor
     *
     * @param intensity intensity light
     * @param direction direction of light
     */
    protected DirectionalLight(Color intensity, Vector direction) {
        super(intensity);
        this.direction = direction;
    }

    @Override
    public Color getIntensity(Point3D p) {
        return super.getIntensity();
    }

    @Override
    public Vector getL(Point3D p) {
        return direction.normalized();
    }

    @Override
    public double getDistance(Point3D point3D) {
        return Double.POSITIVE_INFINITY;
    }
}
