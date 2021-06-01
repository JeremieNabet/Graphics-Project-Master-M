package elements;


import primitives.*;

import static primitives.Util.alignZero;
import static primitives.Util.isZero;

/**
 * Camera class is it the representation of a real camera
 * and to put yourself in the shoes of a photographer
 */
public class Camera {

    /**
     * the camera location
     */
    final private Point3D p0;
    /**
     * vector up
     */
    final private Vector vUp;
    /**
     * vTo vector that exit behind
     * vector toward
     */
    final private Vector vTo;
    /**
     * vector right
     */
    final private Vector vRight;
    /**
     * width of my camera place
     */
    private double width;
    /**
     * height of my camera place
     */
    private double height;
    /**
     * distance of my camera
     */
    private double distance;

    /**
     * ctor
     */
    private Camera(CameraBuilder builder) {
        p0 = builder._p0;
        vTo = builder._vTo;
        vUp = builder._vUp;
        vRight = builder._vRight;
        height = builder._height;
        width = builder._width;
        distance = builder._distance;
    }

    /**
     * set view plane size
     *
     * @param width  of the camera plane size
     * @param height of camera plane size
     * @return the new width and the new height
     */
    public Camera setViewPlaneSize(double width, double height) {
        this.width = width;
        this.height = height;
        return this;
    }

    /**
     * Camera setter chaining methods
     *
     * @param distance between a camera and the goal
     * @return the new distance
     */
    public Camera setDistance(double distance) {
        this.distance = distance;
        return this;
    }


    /**
     * This methods calculate the ray
     * the starting camera and the intercept the view plane and the center
     * of the pixel's square
     *
     * @param nX row of the pixel
     * @param nY column of the pixel
     * @param j  pixel
     * @param i  pixel
     * @return the new ray
     */
    public Ray constructRayThroughPixel(int nX, int nY, int j, int i) {
        Point3D pC = p0.add(vTo.scale(distance));

        double rY = alignZero(height / nY);
        double rX = alignZero(width / nX);

        double yI = alignZero(-(i - ((nY - 1) / 2d)) * rY);
        double xJ = alignZero((j - ((nX - 1) / 2d)) * rX);

        Point3D pIJ = pC;
        if (!isZero(xJ))
            pIJ = pIJ.add(vRight.scale(xJ));
        if (!isZero(yI))
            pIJ = pIJ.add(vUp.scale(yI));

        Vector vIJ = pIJ.subtract(p0);
        return new Ray(p0, vIJ);
    }

    /**
     * WE need to check if Dan accept this class
     * because we found this on Eliezer's github
     */
    public static class CameraBuilder {
        final private Point3D _p0;
        final private Vector _vTo;
        final private Vector _vUp;
        final private Vector _vRight;

        private double _distance;
        private double _width;
        private double _height;

        /**
         * Constructor which checks that my variables are not equal to zero
         * copier builder
         *
         * @param p0
         * @param vTo
         * @param vUp
         */

        public CameraBuilder(Point3D p0, Vector vTo, Vector vUp) {
            if (!isZero(vUp.dotProduct(vTo)))
                throw new IllegalArgumentException("the vectors must be");

            this._p0 = p0;
            this._vTo = vTo.normalized();
            this._vUp = vUp.normalized();

            _vRight = this._vTo.crossProduct(this._vUp).normalize();
        }

        /**
         * distance setter
         *
         * @param distance distance from camera to view plane
         * @return camera
         */
        public CameraBuilder setDistance(double distance) {
            _distance = distance;
            return this;
        }

        /**
         * build the camera
         *
         * @return camera
         */
        public Camera build() {
            Camera camera = new Camera(this);
            return camera;
        }

        /**
         * view plane size setter
         *
         * @param width  view plane's width
         * @param height view plane's height
         * @return new entity from type of camera builder
         */
        public CameraBuilder setViewPlaneSize(double width, double height) {
            this._width = width;
            this._height = height;
            return this;
        }

        /**
         * Upwards vector getter
         *
         * @return upwards vector
         */
        public Vector getVUp() {
            return _vUp;
        }

        /**
         * vto vector getter
         *
         * @return vto vector
         */
        public Vector getVTo() {
            return _vTo;
        }

        /**
         * right vector getter
         *
         * @return right vector
         */
        public Vector getVRight() {
            return _vRight;
        }

        /**
         * width vector getter
         *
         * @return width vector
         */
        public double getWidth() {
            return _width;
        }

        /**
         * height vector getter
         *
         * @return height vector
         */
        public double getHeight() {
            return _height;
        }

        /**
         * distance vector getter
         *
         * @return distance vector
         */
        public double getDistance() {
            return _distance;
        }


    }
}
