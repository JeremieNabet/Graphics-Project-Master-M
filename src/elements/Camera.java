package elements;


import primitives.*;

import static primitives.Util.alignZero;
import static primitives.Util.isZero;

/**
 * The Camera of the scene
 * point - the beginning point of the camera
 * Vup - vector in the upward direction from the camera
 * Vto - vector in a straight line from the camera
 * Vright - vector in the right direction from the camera
 * viewplaneW - the width of the view plane
 * viewplaneH - the height of the view plane
 * distance - the distance between the camera and the view plane
 */
public class Camera {

    /**
     * the camera location
     */
    final private Point3D p0;
    /**
     * vector in the upward direction from the camera
     */
    final private Vector vUp;
    /**
     * vector in a straight line from the camera
     */
    final private Vector vTo;
    /**
     * vector in the right direction from the camera
     */
    final private Vector vRight;
    /**
     * the width of the view plane
     */
    private double width;
    /**
     * the height of the view plane
     */
    private double height;
    /**
     * the distance between the camera and the view plane
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
     * set the view plane size
     *
     * @param width  - the width of the view plane
     * @param height - the height of the view plane
     * @return the camera
     */
    public Camera setViewPlaneSize(double width, double height) {
        this.width = width;
        this.height = height;
        return this;
    }

    /**
     * set the distance between the camera and the view plane
     *
     * @param distance - the distance between the camera and the view plane
     * @return the camera
     */
    public Camera setDistance(double distance) {
        this.distance = distance;
        return this;
    }


    /**
     * construct a ray from the camera through the middle of (i, j) pixel
     *
     * @param nX - the number of pixels in one row (= the number of pixel columns)
     * @param nY - the number of pixels in one column (= the number of pixel rows)
     * @param j  - the column of the pixel
     * @param i  - the row of the pixel
     * @return the ray throughout the pixel(i, j)
     */
    public Ray constructRayThroughPixel(int nX, int nY, int j, int i) {
        //give me the center of the screen
        Point3D pC = p0.add(vTo.scale(distance));

        double rY = alignZero(height / nY);
        double rX = alignZero(width / nX);

//          We must find the ray from the eye of the camera that passes in pixels of the scene
//          for a given Point2D(X,Y).
//          First we have PC=P0+Vto*screenDistance(the center of the screen).
//          Then we calculate i = (x - (Nx) / 2) * Rx this  is the number
//          of pixels we need to move and the X axis. We multiply Vright by it
//          We do the same thing for j=(y - (Ny) / 2) * Ry but this time it is on the up axis and we
//          multiply Vup.
//          We add to PC the difference between Vright and Vup and we get P.
//          P= PC+ [Vright*i- Vup*j]
//          The ray is the vector from P0 to P with P0 as POO.

        double yI = alignZero(-(i - ((nY - 1) / 2d)) * rY);
        double xJ = alignZero((j - ((nX - 1) / 2d)) * rX);

        Point3D pIJ = pC;
        if (!isZero(xJ)) //we don't need to add Xj * Vright to pij because it's equal zero
            pIJ = pIJ.add(vRight.scale(xJ));
        if (!isZero(yI)) //we don't need to add Yi * Vup to pij because it's equal zero
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
