package renderer;

import elements.Camera;
import primitives.Color;
import primitives.Ray;
import scene.Scene;

import java.util.MissingResourceException;

public class Render {
    /**
     * image writer
     */
    ImageWriter imageWriter = null;
    /**
     * scene
     */
    Scene scene = null;
    /**
     * camera
     */
    Camera camera = null;
    /**
     * the ray tracer base
     */
    RayTracerBase rayTracerBase = null;

    /**
     * setter of image writer
     *
     * @param imageWriter the image writer
     * @return the image writer calculated
     */
    public Render setImageWriter(ImageWriter imageWriter) {
        this.imageWriter = imageWriter;
        return this;
    }

    /**
     * set a scene
     *
     * @param scene the scene
     * @return the new scene
     */
    public Render setScene(Scene scene) {
        this.scene = scene;
        return this;
    }

    /**
     * set camera
     *
     * @param camera the camera
     * @return the new camera
     */
    public Render setCamera(Camera camera) {
        this.camera = camera;
        return this;
    }

    /**
     * set ray tracer
     *
     * @param rayTracer the raytracer
     * @return the new ray tracer
     */
    public Render setRayTracer(RayTracerBase rayTracer) {
        rayTracerBase = rayTracer;
        return this;
    }

    /**
     * We are checking if image Writer, scene, camera and rayTracer base is not null
     * we are creating a render image
     */
    public void renderImage() {
        try {
            if (imageWriter == null) {
                throw new MissingResourceException("missing resource", ImageWriter.class.getName(), "");
            }
            if (scene == null) {
                throw new MissingResourceException("missing resource", Scene.class.getName(), "");
            }
            if (camera == null) {
                throw new MissingResourceException("missing resource", Camera.class.getName(), "");
            }
            if (rayTracerBase == null) {
                throw new MissingResourceException("missing resource", RayTracerBase.class.getName(), "");
            }

            //rendering the image
            int nX = imageWriter.getNx();
            int nY = imageWriter.getNy();
            for (int i = 0; i < nY; i++) {
                for (int j = 0; j < nX; j++) {
                    Ray ray = camera.constructRayThroughPixel(nX, nY, j, i);
                    Color pixelColor = rayTracerBase.traceRay(ray);
                    imageWriter.writePixel(j, i, pixelColor);
                }
            }
        } catch (MissingResourceException e) {
            throw new UnsupportedOperationException("Not implemented yet" + e.getClassName());
        }
    }

    /**
     * This use to print a grid by the interval and the color
     *
     * @param interval interval
     * @param color    color
     */
    public void printGrid(int interval, Color color) {
        int nX = imageWriter.getNx();
        int nY = imageWriter.getNy();
        int g = 1;
        for (int i = 0; i < nY; i++) {
            g = i % interval == 0 ? 1 : interval;
            for (int j = 0; j < nX; j += g) {
                imageWriter.writePixel(j, i, color);
            }
        }
    }

    /**
     * Use to write to image
     */
    public void writeToImage() {
        imageWriter.writeToImage();
    }
}