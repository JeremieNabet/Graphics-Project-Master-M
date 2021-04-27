package elements;


import primitives.*;

import static primitives.Util.isZero;


public class Camera {

    final private Point3D p0;
    final private Vector vUp;
    final private Vector vTo;
    final private Vector vRight;

    private double width = 1;
    private double height = 1;
    private double distance = 1;

    /**
     * Constructeur for the class BuilderCamera
     *
     * @param builder
     * @author Yona
     */
    public Camera(BuilderCamera builder) {
        this.p0 = builder._p0;
        this.vUp = builder._vUp;
        this.vTo = builder._vTo;
        this.vRight = builder._vRight;
        this.width = builder._width;
        this.height = builder._height;
        this.distance = builder._distance;
    }

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

    /**
     * Upwards vector getter
     *
     * @return upwards vector
     */
    public Vector getVUp() {
        return vUp;
    }

    /**
     * to vector getter
     *
     * @return to vector
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
     *
     * @param nX
     * @param nY
     * @param j
     * @param i
     * @return
     */
    public Ray constructRayThroughPixel(int nX, int nY, int j, int i) {
        Point3D pC = p0.add(vTo.scale(distance));

        double Ry = height / nY;
        double Rx = width / nX;

        double yi = -((i - nY / 2d) * Ry + Ry / 2d);
        double xj = ((j - nX / 2d) * Rx + Rx / 2d);

        Point3D Pij = pC;

        if (!isZero(xj))
            Pij = Pij.add(vRight.scale(xj).normalize());
        if (!isZero(yi))
            Pij = Pij.add(vUp.scale(yi).normalize());

        Vector Vij = Pij.subtract(p0);
        return new Ray(p0, Vij);

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
     * @param width
     * @param height
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
     * @param d
     * @return the distance value modify
     */
    public Camera setVpDistance(int d) {
        distance = d;
        return this;
    }


    /**
     * Builder Class for Camera
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
