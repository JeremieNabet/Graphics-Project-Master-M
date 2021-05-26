package elements;

import primitives.Color;
import primitives.Point3D;
import primitives.Vector;

/**
 * spot light class
 * @author Jeremie Nabet and Israel Bellaiche
 */
public class SpotLight extends PointLight implements LightSource{

    //spot aimed direction
    private final Vector _direction;

    /**
     * ctor
     * @param intensity spot intensity
     * @param position spot position
     * @param direction spot direction
     */
    public SpotLight(Color intensity, Point3D position, Vector direction) {
        super(intensity, position);
        this._direction = direction.normalized();
    }

    /**
     * spot color getter
     * @param p given point
     * @return color at this point
     */
    @Override
    public Color getIntensity(Point3D p) {
        return super.getIntensity(p).scale(Math.max(0,_direction.dotProduct(getL(p))));
    }
}
