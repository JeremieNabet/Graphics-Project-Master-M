package geometries;

import primitives.Point3D;
import primitives.Vector;

import java.awt.*;

public class Plane implements Geometry{
    primitives.Point3D point;
    Vector normal;

    public Plane(Point3D point1, Point3D point2, Point3D point3){
        this.point = point1;
        this.normal = null;
    }
    public Plane(Point3D p, Vector normal){
        this.point = new Point3D(p);
        this.normal = new Vector(normal);
    }

    public Vector getNormal(Point3D p){return normal};
    public Vector getNormal() {
        return null;
    }
}
