package elements;

import primitives.Color;
import primitives.Point3D;
import primitives.Vector;

/**
 * light source interface
 *
 * @author Jeremie Nabet and Israel Bellaiche
 */
public interface LightSource {
    /**
     * calculate the intensity in the point
     *
     * @param p given point
     * @return intensity at the given point
     */
    Color getIntensity(Point3D p);

    /**
     * calculate the direction in the point
     *
     * @param p given point
     * @return direction vector to the point
     */
    Vector getL(Point3D p);
}
