package elements;


import primitives.*;

import java.awt.geom.Arc2D;

import static primitives.Util.isZero;


public class Camera {

    private Point3D p0;
     Vector vUp;
     Vector vTo;
     Vector vRight;

     double width = 1;
     double height = 1;
     double distance = 1;

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
     * @return upwards vector
     */
    public Vector getvUp() { return vUp; }

    public Vector getvTo() { return vTo; }

    public Vector getvRight() { return vRight; }

    public double getWidth() { return width; }

    public double getHeight() { return height; }

    public double getDistance() { return distance;}

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

    public Camera setVpSize(int i, int i1) {

        return this;
    }

    public Camera setVpDistance(int d) {
        distance = d;
        return this;
    }
}
