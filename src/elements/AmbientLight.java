package elements;

import primitives.Color;

/**
 * An ambient light source represents a fixed-intensity and fixed color
 * light source that affects all objects in the scene equally.
 */
public class AmbientLight  extends Light {
    /**
     * intensity of ambient light color
     */
    //final private Color intensity;

    public AmbientLight() {
        super(Color.BLACK);
    }

    /**
     * ctor
     *
     * @param iA color of light
     * @param kA light intensity
     */
    public AmbientLight(Color iA, double kA) {
        super(iA.scale(kA));
    }


    /**
     * get intensity color
     *
     * @return intensity
     */
   // public Color getIntensity() {
 //   return intensity;
   //  }

}