package primitives;

/**
 * class Material
 */
public class Material {
    public double kD=0;
    public double kS=0;
    public int nShininess=0;
    public double kT=0;
    public double kR=0;



    /**
     * setter
     * @param kD
     * @return material
     */
    public Material setKd(double kD) {
        this.kD = kD;
        return this;
    }

    /**
     * setter
     * @param kS
     * @return material
     */
    public Material setKs(double kS) {
        this.kS = kS;
        return  this;
    }

    /**
     * setter
     * @param nShininess
     * @return material
     */
    public Material setShininess(int nShininess) {
        this.nShininess = nShininess;
        return this;
    }

    /**
     * setter
     * @param kT
     * @return material
     */
    public Material setKt(double kT) {
        this.kT = kT;
        return this;
    }

    /**
     * setter
     * @param kR
     * @return material
     */
    public Material setKr(double kR) {
        this.kR = kR;
        return  this;
    }


}
