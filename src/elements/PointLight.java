package elements;

import primitives.Color;
import primitives.Point3D;
import primitives.Vector;

/**
 * point light class
 *
 * @author Jeremie Nabet and Israel Bellaiche
 */
public class PointLight extends Light implements LightSource {
    /**
     * point light position
     */
    private final Point3D position;
    /**
     * Kc
     */
    private double kc = 1d;
    /**
     * Kl
     */
    private double kl = 0d;
    /**
     * Kq
     */
    private double kq = 0d;

    /**
     * Kc setter
     *
     * @param kc given Kc
     * @return this
     */
    public PointLight setKc(double kc) {
        this.kc = kc;
        return this;
    }

    /**
     * Kl setter
     *
     * @param kl given Kl
     * @return this
     */
    public PointLight setKl(double kl) {
        this.kl = kl;
        return this;
    }

    /**
     * Kq setter
     *
     * @param kq given Kq
     * @return this
     */
    public PointLight setKq(double kq) {
        this.kq = kq;
        return this;
    }

    /**
     * Constructor
     *
     * @param intensity given intensity
     * @param position  given light source position
     */
    public PointLight(Color intensity, Point3D position) {
        super(intensity);
        this.position = position;
    }

    @Override
    public Color getIntensity(Point3D p) {
        double d2 = p.distanceSquared(position);
        double factor = kc + kl * Math.sqrt(d2) + kq * d2;
        return intensity.reduce(factor);

    }

    @Override
    public Vector getL(Point3D p) {
        return p.subtract(position).normalized();
    }

    @Override
    public double getDistance(Point3D point3D) {
        return position.distance(point3D);
    }
}
