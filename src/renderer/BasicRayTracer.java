package renderer;

import elements.LightSource;
import geometries.Intersectable;

import static geometries.Intersectable.GeoPoint;
import static primitives.Util.*;

import primitives.*;
import scene.*;

import java.util.List;

/**
 * Class which its role is to look for cuts between the ray and the 3D model of the scene,
 * and calculates the color in the closest intersection
 *
 * @author jeremie nabet and israel bellaiche
 */
public class BasicRayTracer extends RayTracerBase {

    /**
     * recursive calls > 10 then we stop making more readings
     */
    private static final int MAX_CALC_COLOR_LEVEL = 10;
    /**
     * color pixel effect's < 0.001 then its marge and will not consider it
     */
    private static final double MIN_CALC_COLOR_K = 0.001;
    /**
     * initialize the pixel color effect's
     */
    private static final double INITIAL_K = 1.0;

    /**
     * Constructor
     *
     * @param scene The scene where we look for the cuts
     */
    public BasicRayTracer(Scene scene) {
        super(scene);
    }

    @Override
    public Color traceRay(Ray ray) {
        GeoPoint geoPoint = findClosestIntersection(ray);
        return geoPoint == null ? scene.background : calcColor(geoPoint, ray);
    }

    /**
     * this function is a wrapping function for the calcColor
     * this function gets two arguments and send to the calcColor with 4 arguments
     *
     * @param closestPoint the geo point that sets the color of the pixel to the calculation
     * @param ray          the ray that constructed from the camera and intersected the geometry
     * @return the color of the point for calculating the color for the pixel
     */
    private Color calcColor(GeoPoint closestPoint, Ray ray) {
        return calcColor(closestPoint, ray, MAX_CALC_COLOR_LEVEL, INITIAL_K).add(scene.ambientLight.getIntensity());
    }

    /**
     * this function calculate the color of a point from calculating the color of the pixel
     *
     * @param intersection the geo point that sets the color of the pixel to the calculation
     * @param ray          the ray that constructed from the camera and intersected the geometry
     * @param level        the level of the recursion
     * @param k            comparison measure for the recursion
     * @return the color of the point for calculating the color for the pixel
     */
    private Color calcColor(GeoPoint intersection, Ray ray, int level, double k) {
        Color color = intersection.geometry.getEmission();
        color = color.add(calcLocalEffects(intersection, ray, k));
        return 1 == level ? color : color.add(calcGlobalEffects(intersection, ray, level, k));
    }

