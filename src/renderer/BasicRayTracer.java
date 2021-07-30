package renderer;

import elements.LightSource;
import geometries.Geometry;
import geometries.Intersectable.GeoPoint;
import primitives.*;
import scene.Scene;

import java.util.LinkedList;
import java.util.List;

import static primitives.Ray.rayRandomBeam;
import static primitives.Util.alignZero;

/**
 * class BasicRayTracer-responsible for coloring each pixel
 * in the right color according to the object
 */
public class BasicRayTracer extends RayTracerBase {

    /**
     * MAX_CALC_COLOR_LEVEL-Variable for limit recursion
     * MIN_CALC_COLOR_K-Variable for limit recursion
     * INITIAL_K-
     */
    private static final int MAX_CALC_COLOR_LEVEL = 10;
    private static final double MIN_CALC_COLOR_K = 0.001;
    private static final double INITIAL_K = 1.0;


    public BasicRayTracer(Scene scene) {
        super(scene);
    }

    /**
     * Find the intersection and the scene’s geometries
     * If there is no intersection, return the background color
     * Find the closest intersection point
     * Find the color of the intersection point (Ambient light)
     *
     * @param ray-the ray of the specific pixel
     * @return color
     */
    @Override
    public Color traceRay(Ray ray) {
        List<GeoPoint> intersections = scene.geometries.findGeoIntersections(ray);
        if (intersections != null) {
            GeoPoint closestPoint = ray.findClosestGeoPoint(intersections);
            return calcColor(closestPoint, ray)
                    .add(scene.ambientLight.getIntensity()
                            .add(closestPoint.geometry.getEmission()));
        }
        return scene.background;
    }

    /**
     * Calculate the average of a color in a point
     *
     * @param rays-the beam of ray of the specific pixel
     * @return color
     */
    @Override
    public Color traceRays(List<Ray> rays) {
        Color color = new Color(scene.background);
        for (Ray ray : rays) {
            color = color.add(traceRay(ray));
        }
        color = color.reduce(rays.size());
        return color;
    }


    /**
     * call to the recursive func calcColor
     * returns the color
     *
     * @param closestPoint-The closest point to the head of the ray
     * @param ray-the          ray of the specific pixel
     * @return color
     */
    private Color calcColor(GeoPoint closestPoint, Ray ray) {
        return calcColor(closestPoint, ray, MAX_CALC_COLOR_LEVEL, INITIAL_K)
                .add(scene.ambientLight.getIntensity());
    }


    /**
     * Fill environmental lighting color of the scene by call to calcLocalEffects and calcGlobalEffects
     *
     * @param intersection-The closest point to the head of the ray
     * @param ray-the          ray of the specific pixel
     * @param level-the        level of the recursion
     * @param k
     * @return
     */

    private Color calcColor(GeoPoint intersection, Ray ray, int level, double k) {
        Color color = intersection.geometry.getEmission();
        color = color.add(calcLocalEffects(intersection, ray, k));
        return 1 == level ? color : color.add(calcGlobalEffects(intersection, ray.getDirection(), level, k));
    }


