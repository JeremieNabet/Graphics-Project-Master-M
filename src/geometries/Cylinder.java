package geometries;

import primitives.*;

public class Cylinder extends Tube{
    /**
     * the height of the cylinder
     */
    private double height;

    public Cylinder(Ray ray, double radius, double height) {
        super(ray, radius);
        this.height = height;
    }

    @Override
    public String toString() {
        return "Cylinder{" +
                "height=" + height +
                '}';
    }
    public Vector getNormal(Point3D p){return getNormal(p);}
}
