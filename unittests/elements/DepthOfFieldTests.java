package elements;

import geometries.*;
import org.junit.jupiter.api.Test;
import primitives.Color;
import primitives.Material;
import primitives.Point3D;
import primitives.Vector;
import renderer.BasicRayTracer;
import renderer.ImageWriter;
import renderer.Render;
import scene.Scene;

public class DepthOfFieldTests {


    @Test
    public void Project() {
        Scene scene = new Scene("Test scene");
        Camera camera = new Camera(new Point3D(-120, 0, -1000), new Vector(0, 0, 1), new Vector(0, -1, 0))
                .setDistance(1000)
                .setViewPlaneSize(350, 350)
                .setDepthOfFiled(50, 0.15, 200)
                .setNumOfRayForAntiAliasing(150);
        scene.setBackground(new Color(java.awt.Color.BLACK));
        scene.setAmbientLight(new AmbientLight(new Color(255, 255, 255), 0));

        scene.geometries.add(

                new Plane(new Point3D(-200, 300, 0), new Vector(0, 5, 0))
                        .setEmission(new Color(100, 100, 100))//
                        .setMaterial(new Material().setKd(1).setKs(1).setShininess(20).setKt(0.8))

        );
        for (int i = 1; i < 7; i++) {
            scene.geometries.add(


                    new Sphere(new Point3D(100 + -i * 100, 3, i * 200), 45) //
                            .setEmission(new Color(20, 0, 0))//
                            .setMaterial(new Material().setKd(1).setKs(1).setShininess(100)), //


                    new Sphere(new Point3D(100 + -i * 100, -70, i * 200), 30) //
                            .setEmission(new Color(0, 20, 0)) //
                            .setMaterial(new Material().setKd(1).setKs(1).setShininess(100)), //

                    new Sphere(new Point3D(100 + -i * 100, -115,  i * 200), 15) //
                            .setEmission(new Color(0, 0, 20)) //
                            .setMaterial(new Material().setKd(1).setKs(1).setShininess(100))//


            );




        }



        scene.lights.add(new DirectionalLight(new Color(50, 50, 50), new Vector(50, 180, 600)));
        scene.lights.add(new DirectionalLight(new Color(20,0,20), new Vector(50, 180, 600)));


        Render render1 = new Render()
                .setImageWriter(new ImageWriter("DepthOfFieldProject", 500, 500)) //
                .setCamera(camera) //
                .setRayTracer(new BasicRayTracer(scene))
                .setFlagDOF(true);
        render1.setMultithreading(3).setDebugPrint();
        render1.renderImage();
        render1.writeToImage();


        Render render2 = new Render()
                .setImageWriter(new ImageWriter("withoutDepthOfFieldProject", 500, 500)) //
                .setCamera(camera) //
                .setRayTracer(new BasicRayTracer(scene));
        render2.setMultithreading(3).setDebugPrint();
        render2.renderImage();
        render2.writeToImage();


    }
}





