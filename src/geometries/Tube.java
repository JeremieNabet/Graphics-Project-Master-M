package geometries;

import primitives.Point3D;
import primitives.Ray;

public class Tube{
    Ray axisRay;

    public Tube(double _radius){super(_radius);}

    @Override
    public primitives.Vector getNormal(Point3D point){return null;}
}
