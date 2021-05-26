package geometries;

import primitives.Point3D;
import primitives.Ray;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * Geometries class which implements Intersectable
 */
public class Geometries implements Intersectable {
    /**
     * List which contains all my interceptions
     */
    private List<Intersectable> intersectables = new LinkedList<>();

    /**
     * Constructor Geometries which uses the add function in recursive
     *
     * @param geos
     */
    public Geometries(Intersectable... geos) {
        add(geos);
    }

    /**
     * Add function which allows me to add all my values to my list
     *
     * @param geos
     */
    public void add(Intersectable... geos) {
        Collections.addAll(intersectables, geos);
    }

    /**
     * allows me to find the intersection points of my Geometries values
     * if the points are not found, the function returns null
     *
     * @param ray
     * @return list of intersection points
     */
    @Override
    public List<Point3D> findIntersections(Ray ray) {
        List<Point3D> result = null;
        for (Intersectable item : intersectables) {
            //get intersections points of a particular item from intersectables
            List<Point3D> itemPoints = item.findIntersections(ray);

            if (itemPoints != null) {
                //first time initialize result to new LinkedList
                if (result == null)
                    result = new LinkedList<>(itemPoints);
                else
                    //add all item points to the resulting list
                    result.addAll(itemPoints);
            }
        }
        return result;
    }

    /**
     * check ray Geo intersections
     *
     * @param ray given ray
     * @return list that includes all the Geo Intersection points
     */
    @Override
    public List<GeoPoint> findGeoIntersections(Ray ray) {
        List<GeoPoint> result = null;
        for (Intersectable item : this.intersectables) {
            List<GeoPoint> itemPoints = item.findGeoIntersections(ray);
            if (itemPoints != null) {
                if (result == null) {
                    result = new LinkedList<>();
                }
                result.addAll(itemPoints);
            }
        }
        return result;
    }

}
