package elements;

import primitives.Color;

/**
 * light class for lightning source with intensity
 * @author Jeremie Nabet and Israel Bellaiche
 */
abstract class Light {
    //light intensity
    private final Color _intensity;

    /**
     * ctor
     * @param intensity light intensity
     */
    protected Light(Color intensity) {
        _intensity = intensity;
    }

    /**
     * intensity getter
     * @return original light intensity
     */
    public Color getIntensity() {
        return _intensity;
    }
}
