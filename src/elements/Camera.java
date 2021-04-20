package elements;


import primitives.*;

import java.awt.geom.Arc2D;

import static primitives.Util.isZero;


public class Camera {

     Point3D p0;
     Vector vUp;
     Vector vTo;
     Vector vRight;

     double width;
     double height;
     double distance;

    public Camera(Point3D p0, Vector vTo, Vector vUp) {

        if (vUp.dotProduct(vTo) != 0)
            throw new IllegalArgumentException("the vectors must be orthogonal");

        this.p0 = new Point3D(p0.getX(), p0.getY(),p0.getZ());
        this.vTo = vTo.normalized();
        this.vUp = vUp.normalized();

        vRight = this.vTo.crossProduct(this.vUp).normalize();

    }

    public Vector getvUp() { return vUp; }

    public Vector getvTo() { return vTo; }

    public Vector getvRight() { return vRight; }

    public double getWidth() { return width; }

    public double getHeight() { return height; }

    public double getDistance() { return distance;}

    public Ray constructRay(int nX, int nY, int j, int i){
        return new Ray();
    }
    public Ray constructRayThroughPixel(int nX, int nY, int j, int i) {

        int screenDistance = 1;
        int screenHeight = 1;
        int screenWidth = 1;

        if (screenDistance == 0 && screenHeight == 0) {
            throw new IllegalArgumentException("distance cannot be 0");
        }

        Point3D Pc = p0.add(vTo.scale(screenDistance));

        double Ry = screenHeight / nY;
        double Rx = screenWidth / nX;

        double yi = ((i - nY / 2d) * Ry + Ry / 2d);
        double xj = ((j - nX / 2d) * Rx + Rx / 2d);

        Point3D Pij = Pc;

        if (!isZero(xj)) {
            Pij = Pij.add(vRight.scale(xj).normalize());
        }
        if (!isZero(yi)) {
            Pij = Pij.add(vUp.scale(yi).normalize());
        }

        Vector Vij = Pij.subtract(p0);

        return new Ray(p0, Vij);

    }

    public Camera setVpSize(int i, int i1) {

        return this;
    }

    public Camera setVpDistance(int i) {
        return this;
    }
}
