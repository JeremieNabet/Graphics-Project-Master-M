package elements;

import primitives.Color;
import primitives.Point3D;
import primitives.Vector;

/**
 * point light class
 * @author Jeremie Nabet and Israel Bellaiche
 */
public class PointLight extends Light implements LightSource{
    //point light position
    private final Point3D _position;

    private double _Kc=1d;
    private double _Kl=0d;
    private double _Kq=0d;

    /**
     * Kc setter
     * @param kc given Kc
     * @return this
     */
    public PointLight setKc(double kc) {
        _Kc = kc;
        return this;
    }
    /**
     * Kl setter
     * @param kl given Kl
     * @return this
     */
    public PointLight setKl(double kl) {
        _Kl = kl;
        return this;
    }
    /**
     * Kq setter
     * @param kq given Kq
     * @return this
     */
    public PointLight setKq(double kq) {
        _Kq = kq;
        return this;
    }

    /**
     * ctor
     * @param intensity given intensity
     * @param position given light source position
     */
    protected PointLight(Color intensity, Point3D position) {
        super(intensity);
        _position = position;
    }

    /**
     * get color of a point
     * @param p given point
     * @return color at the given point
     */
    @Override
    public Color getIntensity(Point3D p) {
        double d=p.distance(_position);
        double factor=_Kc+_Kl*d+_Kq*d*d;
        return getIntensity().scale(1d/factor);

    }

    /**
     * direction getter
     * @param p given point
     * @return direction vector to the point
     */
    @Override
    public Vector getL(Point3D p) {
        return p.subtract(_position).normalized();
    }
}
