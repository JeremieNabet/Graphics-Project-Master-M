package geometries;

import primitives.*;

import java.util.List;

public class Cylinder extends Tube {
    /**
     * the height of the cylinder
     */
    private double height;

    public Cylinder(double radius, Ray ray,  double height) {
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

    @Override
    public List<Point3D> findIntersections(Ray ray) {
        return super.findIntersections(ray);
    }
}
