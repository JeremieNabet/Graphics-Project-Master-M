package geometries;

import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

import java.util.LinkedList;
import java.util.List;

import static primitives.Util.alignZero;
import static primitives.Util.isZero;

/**
 * cylinder class with height.
 *
 * @author jeremie nabet and israel bellaiche
 */
public class Cylinder extends Tube {

    /**
     * cylinder's height.
     */
    private double height;
    /**
     * the base of my cylinder
     */
    private final Plane base1;
    /**
     * the base of my cylinder
     */
    private final Plane base2;

    /**
     * Constructor which gives me the basics of my cylinder as well as the direction
     * and the height
     *
     * @param axisRay abscess de mon cylinder
     * @param radius  of the cylinder
     * @param height  of my cylinder
     */
    public Cylinder(Ray axisRay, double radius, double height) {
        super(axisRay, radius);
        this.height = alignZero(height);
        this.radius = alignZero(radius);
        Vector v = axisRay.getDirection();
        this.base1 = new Plane(axisRay.getP0(), v);
        this.base2 = new Plane(axisRay.getPoint(this.height), v);
    }

    /**
     * get cylinder normal.
     *
     * @param point a point
     * @return cylinder's normal
     */
    public Vector getNormal(Point3D point) {
        Point3D P0 = axisRay.getP0();
        Vector dir = axisRay.getDirection();

        //point on the base
        if (isZero(point.subtract(P0).dotProduct(dir))) {
            return dir;
        }
        //point on the top
        else if (isZero(point.subtract(P0.add(dir.scale(height))).dotProduct(dir))) {
            return dir;
        }
        //point on the surface, the normal is just like tube
        return super.getNormal(point);
    }

    @Override
    public List<GeoPoint> findGeoIntersections(Ray ray) {
        //the center of the bottom base.
        Point3D p1 = axisRay.getP0();
        //the center of the upper base.
        Point3D p2 = axisRay.getPoint(height);
        Vector Va = axisRay.getDirection();

        //tube intersections.
        List<GeoPoint> list = super.findGeoIntersections(ray);

        List<GeoPoint> result = new LinkedList<>();

        //checks that all the intersections are on the finite cylinder.
        if (list != null) {
            for (GeoPoint p : list) {
                if (Va.dotProduct(p.point.subtract(p1)) > 0 && Va.dotProduct(p.point.subtract(p2)) < 0)
                    result.add(0, p);
            }
        }

        //checks the bases intersections.
        //only less than 2 intersections.
        if (result.size() < 2) {
            //bottom base.
            Plane bottomBase = new Plane(p1, Va);
            //upper base.
            Plane upperBase = new Plane(p2, Va);
            GeoPoint p;

            //bottom base intersections.
            list = bottomBase.findGeoIntersections(ray);

            if (list != null) {
                p = list.get(0);
                //checks the intersections are on the cylinder bottom base.
                if (p.point.distanceSquared(p1) < radius * radius)
                    result.add(p);
            }

            //upper base intersections.
            list = upperBase.findGeoIntersections(ray);

            if (list != null) {
                p = list.get(0);
                //checks the intersections are on the cylinder upper base.
                if (p.point.distanceSquared(p2) < radius * radius)
                    result.add(p);
            }
        }
        //no intersections.
        return result.size() == 0 ? null : result;
    }

    @Override
    public String toString() {
        return "Cylinder{" + "height = " + height + ", axisRay = " + axisRay + ", radius = " + radius + '}';
    }
}
