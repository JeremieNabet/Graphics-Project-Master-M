package renderer;

import elements.*;
import primitives.*;

import java.util.*;

/**
 * Class which create the color matrix of the image from the scene
 * @author jerem and israel
 */
public class Render {
    private ImageWriter imageWriter;
    //    private Scene scene;
    private Camera camera;
    private BasicRayTracer basicRayTracer;

    /**
     * ctor
     *
     * @param renderBuilder
     */
    public Render(RenderBuilder renderBuilder) {
        imageWriter = renderBuilder._imageWriter;
//        _scene = renderBuilder._scene;
        basicRayTracer = renderBuilder._basicRayTracer;
        camera = renderBuilder._camera;
    }

    public static class RenderBuilder {
        private ImageWriter _imageWriter;
        //        private Scene _scene;
        private Camera _camera;
        private BasicRayTracer _basicRayTracer;

        /**
         * imageWriter setter
         *
         * @param imageWriter the given imageWriter
         * @return Render
         */
        public RenderBuilder setImageWriter(ImageWriter imageWriter) {
            this._imageWriter = imageWriter;
            return this;
        }

        /**
         * scene setter
         * @param scene the given scene
         * @return Render
         */
//        public RenderBuilder setScene(Scene scene) {
//            this._scene = scene;
//            return this;
//        }

        /**
         * camera setter
         *
         * @param camera the given camera
         * @return Render
         */
        public RenderBuilder setCamera(Camera camera) {
            this._camera = camera;
            return this;
        }

        /**
         * rayTracer setter
         *
         * @param basicRayTracer the given rayTracer
         * @return Render
         */
        public RenderBuilder setRayTracer(BasicRayTracer basicRayTracer) {
            this._basicRayTracer = basicRayTracer;
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
//        else if(_scene == null)
//            throw new MissingResourceException("scene is null","Render","scene");
        else if (camera == null)
            throw new MissingResourceException("camera is null", "Render", "camera");
        else if (basicRayTracer == null)
            throw new MissingResourceException("basicRayTracer is null", "Render", "basicRayTracer");

        for (int i = 0; i < imageWriter.getNy(); i++) {
            for (int j = 0; j < imageWriter.getNx(); j++) {
                Ray ray = camera.constructRayThroughPixel(imageWriter.getNx(), imageWriter.getNy(), j, i);
//                BasicRayTracer basicRayTracer = new BasicRayTracer(_scene);
                Color pixelColor = basicRayTracer.traceRay(ray);
                imageWriter.writePixel(j, i, pixelColor);
            }
            //_imageWriter.writeToImage();
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
        for (int i = 0; i < imageWriter.getNy(); i++) {
            for (int j = 0; j < imageWriter.getNx(); j++) {
                if (i % interval == 0 || j % interval == 0) {
                    imageWriter.writePixel(j, i, color);
                }
            }
            //this.writeToImage();
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