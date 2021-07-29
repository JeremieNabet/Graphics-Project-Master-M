/**
 *
 */
package elements;

import geometries.*;
import org.junit.jupiter.api.Test;
import elements.*;
import primitives.*;
import renderer.*;
import scene.Scene;


/**
 *
 * Tests for reflection and transparency functionality, test for partial shadows
 * (with transparency)
 *
 * @author dzilb
 */
public class ReflectionRefractionTests {
    private Scene scene = new Scene("Test scene");

    /**
     * Produce a picture of a sphere lighted by a spot light
     */
    @Test
    public void twoSpheres() {
        Camera camera = new Camera(new Point3D(0, 0, 1000), new Vector(0, 0, -1), new Vector(0, 1, 0)) //
                .setViewPlaneSize(150, 150).setDistance(1000);

        scene.geometries.add( //
                new Sphere(new Point3D(0, 0, -50), 50) //
                        .setEmission(new Color(java.awt.Color.BLUE)) //
                        .setMaterial(new Material().setKd(0.4).setKs(0.3).setShininess(100).setKt(0.3)),
                new Sphere(new Point3D(0, 0, -50), 25) //
                        .setEmission(new Color(java.awt.Color.RED)) //
                        .setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(100)));
        scene.lights.add( //
                new SpotLight(new Color(1000, 600, 0), new Point3D(-100, -100, 500), new Vector(-1, -1, -2)) //
                        .setKl(0.0004).setKq(0.0000006));

