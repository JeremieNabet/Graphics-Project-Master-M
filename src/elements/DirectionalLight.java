package elements;

import primitives.Color;
import primitives.Point3D;
import primitives.Vector;

public class DirectionalLight extends Light implements LightSource{
    private final Vector direction;

    /**
     * ctor
     * @param intensity intensity light
     * @param direction direction of light
     */
    protected DirectionalLight(Color intensity, Vector direction) {
        super(intensity);
        this.direction = direction;
    }

    /**
     * intensity getter
     * @param p calculate the intensity in the point
     * @return original intensity of light source
     */
    @Override
    public Color getIntensity(Point3D p) {
        return super.getIntensity();
    }

    /**
     * direction getter
     * @param p calculate the intensity in the point
     * @return normalized direction
     */
    @Override
    public Vector getL(Point3D p) {
        return direction.normalized();
    }
}
