package geometries;

import primitives.*;


import java.util.List;

public class Triangle extends Polygon {

    public Triangle(Point3D pointA, Point3D pointB, Point3D pointC) {
        super(pointA, pointB, pointC);
    }

    @Override
    public List<Point3D> findIntersections(Ray ray) {
        return super.findIntersections(ray);
    }
}
