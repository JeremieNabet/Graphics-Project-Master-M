package primitives;

/**
 *
 */
public class Point3D {

    /**
     * x coordinate of the point
     */
    final Coordinate x;
    /**
     * y coordinate of the point
     */
    final Coordinate y;
    /**
     * z coordinate of the point
     */
    final Coordinate z;

    /**
     * Point of coordinate system center
     */
    public static final Point3D ZERO = new Point3D(0d, 0d, 0d);

    /**
     * primary constructor for Point3D
     *
     * @param x coordinate value for X axis
     * @param y coordinate value for Y axis
     * @param z coordinate value for Z axis
     */
    public Point3D(double x, double y, double z) {
        this.x = new Coordinate(x);
        this.y = new Coordinate(y);
        this.z = new Coordinate(z);
    }

    /**
     * The function creates a new point as a result of moving this point by a vector
     *
     * @param v vector to add to the point
     * @return new moved point
     */
    public Point3D add(Vector v) {
        return new Point3D((this.x.coord + v.head.x.coord), (this.y.coord + v.head.y.coord),
                (this.z.coord + v.head.z.coord));
    }

    /**
     * The function subtract 2 points
     *
     * @param obj
     * @return the new Vector by this result
     */
    public Vector subtract(Point3D obj) {
        return new Vector(this.x.coord - obj.x.coord, this.y.coord - obj.y.coord, this.z.coord - obj.z.coord);
    }

    /**
     * Calculate the distance squared
     *
     * @param other
     * @return (x2 - x1)^2 + (y2-y1)^2 + (z2-z1)^2
     */
    public double distanceSquared(Point3D other) {
        double dx = this.x.coord - other.x.coord;
        double dy = this.y.coord - other.y.coord;
        double dz = this.z.coord - other.z.coord;
        return dx * dx + dy * dy + dz * dz;
    }

    /**
     * Distance between this point and another point
     *
     * @param other another point
     * @return the calculated distance
     */
    public double distance(Point3D other) {
        return Math.sqrt(distanceSquared(other));
    }

    /**
     * Function is to get the X from coordinate
     *
     * @return x
     */
    public double getX() {
        return x.coord;
    }

    /**
     * Function is to get the Y from coordinate
     *
     * @return y
     */
    public double getY() {
        return y.coord;
    }

    /**
     * Function is to get the Z from coordinate
     *
     * @return z
     */
    public double getZ() {
        return z.coord;
    }


    /*************** Admin *****************/
    @Override
    public boolean equals(Object o) {
        if (o == null) return false;
        if (this == o) return true;
        if (!(o instanceof Point3D)) return false;
        Point3D p = (Point3D) o;
        return x.equals(p.x) && y.equals(p.y) && z.equals(p.z);
    }

    @Override
    public String toString() {
        return "(" + x + "," + y + "," + z + ")";
    }

    /**
     * This function allows me to know the rotation of the point
     *
     * @param axis  axis of rotation
     * @param theta angle of rotation
     * @return new point - result of rotating this point
     */
    public Point3D rotate(Vector axis, double theta) {
        double x = this.x.coord;
        double y = this.y.coord;
        double z = this.z.coord;

        double u = axis.head.x.coord;
        double v = axis.head.y.coord;
        double w = axis.head.z.coord;

        double v1 = u * x + v * y + w * z;

        double thetaRad = Math.toRadians(theta);
        double cos = Math.cos(thetaRad);
        double oneMinusCos = 1d - cos;
        double sin = Math.sin(thetaRad);

        double xPrime = u * v1 * oneMinusCos + x * cos + (v * z - w * y) * sin;
        double yPrime = v * v1 * oneMinusCos + y * cos + (w * x - u * z) * sin;
        double zPrime = w * v1 * oneMinusCos + z * cos + (u * y - v * x) * sin;
        return new Point3D(xPrime, yPrime, zPrime);
    }
}
