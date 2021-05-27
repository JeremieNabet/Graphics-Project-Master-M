package elements;

import geometries.Sphere;
import org.junit.jupiter.api.*;
import geometries.*;
import primitives.*;
import renderer.*;
import scene.*;
import java.util.List;

/**
 * Test rendering a basic image
 */
class LightTests {
    private Scene.SceneBuilder sceneBuilder1 = new Scene.SceneBuilder("Test scene");
    private Scene.SceneBuilder sceneBuilder2 = new Scene.SceneBuilder("Test scene") //
            .setAmbientLight(new AmbientLight(new Color(java.awt.Color.WHITE), 0.15));
    private Camera.CameraBuilder cameraBuilder1 =
            new Camera.CameraBuilder(
                    new Point3D(0, 0, 1000),
                    new Vector(0, 0, -1),
                    new Vector(0, 1, 0)) //
                    .setViewPlaneSize(150, 150) //
                    .setDistance(1000);
    private Camera camera1 = cameraBuilder1.build();
    private Camera.CameraBuilder cameraBuilder2 =
            new Camera.CameraBuilder(
                    new Point3D(0, 0, 1000),
                    new Vector(0, 0, -1),
                    new Vector(0, 1, 0)) //
                    .setViewPlaneSize(200, 200) //
                    .setDistance(1000);
    private Camera camera2 = cameraBuilder2.build();

    private static Geometry triangle1 = new Triangle( //
            new Point3D(-150, -150, -150),
            new Point3D(150, -150, -150),
            new Point3D(75, 75, -150));
    private static Geometry triangle2 = new Triangle( //
            new Point3D(-150, -150, -150),
            new Point3D(-70, 70, -50),
            new Point3D(75, 75, -150));
    private static Geometry sphere = new Sphere(new Point3D(0, 0, -50), 50) //
            .setEmission(new Color(java.awt.Color.BLUE)) //
            .setMaterial(new Material().setKd(0.5).setKs(0.5).setNShininess(100));

    /**
     * Produce a picture of a sphere lighted by a directional light
     */
    @Test
    public void sphereDirectional() {
        sceneBuilder1.setGeometries(new Geometries(sphere));
        sceneBuilder1.setLights(
                List.of(new DirectionalLight(new Color(500, 300, 0), new Vector(1, 1, -1))));
        Scene scene1 = sceneBuilder1.build();
        ImageWriter imageWriter = new ImageWriter("lightSphereDirectional", 1000, 1000);
        Render.RenderBuilder renderBuilder = new Render.RenderBuilder()//
                .setImageWriter(imageWriter) //
                .setCamera(camera1) //
                .setRayTracer(new BasicRayTracer(scene1));
        Render render = renderBuilder.build();
        render.renderImage();
        render.writeToImage();
    }

    /**
     * Produce a picture of a sphere lighted by a point light
     */
    @Test
    public void spherePoint() {
        sceneBuilder1.setGeometries(new Geometries(sphere));
        sceneBuilder1.setLights(
                List.of(new PointLight(
                        new Color(500, 300, 0), new Point3D(-50, -50, 50))//
                        .setKl(0.00001).setKq(0.000001)));
        Scene scene1 = sceneBuilder1.build();
        ImageWriter imageWriter = new ImageWriter("lightSpherePoint", 500, 500);
        Render.RenderBuilder renderBuilder = new Render.RenderBuilder()//
                .setImageWriter(imageWriter) //
                .setCamera(camera1) //
                .setRayTracer(new BasicRayTracer(scene1));
        Render render = renderBuilder.build();
        render.renderImage();
        render.writeToImage();
    }

    /**
     * Produce a picture of a sphere lighted by a spot light
     */
    @Test
    public void sphereSpot() {
        sceneBuilder1.setGeometries(new Geometries(sphere));
        sceneBuilder1.setLights(
                List.of(new SpotLight(
                        new Color(500, 300, 0),
                        new Point3D(-50, -50, 50),
                        new Vector(1, 1, -2)) //
                        .setKl(0.00001)
                        .setKq(0.00000001)));
        Scene scene1 = sceneBuilder1.build();
        ImageWriter imageWriter = new ImageWriter("lightSphereSpot", 500, 500);
        Render.RenderBuilder renderBuilder = new Render.RenderBuilder()//
                .setImageWriter(imageWriter) //
                .setCamera(camera1) //
                .setRayTracer(new BasicRayTracer(scene1));
        Render render = renderBuilder.build();
        render.renderImage();
        render.writeToImage();
    }

    /**
     * Produce a picture of a two triangles lighted by a directional light
     */
    @Test
    public void trianglesDirectional() {
        sceneBuilder2.setGeometries(new Geometries(
                triangle1.setMaterial(new Material().setKd(0.8).setKs(0.2).setNShininess(300)), //
                triangle2.setMaterial(new Material().setKd(0.8).setKs(0.2).setNShininess(300))));
        sceneBuilder2.setLights(
                List.of(new DirectionalLight(new Color(300, 150, 150), new Vector(0, 0, -1))));
        Scene scene2 = sceneBuilder2.build();
        ImageWriter imageWriter = new ImageWriter("lightTrianglesDirectional", 500, 500);
        Render.RenderBuilder renderBuilder = new Render.RenderBuilder()//
                .setImageWriter(imageWriter) //
                .setCamera(camera2) //
                .setRayTracer(new BasicRayTracer(scene2));
        Render render = renderBuilder.build();
        render.renderImage();
        render.writeToImage();
    }

