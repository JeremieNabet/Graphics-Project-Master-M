package elements;


import primitives.*;

import java.util.LinkedList;
import java.util.List;

import static primitives.Ray.rayRandomBeam;
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
     * Also serves as a Boolean variable whether or not this image improvement<br>
     * num Of Ray For Anti Aliasing sharping the Edges
     */
    private int numOfRayForAntiAliasing = 0;

    /*
     *  focalDistance - the distance of the  focus.
     *  aperture      - the radius of the aperture.
     *  numOfRays     - number of rays that will be in the beam from every pixels area (in addition to the original ray).
     */
    private double _focalDistance;
    private double _aperture;
    private int _numOfRays;

    /**
     * ctor
     */
    public Camera(CameraBuilder builder) {
        p0 = builder._p0;
        vTo = builder._vTo;
        vUp = builder._vUp;
        vRight = builder._vRight;
        height = builder._height;
        width = builder._width;
        distance = builder._distance;
    }

    /**
     * @param p0  location of the camera
     * @param vUp y
     * @param vTo z
     */
    public Camera(Point3D p0, Vector vTo, Vector vUp) {
        this.p0 = p0;
        this.vUp = vUp.normalized();
        this.vTo = vTo.normalized();
        if (!isZero(vUp.dotProduct(vTo))) {
            throw new IllegalArgumentException("Vectors are not ortogonal.");
        }
        this.vRight = this.vTo.crossProduct(this.vUp);
    }

    /**
     * setter of Depth of filed. if Depth of filed function is called the camera will be focused for a specific distance.
     * if Depth of filed will not be called the camera will be focused on the whole scene equally.
     *
     * @param focalDistance - the distance of the  focus.
     * @param aperture      - the radius of the aperture.
     * @param numOfRays     - number of rays that will be in the beam from every pixels area (in addition to the original ray).
     */
    public Camera setDepthOfFiled(double focalDistance, double aperture, int numOfRays) {
        _focalDistance = focalDistance;
        _aperture = aperture;
        _numOfRays = numOfRays;
        return this;
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
     * @param numOfRayForAntiAlias the number of ray
     */
    public Camera setNumOfRayForAntiAliasing(int numOfRayForAntiAlias) {
        if (numOfRayForAntiAliasing < 0)
            throw new IllegalArgumentException("num Of Ray For Anti Aliasing cant be less then 0!");
        this.numOfRayForAntiAliasing = numOfRayForAntiAlias;
        return this;

    }

    public double getAperture() {
        return _aperture;
    }

    public int getNumOfRays() {
        return _numOfRays;
    }

    public double getFocalDistance() {
        return _focalDistance;
    }

    /**
     * builds a beam of Rays from the area of a pixel through a specific point on the focal plane
     *
     * @param nX - number of cells left to right
     * @param nY - number of cells up to down
     * @param j  - index of width cell
     * @param i  - index of height cell
     * @return - a list of rays that contains the beam of rays
     */
    public List<Ray> constructRaysThroughPixel(int nX, int nY, int j, int i) {
        Ray ray = constructRayThroughPixel(nX, nY, j, i);
        Point3D pij = ray.getPoint(distance / (vTo.dotProduct(ray.getDirection())));
        Point3D f = ray.getPoint((_focalDistance + distance) / (vTo.dotProduct(ray.getDirection())));//focal point
        List<Ray> result = rayRandomBeam(pij, f, _aperture, _numOfRays, vRight, vUp);
        if (numOfRayForAntiAliasing > 0)
            result.addAll(constructBeamRayForAntiAliesing(ray, nX, nY));
        result.add(new Ray(pij, ray.getDirection()));
        return result;
    }


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

    }

    /**
     * construct beam of Ray Through Pixel form location of camera F
     *
     * @param ray for center ray
     * @param nX  depend hoe pixel we wont row
     * @param nY  depend hoe pixel we wont column
     * @return List<Ray> form radius For Anti Aliesing towards the center of
     * pixel<br>
     */
    public List<Ray> constructBeamRayForAntiAliesing(Ray ray, int nX, int nY) {
        List<Ray> splittedRays = new LinkedList<>();
        Point3D centerCirclePoint = null;
        double t = distance / (vTo.dotProduct(ray.getDirection()));
        double distance = p0.distance(ray.getPoint(t));
        try {
            centerCirclePoint = ray.getPoint(distance);
        } catch (Exception e) {
            centerCirclePoint = p0;
        }
        Point3D randomCirclePoint = null;
        Vector dir = ray.getDirection();
        for (int i = 0; i < numOfRayForAntiAliasing; i++) {
            randomCirclePoint = centerCirclePoint.randomPointOnRectangle(dir, width / nX, height / nY);
            Vector v = randomCirclePoint.subtract(p0);
            splittedRays.add(new Ray(p0, v));
        }
        return splittedRays;
    }

}
