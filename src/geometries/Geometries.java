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
                if (result == null) {
                    result = new LinkedList<>();
                }
                //add all item points to the resulting list
                result.addAll(itemPoints);
            }
        }
        return result;
    }
}