    /**
     * Produce a picture of a two triangles lighted by a point light
     */
    @Test
    public void trianglesPoint() {
        sceneBuilder2.setGeometries(
                new Geometries(
                        triangle1.setMaterial(new Material().setKd(0.5).setKs(0.5).setNShininess(300)), //
                        triangle2.setMaterial(new Material().setKd(0.5).setKs(0.5).setNShininess(300))));
        sceneBuilder2.setLights(
                List.of(new PointLight(new Color(500, 250, 250), new Point3D(10, -10, -130)) //
                        .setKl(0.0005)
                        .setKq(0.0005)));
        Scene scene2 = sceneBuilder2.build();

        ImageWriter imageWriter = new ImageWriter("lightTrianglesPoint", 500, 500);
        Render.RenderBuilder renderBuilder = new Render.RenderBuilder()//
                .setImageWriter(imageWriter) //
                .setCamera(camera2) //
                .setRayTracer(new BasicRayTracer(scene2));
        Render render = renderBuilder.build();
        render.renderImage();
        render.writeToImage();
    }

    /**
     * Produce a picture of a two triangles lighted by a spot light
     */
    @Test
    public void trianglesSpot() {
        Geometries geometries = new Geometries(
                triangle1.setMaterial(new Material().setKd(0.5).setKs(0.5).setNShininess(300)),
                triangle2.setMaterial(new Material().setKd(0.5).setKs(0.5).setNShininess(300)));
        sceneBuilder2.setGeometries(geometries);
        sceneBuilder2.setLights(
                List.of(new SpotLight(
                        new Color(500, 250, 250),
                        new Point3D(10, -10, -130),
                        new Vector(-2, -2, -1)) //
                        .setKl(0.0001)
                        .setKq(0.000005)));
        Scene scene2 = sceneBuilder2.build();

        ImageWriter imageWriter = new ImageWriter("lightTrianglesSpot", 500, 500);
        Render.RenderBuilder renderBuilder = new Render.RenderBuilder()//
                .setImageWriter(imageWriter) //
                .setCamera(camera2) //
                .setRayTracer(new BasicRayTracer(scene2));
        Render render = renderBuilder.build();
        render.renderImage();
        render.writeToImage();
    }

    /**
     * Produces an image of two triangles illuminated by a number of lights
     */
    @Test
    public void trianglesMultiLight() {
        Geometries geometries = new Geometries(
                triangle1.setMaterial(new Material().setKd(0.5).setKs(0.5).setNShininess(300)),
                triangle2.setMaterial(new Material().setKd(0.5).setKs(0.5).setNShininess(300)));
        sceneBuilder2.setGeometries(geometries);
        sceneBuilder2.setLights(List.of(
                new SpotLight(
                        new Color(100, 250, 0),
                        new Point3D(20, 50, -50),
                        new Vector(0, -2, -1)),
                new SpotLight(
                        new Color(30, 200, 100),
                        new Point3D(20, 10, -150),
                        new Vector(0, -2, -1)),
                new SpotLight(
                        new Color(300, 150, 300),
                        new Point3D(50, 50, -150),
                        new Vector(0, -2, -1)),
                new PointLight(
                        new Color(0,0,250),
                        new Point3D(0,0,-145)),
                new DirectionalLight(new Color(100,0,0),new Vector( 1,1,-1))));
        Scene scene2 = sceneBuilder2.build();

        ImageWriter imageWriter = new ImageWriter("multiLightsTriangles", 500, 500);
        Render.RenderBuilder renderBuilder = new Render.RenderBuilder()//
                .setImageWriter(imageWriter) //
                .setCamera(camera2) //
                .setRayTracer(new BasicRayTracer(scene2));
        Render render = renderBuilder.build();
        render.renderImage();
        render.writeToImage();
    }

    /**
     * Produces an image of sphere illuminated by a number of lights
     */
    @Test
    public void sphereMultiLight() {
        sceneBuilder1.setGeometries(new Geometries(sphere));
        sceneBuilder1.setLights(
                List.of(
                        new SpotLight(
                                new Color(200, 110, 0),
                                new Point3D(60, 20, 10),
                                new Vector(0, -2, -1)),
                        new SpotLight(
                                new Color(250, 450, 0),
                                new Point3D(90, 40, 20),
                                new Vector(0, -2, -1)),
                        new SpotLight(new Color(350, 350, 0),
                                new Point3D(40, 0, 10),
                                new Vector(0, -2, -1)),
                        new PointLight(
                                new Color(400,0,0),
                                new Point3D(200,200,-50)),
                        new DirectionalLight(new Color(400,250,100),new Vector( 15,15,-10))));//
        Scene scene1 = sceneBuilder1.build();
        ImageWriter imageWriter = new ImageWriter("multiLightSphere", 500, 500);
        Render.RenderBuilder renderBuilder = new Render.RenderBuilder()//
                .setImageWriter(imageWriter) //
                .setCamera(camera1) //
                .setRayTracer(new BasicRayTracer(scene1));
        Render render = renderBuilder.build();
        render.renderImage();
        render.writeToImage();
    }
}