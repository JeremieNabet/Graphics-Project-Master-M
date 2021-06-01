package renderer;


import elements.LightSource;
import geometries.Intersectable;

import static geometries.Intersectable.GeoPoint;
import static primitives.Util.alignZero;
import static primitives.Util.isZero;

import primitives.*;
import scene.*;

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

    @Override
    public Color traceRay(Ray ray) {
        List<GeoPoint> points = scene.geometries.findGeoIntersections(ray);
        GeoPoint point;
        if (points == null) {
            return scene.background;
        } else {
            point = ray.findClosestGeoPoint(points);
            return calcColor(point, ray);
        }
    }

    /**
     * use to calculate the color and to get the intensity about the ambient light
     * of the scene
     *
     * @param geoPoint on my scene to calculate the color
     * @param ray      given ray
     * @return the color of my scene
     */
    public Color calcColor(GeoPoint geoPoint, Ray ray) {
        Color emission = geoPoint.geometry.getEmission();
        Color basicColor = scene.ambientLight.getIntensity().add(emission);
        return basicColor.add(calcLocalEffects(geoPoint, ray));
    }

    /**
     * calculate the effects on the shape's material
     *
     * @param geoPoint given point
     * @param ray      given ray
     * @return color at given point
     */
    private Color calcLocalEffects(Intersectable.GeoPoint geoPoint, Ray ray) {
        Vector v = ray.getDirection();
        Vector n = geoPoint.geometry.getNormal(geoPoint.point);
        double nV = alignZero(n.dotProduct(v));
        if (isZero(nV)) {
            return Color.BLACK;
        }
        Material material = geoPoint.geometry.getMaterial();
        int nShininess = material.nShininess;
        double kd = material.kd;
        double ks = material.ks;
        Color color = Color.BLACK;
        for (LightSource lightSource : scene.lights) {
            Vector l = lightSource.getL(geoPoint.point);
            double nl = alignZero(n.dotProduct(l));
            // check they got the same sign (the light and the camera are in the same side of the given point)
            if (nl * nV > 0) {
                Color lightIntensity = lightSource.getIntensity(geoPoint.point);
                color = color.add(calcDiffusive(kd, l, n, lightIntensity), calcSpecular(ks, l, n, v, nShininess, lightIntensity));
            }
        }
        return color;
    }

    /**
     * calculate the diffusive
     *
     * @param kd
     * @param l
     * @param n
     * @param lightIntensity
     * @return
     */
    private Color calcDiffusive(double kd, Vector l, Vector n, Color lightIntensity) {
        double ln = Math.abs(l.dotProduct(n));
        return lightIntensity.scale(kd * ln);
    }

    /**
     * calculates the glossy of the material
     *
     * @param ks
     * @param l
     * @param n
     * @param v
     * @param nShininess
     * @param lightIntensity
     * @return
     */
    private Color calcSpecular(double ks, Vector l, Vector n, Vector v, int nShininess, Color lightIntensity) {
        Vector r = l.subtract(n.scale(l.dotProduct(n) * 2));
        double vrMinus = v.scale(-1).dotProduct(r);
        double vrn = Math.pow(vrMinus, nShininess);
        return lightIntensity.scale(ks * vrn);
    }
}