        Render render = new Render() //
                .setImageWriter(new ImageWriter("refractionTwoSpheres", 500, 500)) //
                .setCamera(camera) //
                .setRayTracer(new BasicRayTracer(scene));
        render.renderImage();
        render.writeToImage();
    }

    @Test
    public void trianglesTransparentSphere() {
        Camera camera = new Camera(new Point3D(0, 0, 1000), new Vector(0, 0, -1), new Vector(0, 1, 0)) //
                .setViewPlaneSize(200, 200).setDistance(1000);

        scene.setAmbientLight(new AmbientLight(new Color(java.awt.Color.WHITE), 0.15));

        scene.geometries.add( //
                new Triangle(new Point3D(-150, -150, -115), new Point3D(150, -150, -135), new Point3D(75, 75, -150)) //
                        .setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(60)), //
                new Triangle(new Point3D(-150, -150, -115), new Point3D(-70, 70, -140), new Point3D(75, 75, -150)) //
                        .setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(60)), //
                new Sphere(new Point3D(60, 50, -50), 30) //
                        .setEmission(new Color(java.awt.Color.BLUE)) //
                        .setMaterial(new Material().setKd(0.2).setKs(0.2).setShininess(30).setKt(0.6)));

        scene.lights.add(new SpotLight(new Color(700, 400, 400), new Point3D(60, 50, 0), new Vector(0, 0, -1)) //
                .setKl(4E-5).setKq(2E-7));

        ImageWriter imageWriter = new ImageWriter("refractionShadow", 600, 600);
        Render render = new Render() //
                .setImageWriter(imageWriter) //
                .setCamera(camera) //
                .setRayTracer(new BasicRayTracer(scene));

        render.renderImage();
        render.writeToImage();
    }

    /**
     * Our picture
     */

    @Test
    public void picture()
    {

        Camera camera=new Camera(new Point3D(0, 0, -1000), new Vector(0, 0, 1), new Vector(0, -1, 0))
                .setViewPlaneSize(200, 200).setDistance(1000);
        //scene.setAmbientLight(new AmbientLight(new Color(java.awt.Color.WHITE), 0.15));

        scene.geometries.add(

                new Sphere(new Point3D(-50, -70, 45), 30) //
                        .setEmission(new Color(0,0,20)) //
                        .setMaterial(new Material().setKd(0.3).setKs(0.7).setShininess(100).setKt(0.6)), //
                new Sphere(new Point3D(-50, -70, 45), 20) //
                        .setEmission(new Color(0,0,20)) //
                        .setMaterial(new Material().setKd(0.3).setKs(0.7).setShininess(100).setKt(0.6)), //
                new Sphere(new Point3D(0, -10, 20), 30) //
                        .setEmission(new Color(0,20,0)) //
                        .setMaterial(new Material().setKd(0.3).setKs(0.7).setShininess(100).setKt(0.6)), //
                new Sphere(new Point3D(0, -10, 20), 20) //
                        .setEmission(new Color(0,20,0)) //
                        .setMaterial(new Material().setKd(0.3).setKs(0.7).setShininess(100).setKt(0.6)), //
                new Sphere(new Point3D(50, 50, 45), 30) //
                        .setEmission(new Color(20,0,0)) //
                        .setMaterial(new Material().setKd(0.3).setKs(0.7).setShininess(100).setKt(0.6)), //
                new Sphere(new Point3D(50, 50, 45), 20) //
                        .setEmission(new Color(20,0,0)) //
                        .setMaterial(new Material().setKd(0.3).setKs(0.7).setShininess(100).setKt(0.6)), //
                new Plane(new Point3D(0, 0, 120), new Vector(0, 0, 1)) //
                        .setEmission(new Color(java.awt.Color.BLACK)) //
                        .setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(1200).setKt(0.8)) //
        );

        scene.lights.add(new DirectionalLight(new Color(java.awt.Color.WHITE), new Vector(-1,0.3,1)));//

        ImageWriter imageWriter = new ImageWriter("picture", 600, 600);
        Render render = new Render() //
                .setImageWriter(imageWriter) //
                .setCamera(camera) //
                .setRayTracer(new BasicRayTracer(scene));

        render.renderImage();
        render.writeToImage();

    }

    /**
     * The picture from another angle
     */

    @Test
    public void pictureMove()
    {
        Camera camera=new Camera(new Point3D(300, -10, -1800), new Vector(-0.1, 0, 0.8), new Vector(0, -1, 0))
                .setViewPlaneSize(200, 200).setDistance(1000);

        scene.geometries.add(

                new Sphere(new Point3D(-50, -70, 45), 30) //
                        .setEmission(new Color(0,0,20)) //
                        .setMaterial(new Material().setKd(0.3).setKs(0.7).setShininess(100).setKt(0.6)), //
                new Sphere(new Point3D(-50, -70, 45), 20) //
                        .setEmission(new Color(0,0,20)) //
                        .setMaterial(new Material().setKd(0.3).setKs(0.7).setShininess(100).setKt(0.6)), //
                new Sphere(new Point3D(0, -10, 20), 30) //
                        .setEmission(new Color(0,20,0)) //
                        .setMaterial(new Material().setKd(0.3).setKs(0.7).setShininess(100).setKt(0.6)), //
                new Sphere(new Point3D(0, -10, 20), 20) //
                        .setEmission(new Color(0,20,0)) //
                        .setMaterial(new Material().setKd(0.3).setKs(0.7).setShininess(100).setKt(0.6)), //
                new Sphere(new Point3D(50, 50, 45), 30) //
                        .setEmission(new Color(20,0,0)) //
                        .setMaterial(new Material().setKd(0.3).setKs(0.7).setShininess(100).setKt(0.6)), //
                new Sphere(new Point3D(50, 50, 45), 20) //
                        .setEmission(new Color(20,0,0)) //
                        .setMaterial(new Material().setKd(0.3).setKs(0.7).setShininess(100).setKt(0.6)), //
                new Plane(new Point3D(0, 0, 120), new Vector(0, 0, 1)) //
                        .setEmission(new Color(java.awt.Color.BLACK)) //
                        .setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(1200).setKt(0.8)) //
        );

        scene.lights.add(new DirectionalLight(new Color(java.awt.Color.WHITE), new Vector(-1,0.3,1)));//

        ImageWriter imageWriter = new ImageWriter("pictureMove", 600, 600);
        Render render = new Render() //
                .setImageWriter(imageWriter) //
                .setCamera(camera) //
                .setRayTracer(new BasicRayTracer(scene));

        render.renderImage();
        render.writeToImage();

    }


}


