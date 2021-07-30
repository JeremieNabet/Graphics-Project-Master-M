package elements;

import geometries.Sphere;
import org.junit.jupiter.api.Test;
import primitives.Color;
import primitives.Point3D;
import primitives.Vector;
import renderer.BasicRayTracer;
import renderer.ImageWriter;
import renderer.Render;
import scene.Scene;

public class AntiAleas {
    /**
     * Produce a scene with basic 3D model and render it into a jpeg image with a
     * grid
     */
    @Test
    public void AntiAliasing() {
        Camera camera = new Camera(new Point3D(0, 0, 10000), new Vector(0, 0, -1), new Vector(0, 1, 0)) //
                .setDistance(9600) //
                .setViewPlaneSize(1000, 1000)
                .setNumOfRayForAntiAliasing(150);
        Scene scene = new Scene("Anti Aliasing test")//
                .setAmbientLight(new AmbientLight(new Color(255, 191, 191), 1)) //
                .setBackground(new Color(0, 0, 0));

        scene.geometries.add(new Sphere(new Point3D(0, 0, -100), 500)

        ); // down
        // right
        int p = 1000;
        ImageWriter imageWriter = new ImageWriter("base whit Anti Aliasing", p, p);
        Render render = new Render() //
                .setImageWriter(imageWriter) //
                .setCamera(camera) //
                .setRayTracer(new BasicRayTracer(scene)) .setFlagDOF(true)//
                //
                ;

        render.renderImage();
        render.writeToImage();

        camera = new Camera(new Point3D(0, 0, 10000), new Vector(0, 0, -1), new Vector(0, 1, 0)) //
                .setDistance(9600) //
                .setViewPlaneSize(1000, 1000);
        ImageWriter imageWriter1 = new ImageWriter("base without Anti Aliasing", p, p);
        Render render1 = new Render() //
                .setImageWriter(imageWriter1) //
                .setCamera(camera) //
                .setRayTracer(new BasicRayTracer(scene));//

        render1.renderImage();
        render1.writeToImage();
    }
}
