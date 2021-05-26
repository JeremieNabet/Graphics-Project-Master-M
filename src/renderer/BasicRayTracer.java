package renderer;

import primitives.Color;
import primitives.Point3D;
import primitives.Ray;
import scene.Scene;

import java.util.List;

/**
 * Ray tracer basic is a class that extends from ray tracer base
 * that help me to trace the new ray in my figure
 */
public class BasicRayTracer extends RayTracerBase {
    /**
     * Constructor
     *
     * @param scene the scene
     */
    public BasicRayTracer(Scene scene) {
        super(scene);
    }

    /**
     * use to trace the ray
     * He is calculate the intersection of all my ray status
     *
     * @param ray a ray
     * @return the color or the background of my scene
     */
    @Override
    public Color traceRay(Ray ray) {
        List<Point3D> intersections = scene.geometries.findIntersections(ray);
        if (intersections != null) {
            Point3D closestPoint = ray.findClosestPoint(intersections);
            return calcColor(closestPoint);
        }
        //ray did not intersect any geometrical object
        return scene.background;
    }

    /**
     * use to calculate the color and to get the intensity about the ambient light
     * of the scene
     *
     * @param point on my scene to calculate the color
     * @return the color of my scene
     */
    private Color calcColor(Point3D point) {
        return scene.ambientLight.getIntensity();
    }
}