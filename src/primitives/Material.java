package primitives;

/**
 * material class for shapes
 *
 * @author Jeremie Nabet and Israel Bellaiche
 */
public class Material {
    public double kd;
    public double ks;
    public double kr;
    public double kt;
    /**
     * How glossy the shape is
     */
    public int nShininess;

    /**
     * Kd setter
     *
     * @param kd the kD given
     * @return this
     */
    public Material setKd(double kd) {
        this.kd = kd;
        return this;
    }

    /**
     * @param ks the kS given
     * @return this (for chaining)
     */
    public Material setKs(double ks) {
        this.ks = ks;
        return this;
    }

    /**
     * kd setter
     *
     * @param kr the kD given
     * @return this
     */
    public Material setKr(double kr) {
        this.kr = kr;
        return this;
    }

    /**
     * kd setter
     *
     * @param kt the kD given
     * @return this
     */
    public Material setKt(double kt) {
        this.kt = kt;
        return this;
    }

    /**
     * glossy shape setter
     *
     * @param nShininess given nShininess
     * @return this
     */
    public Material setNShininess(int nShininess) {
        this.nShininess = nShininess;
        return this;
    }

}
