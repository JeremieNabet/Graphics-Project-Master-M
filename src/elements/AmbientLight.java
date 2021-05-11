package elements;

import primitives.Color;

/**
 * An ambient light source represents a fixed-intensity and fixed color
 * light source that affects all objects in the scene equally.
 */
public class AmbientLight {
    /**
     * intensity of ambient light color
     */
    final private Color intensity;

    /**
     * Constructor
     * @param Ia intensity color
     * @param Ka constant for intensity
     */
    public AmbientLight(Color Ia, double Ka) {
        intensity = Ia.scale(Ka);
    }

    /**
     * get intensity color
     * @return intensity
     */
    public Color getIntensity() {
        return intensity;
    }

}