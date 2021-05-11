package elements;


import primitives.*;

import static primitives.Util.alignZero;
import static primitives.Util.isZero;

/**
 *
 * Camera class is it the representation of a real camera
 * and to put yourself in the shoes of a photographer
 */
public class Camera {

    /**
     * p0
     */
    final private Point3D p0;
    /**
     * vUp
     */
    final private Vector vUp;
    /**
     * vTo vector that exit behind
     */
    final private Vector vTo;
    /**
     * vRight
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
     * Constructor which checks that my variables are not equal to zero
     * copier builder
     *
     * @param p0
     * @param vTo
     * @param vUp
     */
    public Camera(Point3D p0, Vector vTo, Vector vUp) {
        if (!isZero(vUp.dotProduct(vTo)))
            throw new IllegalArgumentException("the vectors must be");

        this.p0 = p0;
        this.vTo = vTo.normalized();
        this.vUp = vUp.normalized();

        vRight = this.vTo.crossProduct(this.vUp).normalize();
    }

    private Camera(BuilderCamera builder) {
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
     * Upwards vector getter
     *
     * @return upwards vector
     */
    public Vector getVUp() {
        return vUp;
    }

    /**
     * vto vector getter
     *
     * @return vto vector
     */
    public Vector getVTo() {
        return vTo;
    }

    /**
     * right vector getter
     *
     * @return right vector
     */
    public Vector getVRight() {
        return vRight;
    }

    /**
     * width vector getter
     *
     * @return width vector
     */
    public double getWidth() {
        return width;
    }

    /**
     * height vector getter
     *
     * @return height vector
     */
    public double getHeight() {
        return height;
    }

    /**
     * distance vector getter
     *
     * @return distance vector
     */
    public double getDistance() {
        return distance;
    }

    /**
     * This methods calculate the ray
     * the starting camera and the intercept the view plane and the center
     * of the pixel's square
     *
     * @param nX row of the pixel
     * @param nY column of the pixel
     * @param j pixel
     * @param i pixel
     * @return the new ray
     */
    public Ray constructRayThroughPixel(int nX, int nY, int j, int i) {
        Point3D pC = p0.add(vTo.scale(distance));

        double rY = alignZero(height / nY) ;
        double rX =  alignZero(width / nX) ;

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
     * The movement of the camera
     *
     * @param up    delta for _vUp vector
     * @param right delta for _vRight vector
     * @param to    delta for _vTo vector
     * @return the movement
     */
    public Camera moveCamera(double up, double right, double to) {
        if (up == 0 && right == 0 && to == 0) return this;
        if (up != 0) this.p0.add(vUp.scale(up));
        if (right != 0) this.p0.add(vRight.scale(right));
        if (to != 0) this.p0.add(vTo.scale(to));
        return this;
    }

    /**
     * The turn of the camera
     *
     * @param axis  turning axis
     * @param theta angle to turn the camera
     * @return turn of the camera
     */
    public Camera turnCamera(Vector axis, double theta) {
        if (theta == 0) return this;
        this.vUp.rotateVector(axis, theta);
        this.vRight.rotateVector(axis, theta);
        this.vTo.rotateVector(axis, theta);
        return this;
    }

    /**
     * Function set size allow me to change the size
     *
     * @param width of the camera
     * @param height of the camera
     * @return the new values
     */
    public Camera setVpSize(double width, double height) {
        this.width = width;
        this.height = height;
        return this;
    }

    /**
     * Allows me to change the distance value
     *
     * @param d distance
     * @return the distance value modify
     */
    public Camera setVpDistance(int d) {
        distance = d;
        return this;
    }

    /**
     * WE need to check if Dan accept this class
     * because we found this on Eliezer's github
     */
    public static class BuilderCamera {
        final private Point3D _p0;
        final private Vector _vTo;
        final private Vector _vUp;
        final private Vector _vRight;

        private double _distance = 10;
        private double _width = 1;
        private double _height = 1;

        public BuilderCamera setDistance(double distance) {
            _distance = distance;
            return this;
        }


        public BuilderCamera setViewPlaneWidth(double width) {
            _width = width;
            return this;
        }

        public BuilderCamera setViewPlaneHeight(double height) {
            _height = height;
            return this;
        }

        public Camera build() {
            Camera camera = new Camera(this);
            return camera;
        }

        public BuilderCamera(Point3D p0, Vector vTo, Vector vUp) {
            _p0 = p0;

            if (!isZero(vTo.dotProduct(vUp))) {
                throw new IllegalArgumentException("vto and vup are not orthogonal");
            }

            _vTo = vTo.normalized();
            _vUp = vUp.normalized();

            _vRight = _vTo.crossProduct(_vUp);

        }
    }

}
