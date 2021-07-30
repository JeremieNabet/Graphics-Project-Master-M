package elements;

import primitives.Color;

/**
 * An ambient light source represents a fixed-intensity and fixed color
 * light source that affects all objects in the scene equally.
 */
public class AmbientLight extends Light {

    public AmbientLight() {
        super(Color.BLACK);
    }

    /**
     * Constructor
     *
     * @param iA color of light
     * @param kA light intensity
     */
    public AmbientLight(Color iA, double kA) {
        super(iA.scale(kA));
    }

}