    /**
     * calculate the effects on the shape's material
     *
     * @param geoPoint given point
     * @param ray      given ray
     * @param k
     * @return color at given point
     */
    private Color calcLocalEffects(GeoPoint geoPoint, Ray ray, double k) {
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
            if (checkSign(nl, nV)) {
                double ktr = transparency(lightSource, l, n, geoPoint);
                if (ktr * k > MIN_CALC_COLOR_K) {
                    Color lightIntensity = lightSource.getIntensity(geoPoint.point).scale(ktr);
                    color = color.add(calcDiffusive(kd, l, n, lightIntensity), calcSpecular(ks, l, n, v, nShininess, lightIntensity));
                }
            }
        }
        return color;
    }

    /**
     * @param geoPoint
     * @param ray
     * @param level
     * @param k
     * @return
     */
    private Color calcGlobalEffects(GeoPoint geoPoint, Ray ray, int level, double k) {
        Color color = Color.BLACK;
        Material material = geoPoint.geometry.getMaterial();
        double kr = material.getKr();
        double kkr = k * kr;
        Vector n = geoPoint.geometry.getNormal(geoPoint.point);
        if (kkr > MIN_CALC_COLOR_K) {
            Ray reflectedRay = constructReflectedRay(n, geoPoint.point, ray);
            GeoPoint reflectedPoint = findClosestIntersection(reflectedRay);
            if (reflectedPoint != null) {
                color = color.add(calcColor(reflectedPoint, reflectedRay, level - 1, kkr).scale(kr));
            }
        }
        double kt = material.kt;
        double kkt = k * kt;
        if (kkt > MIN_CALC_COLOR_K) {
            Ray refractedRay = constructRefractedRay(n, geoPoint.point, ray);
            GeoPoint refractedPoint = findClosestIntersection(refractedRay);
            if (refractedPoint != null) {
                color = color.add(calcColor(refractedPoint, refractedRay, level - 1, kkt).scale(kt));
            }
        }
        return color;
    }

    /**
     * this function construct a refraction ray from the ray intersected withe the geometry
     *
     * @param n     the normal vector to the intersected point
     * @param point the intersected point
     * @param ray   the vector from the light source
     * @return refraction vector
     */
    private Ray constructRefractedRay(Vector n, Point3D point, Ray ray) {
        return new Ray(point, ray.getDirection(), n);
    }

    /**
     * this function construct a reflection ray from the ray intersected withe the geometry
     *
     * @param n     the normal vector to the intersected point
     * @param point the intersected point
     * @param ray   the vector from the light source
     * @return reflection vector
     */
    private Ray constructReflectedRay(Vector n, Point3D point, Ray ray) {
        // r = v - 2 * (v * n) * n
        Vector v = ray.getDirection();
        Vector r = null;
        try {
            r = v.subtract(n.scale(v.dotProduct(n)).scale(2)).normalized();
        } catch (Exception e) {
            return null;
        }
        return new Ray(point, r, n);
    }

    /**
     * this function calculates the closest intersection
     *
     * @param ray the ray constructed
     * @return the closest intersection point
     */
    private GeoPoint findClosestIntersection(Ray ray) {
        List<GeoPoint> geoPoints = scene.geometries.findGeoIntersections(ray);
        return ray.findClosestGeoPoint(geoPoints);
    }

    /**
     * this function calculate the influence of shadows
     *
     * @param ls       the light source that illuminates the geometry
     * @param l        the light direction
     * @param n        the normal
     * @param geoPoint the intersected point
     * @return
     */
    private double transparency(LightSource ls, Vector l, Vector n, GeoPoint geoPoint) {
        Point3D point = geoPoint.point;
        Vector lightDirection = l.scale(-1); // from point to light source
        // ray from point toward light direction offset by delta
        Ray lightRay = new Ray(point, lightDirection, n);
        double lightDistance = ls.getDistance(point);
        var intersections = scene.geometries.findGeoIntersections(lightRay, ls.getDistance(lightRay.getP0()));
        if (intersections == null) {
            return 1.0;
        }
        double ktr = 1.0;
        for (GeoPoint gp : intersections) {
            if (alignZero(gp.point.distance(point) - lightDistance) <= 0) {
                ktr *= gp.geometry.getMaterial().getKt();
                if (ktr < MIN_CALC_COLOR_K) {
                    return 0.0;
                }
            }
        }
        return ktr;
    }

    /**
     * a helper method for calculating the diffusive color
     *
     * @param kd             material attenuation coefficient
     * @param l              vector from the light source
     * @param n              the normal
     * @param lightIntensity the color calculated without the diffusive
     * @return the color caused by the diffusive influence
     */
    private Color calcDiffusive(double kd, Vector l, Vector n, Color lightIntensity) {
        double ln = Math.abs(l.dotProduct(n));
        return lightIntensity.scale(kd * ln);
    }

    /**
     * a helper method for calculating the specular color
     *
     * @param ks             material attenuation coefficient
     * @param l              the vector from the light source
     * @param n              the normal vector to the intersected point
     * @param v              vector construct from the camera to the intersected point
     * @param nShininess     glitter coefficient of the material
     * @param lightIntensity the color calculated without the specular
     * @return the color caused by the specular influence
     */
    private Color calcSpecular(double ks, Vector l, Vector n, Vector v, int nShininess, Color lightIntensity) {
        Vector r = l.subtract(n.scale(l.dotProduct(n) * 2));
        double vrMinus = v.scale(-1).dotProduct(r);
        double vrn = Math.pow(vrMinus, nShininess);
        return lightIntensity.scale(ks * vrn);
    }

}