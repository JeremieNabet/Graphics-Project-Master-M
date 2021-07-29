package elements;

import primitives.Color;
import primitives.Point3D;
import primitives.Util;
import primitives.Vector;

/**
 * spot light class
 *
 * @author Jeremie Nabet and Israel Bellaiche
 */
public class SpotLight extends PointLight implements LightSource {

    /**
     * spot aimed direction
     */
    private final Vector direction;

    /**
     * Constructor
     *
     * @param intensity spot intensity
     * @param position  spot position
     * @param direction spot direction
     */
    public SpotLight(Color intensity, Point3D position, Vector direction) {
        super(intensity, position);
        this.direction = direction.normalized();
    }

    @Override
    public Color getIntensity(Point3D p) {
        double ld = Util.alignZero(direction.dotProduct(getL(p)));
        if (ld <= 0) return Color.BLACK; // the point is not in the direction of the spot
        return super.getIntensity(p).scale(ld);
    }
}