    /**
     * The Phong Reflectance Model
     * calls the funcs calcDiffusive and calcSpecular
     * 𝒌𝑨 ∙ 𝑰𝑨 + 𝑰𝑬 +∑(𝒌𝑫∙|𝒍𝒊 ∙ 𝒏| + 𝒌𝑺 (∙ 𝒎𝒂𝒙 (𝟎, −𝒗 ∙ 𝒓))∙𝒏𝒔𝒉)∙ 𝑰𝑳𝒊 ∙𝑺𝒊
     *
     * @param geopoint-The closest point to the head of the ray
     * @param ray-the      ray of the specific pixel
     * @param k
     * @return
     */
    private Color calcLocalEffects(GeoPoint geopoint, Ray ray, double k) {
        Vector v = ray.getDirection();
        Vector n = geopoint.geometry.getNormal(geopoint.point);
        double nv = alignZero(n.dotProduct(v));
        if (nv == 0) {
            return Color.BLACK;
        }
        Material material = geopoint.geometry.getMaterial();
        int nShininess = material.nShininess;
        double kd = material.kD;
        double ks = material.kS;
        Color color = Color.BLACK;
        for (LightSource lightSource : scene.lights) {
            Vector l = lightSource.getL(geopoint.point);
            double nl = alignZero(n.dotProduct(l));
            if (nl * nv > 0) { // sign(nl) == sing(nv)
                double ktr = transparency(lightSource, l, n, geopoint);
                if (ktr * k > MIN_CALC_COLOR_K) {
                    Color lightIntensity = lightSource.getIntensity(geopoint.point).scale(ktr);
                    color = color.add(calcDiffusive(material.kD, l, n, lightIntensity),
                            calcSpecular(material.kS, l, n, v, nShininess, lightIntensity));
                }

               /* if (unshaded2(lightSource,l,n,geopoint)){
                    Color lightIntensity = lightSource.getIntensity(geopoint.point);
                    color = color.add(calcDiffusive(kd, l, n, lightIntensity),
                            calcSpecular(ks, l, n, v, nShininess, lightIntensity));
                }*/
            }
        }
        return color;
    }


    /**
     * calculates the specular light on geometry
     *
     * @param ks-the               coefficient of specular
     * @param l-color's            direction
     * @param n-geometry's         normal
     * @param v-ray's              direction
     * @param nShininess-Shininess of the point
     * @param lightIntensity-the   intensity of the light at the point
     * @return
     */
    private Color calcSpecular(double ks, Vector l, Vector n, Vector v, double nShininess, Color lightIntensity) {
        Vector r = l.subtract(n.scale(l.dotProduct(n) * 2));
        double vrMinus = v.scale(-1).dotProduct(r);
        double vrn = Math.pow(vrMinus, nShininess);
        return lightIntensity.scale(ks * vrn);


    }

    /**
     * calculates the diffusion of the light on the touched area
     *
     * @param kd-the             coefficient of diffusive
     * @param l-color's          direction
     * @param n-geometry's       normal
     * @param lightIntensity-the intensity of the light at the point
     * @return the color
     */
    private Color calcDiffusive(double kd, Vector l, Vector n, Color lightIntensity) {
        double ln = Math.abs(l.dotProduct(n));
        return lightIntensity.scale(kd * ln);
    }


    /**
     * Calculate shadow at point by by finding all the point that this ray meets in the scene,
     * and if there are no points then there is a shadow
     *
     * @param light-the    light source of the scene
     * @param l-color's    direction
     * @param n-geometry's normal
     * @param geopoint-The closest point to the head of the ray
     * @return true or false if there is a shadow
     */
    private boolean unshaded1(LightSource light, Vector l, Vector n, GeoPoint geopoint) {
        Vector lightDirection = l.scale(-1); // from point to light source

        Ray lightRay = new Ray(geopoint.point, lightDirection, n);
        List<GeoPoint> intersections = scene.geometries.findGeoIntersections(lightRay);
        if (intersections == null) {
            return true;
        }
        double lightDistance = light.getDistance(geopoint.point);
        for (GeoPoint gp : intersections) {
            if (alignZero(gp.point.distance(geopoint.point) - lightDistance) <= 0)
                return false;
        }
        return true;

        //return intersections == null;
    }

    /**
     * Calculate shadow at point
     *
     * @param light-the    light source of the scene
     * @param l-color's    direction
     * @param n-geometry's normal
     * @param geopoint-the closest point to the head of the ray
     * @return true or false if there is a shadow
     */
    private boolean unshaded2(LightSource light, Vector l, Vector n, GeoPoint geopoint) {
        Vector lightDirection = l.scale(-1); // from point to light source
        Ray lightRay = new Ray(geopoint.point, lightDirection, n);

        double dis = light.getDistance(geopoint.point);

        List<GeoPoint> intersections = scene.geometries
                .findGeoIntersections(lightRay, dis);
        return intersections == null;
    }

