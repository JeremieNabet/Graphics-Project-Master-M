package renderer;

import elements.*;
import primitives.*;

import java.util.*;

/**
 * Class which create the color matrix of the image from the scene
 *
 * @author jerem and israel
 */
public class Render {
    /**
     * image writer
     */
    private ImageWriter imageWriter;
    /**
     * camera
     */
    private Camera camera;
    /**
     * basic ray tracer
     */
    private BasicRayTracer basicRayTracer;

    /**
     * Constructor
     *
     * @param renderBuilder
     */
    public Render(RenderBuilder renderBuilder) {
        imageWriter = renderBuilder.imageWriter;
        basicRayTracer = renderBuilder.basicRayTracer;
        camera = renderBuilder.camera;
    }

    public static class RenderBuilder {
        private ImageWriter imageWriter;
        private Camera camera;
        private BasicRayTracer basicRayTracer;

        /**
         * imageWriter setter
         *
         * @param imageWriter the given imageWriter
         * @return Render
         */
        public RenderBuilder setImageWriter(ImageWriter imageWriter) {
            this.imageWriter = imageWriter;
            return this;
        }

        /**
         * camera setter
         *
         * @param camera the given camera
         * @return Render
         */
        public RenderBuilder setCamera(Camera camera) {
            this.camera = camera;
            return this;
        }

        /**
         * rayTracer setter
         *
         * @param basicRayTracer the given rayTracer
         * @return Render
         */
        public RenderBuilder setRayTracer(BasicRayTracer basicRayTracer) {
            this.basicRayTracer = basicRayTracer;
            return this;
        }

        /**
         * render builder
         *
         * @return the built render
         */
        public Render build() {
            return new Render(this);
        }
    }

    /**
     * building a ray to all the view plane's pixels and get each ray's color
     * and put it in the right pixel for him.
     *
     * @throws MissingResourceException if one field is null
     */
    public void renderImage() {
        if (imageWriter == null)
            throw new MissingResourceException("imageWriter is null", "Render", "imageWriter");
        else if (camera == null)
            throw new MissingResourceException("camera is null", "Render", "camera");
        else if (basicRayTracer == null)
            throw new MissingResourceException("basicRayTracer is null", "Render", "basicRayTracer");

        int nY = imageWriter.getNy();
        int nX = imageWriter.getNx();
        for (int i = 0; i < nY; i++) {
            for (int j = 0; j < nX; j++) {
                Ray ray = camera.constructRayThroughPixel(nX, nY, j, i);
                Color pixelColor = basicRayTracer.traceRay(ray);
                imageWriter.writePixel(j, i, pixelColor);
            }
        }
    }

    /**
     * create grid of lines
     *
     * @param interval Indicates for which quantity of pixels a grid line is passed,
     *                 both horizontally and vertically
     * @param color    the grid's color
     * @throws MissingResourceException if imageWriter is null
     */
    public void printGrid(int interval, Color color) {
        if (imageWriter == null)
            throw new MissingResourceException("imageWriter is null", "Render", "imageWriter");
        int nY = imageWriter.getNy();
        int nX = imageWriter.getNx();
        for (int i = 0; i < nY; i++) {
            for (int j = 0; j < nX; j++) {
                if (i % interval == 0 || j % interval == 0) {
                    imageWriter.writePixel(j, i, color);
                }
            }
        }
    }

    /**
     * Activates the appropriate image maker's method
     *
     * @throws MissingResourceException if imageWriter is null
     */
    public void writeToImage() {
        if (imageWriter == null)
            throw new MissingResourceException("imageWriter is null", "Render", "imageWriter");
        imageWriter.writeToImage();
    }
}