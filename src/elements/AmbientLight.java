package elements;

import primitives.Color;

/**
 * Ambient Light Color
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