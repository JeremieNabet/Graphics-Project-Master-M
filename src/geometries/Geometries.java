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

    @Override
    public List<GeoPoint> findGeoIntersections(Ray ray, double maxDistance) {
        List<GeoPoint> result = null;
        for (Intersectable item : this.intersectables) {
            List<GeoPoint> itemPoints = item.findGeoIntersections(ray, maxDistance);
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
