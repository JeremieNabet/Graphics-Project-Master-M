package geometries;

import primitives.*;

import static primitives.Util.*;

import java.util.List;

/**
 * this is the class definitions and in fact it inherits from the Polygon class.
 */
public class Triangle extends Polygon {
    /**
     * Constructor a of my triangle
     *
     * @param pointA point a of my triangle
     * @param pointB point a of my triangle
     * @param pointC point a of my triangle
     */
    public Triangle(Point3D pointA, Point3D pointB, Point3D pointC) {
        super(pointA, pointB, pointC);
    }


    @Override
    public String toString() {
        return "Triangle{" + "vertices=" + vertices + ", plane=" + plane + '}';
    }
}
