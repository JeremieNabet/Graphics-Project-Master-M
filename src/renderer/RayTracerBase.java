package renderer;

import primitives.Color;
import primitives.Ray;
import scene.Scene;

import java.util.List;

/**
 * allows me to draw my base of my rays
 */
public abstract class RayTracerBase {
    /**
     * scene
     */
    protected Scene scene;

    /**
     * Constructor
     *
     * @param scene the scene
     */
    public RayTracerBase(Scene scene) {
        this.scene = scene;
    }

    /**
     * use to trace the ray
     * He is calculate the intersection of all my ray status
     *
     * @param ray a ray
     * @return the color or the background of my scene
     */
    public abstract Color traceRay(Ray ray);


    public abstract Color traceRays(List<Ray> rayList);
}