    /**
     * The Phong Reflectance  Model
     * Calculate the  Reflection and transparency by calling for funcs constructReflectedRay and  constructRefractedRay
     * 𝒌𝑹 ∙ 𝑰𝑹 + 𝒌𝑻 ∙𝑰𝑻
     *
     * @param gp-he     closest point to the head of the ray
     * @param v-ray's   direction
     * @param level-the level of the recursion
     * @param k
     * @return color
     */

    private Color calcGlobalEffects(GeoPoint gp, Vector v, int level, double k) {
        Color color = Color.BLACK;
        Vector n = gp.geometry.getNormal(gp.point);
        Material material = gp.geometry.getMaterial();
        double kkr = k * material.kR;
        if (kkr > MIN_CALC_COLOR_K)
            color = calcGlobalEffect(constructReflectedRay(gp.point, v, n), level, material.kR, kkr);
        double kkt = k * material.kT;
        if (kkt > MIN_CALC_COLOR_K)
            color = color.add(
                    calcGlobalEffect(constructRefractedRay(gp.point, v, n), level, material.kT, kkt));
        return color;
    }

    /**
     * called by calcGlobalEffect for Calculating the Reflection and transparency
     *
     * @param ray-refraction ray
     * @param level-the      level of the recursion
     * @param kx
     * @param kkx
     * @return
     */
    private Color calcGlobalEffect(Ray ray, int level, double kx, double kkx) {
        GeoPoint gp = findClosestIntersection(ray);
        return (gp == null ? scene.background : calcColor(gp, ray, level - 1, kkx)
        ).scale(kx);
    }

    /**
     * constructs the refraction ray
     *
     * @param point-the    closest point to the head of the ray
     * @param v-ray's      direction
     * @param n-geometry's normal
     * @return ray
     */
    private Ray constructRefractedRay(Point3D point, Vector v, Vector n) {
        return new Ray(point, v, n);
    }

    /**
     * Calculation of a reflected ray
     * 𝒓 = 𝒗 − 𝟐 ∙ (𝒗 ∙ 𝒏 )∙𝒏
     *
     * @param point-the    closest point to the head of the ray
     * @param v-ray's      direction
     * @param n-geometry's normal
     * @return ray
     */
    private Ray constructReflectedRay(Point3D point, Vector v, Vector n) {

        Vector r = v.subtract(n.scale(2 * v.dotProduct(n)));
        return new Ray(point, r);

    }


    /**
     * finding the intersection that this ray meets in the scene
     *
     * @param ray-the ray of the specific pixel
     * @return GeoPoint
     */
    private GeoPoint findClosestIntersection(Ray ray) {
        List<GeoPoint> pointList = scene.geometries.findGeoIntersections(ray);
        return ray.findClosestGeoPoint(pointList);
    }

    /**
     * Calculate the shadowing
     *
     * @param light-the    light source of the scene
     * @param l-color's    direction
     * @param n-geometry's normal
     * @param geopoint-the closest point to the head of the ray
     * @return Shadow coefficient
     */
    private double transparency(LightSource light, Vector l, Vector n, GeoPoint geopoint) {
        Vector lightDirection = l.scale(-1); // from point to light source
        Ray lightRay = new Ray(geopoint.point, lightDirection, n);
        double lightDistance = light.getDistance(geopoint.point);
        var intersections = scene.geometries.findGeoIntersections(lightRay);
        if (intersections == null) return 1.0;
        double ktr = 1.0;
        for (GeoPoint gp : intersections) {
            if (alignZero(gp.point.distance(geopoint.point) - lightDistance) <= 0) {
                ktr *= gp.geometry.getMaterial().kT;
                if (ktr < MIN_CALC_COLOR_K) return 0.0;
            }
        }
        return ktr;
    }


}

