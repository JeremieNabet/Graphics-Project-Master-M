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
     * cylinder's radius
     */
    private double radius;
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
     * get height.
     *
     * @return height
     */
    public double getHeight() {
        return height;
    }

    /**
     * Radius getter
     *
     * @return radius of my cylinder
     */
    public double getRadius() {
        return radius;
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
    public List<Point3D> findIntersections(Ray ray) {
//        //todo rethink the all thing
//        List<Point3D> result = super.findIntersections(ray);
//        if(result != null){
//            Point3D p = result.get(0);
//            Vector v = p.subtract(_axis.getP0());
//           //todo
//        }
//        //todo do the caps
//        return result;

        Vector vAxis = axisRay.getDirection();
        Vector v = ray.getDirection();
        Point3D p0 = ray.getP0();
        Point3D pC = axisRay.getP0();
        Point3D p1;
        Point3D p2;

        // intersections of the ray with infinite cylinder {without the bases)
        List<Point3D> intersections = super.findIntersections(ray);
        double vAxisV = alignZero(vAxis.dotProduct(v)); // cos(angle between ray directions)

        if (intersections == null) { // no intersections with the infinite cylinder
            try {
                vAxis.crossProduct(v); // if ray and axis are parallel - throw exception
                return null; // they are not parallel - the ray is outside the cylinder
            } catch (Exception e) {
            }

            // The rays are parallel
            Vector vP0PC;
            try {
                vP0PC = pC.subtract(p0); // vector from P0 to Pc (O1)
            } catch (Exception e) { // the rays start at the same point
                // check whether the ray goes into the cylinder
                return vAxisV > 0 ? //
                        List.of(ray.getPoint(height)) : null;
            }

            double t1 = alignZero(vP0PC.dotProduct(v)); // project Pc (O1) on the ray
            p1 = ray.getPoint(t1); // intersection point with base1

            // Check the distance between the rays
            if (alignZero(p1.distance(pC) - radius) >= 0)
                return null;

            // intersection points with base2
            double t2 = alignZero(vAxisV > 0 ? t1 + height : t1 - height);
            p2 = ray.getPoint(t2);
            // the ray goes through the bases - test bases vs. ray head and return points
            // accordingly
            if (t1 > 0 && t2 > 0)
                return List.of(p1, p2);
            if (t1 > 0)
                return List.of(p1);
            if (t2 > 0)
                return List.of(p2);
            return null;
        }

        // Ray - infinite cylinder intersection points
        p1 = intersections.get(0);
        p2 = intersections.get(1);

        // get projection of the points on the axis vs. base1 and update the
        // points if both under base1 or they are from different sides of base1
        double p1OnAxis = alignZero(p1.subtract(pC).dotProduct(vAxis));
        double p2OnAxis = alignZero(p2.subtract(pC).dotProduct(vAxis));
        if (p1OnAxis < 0 && p2OnAxis < 0)
            p1 = null;
        else if (p1OnAxis < 0)
            p1 = base1.findIntersections(ray).get(0);
        else
            /* p2OnAxis < 0 */ p2 = base1.findIntersections(ray).get(0);

        // get projection of the points on the axis vs. base2 and update the
        // points if both above base2 or they are from different sides of base2
        double p1OnAxisMinusH = alignZero(p1OnAxis - height);
        double p2OnAxisMinusH = alignZero(p2OnAxis - height);
        if (p1OnAxisMinusH > 0 && p2OnAxisMinusH > 0)
            p1 = null;
        else if (p1OnAxisMinusH > 0)
            p1 = base2.findIntersections(ray).get(0);
        else
            /* p2OnAxisMinusH > 0 */ p2 = base2.findIntersections(ray).get(0);

        // Check the points and return list of points accordingly
        if (p1 != null && p2 != null)
            return List.of(p1, p2);
        if (p1 != null)
            return List.of(p1);
        if (p2 != null)
            return List.of(p2);
        return null;
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
