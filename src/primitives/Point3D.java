package primitives;

import static primitives.Util.alignZero;

/**
 * point3D class with 3 coordinates.
 * @author Israel Bellcaiche and Jeremie Nabet
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
        return new Point3D(//
                (this.x.coord + v.head.x.coord),
                (this.y.coord + v.head.y.coord),
                (this.z.coord + v.head.z.coord));
    }

    /**
     * subtract point from the vector.
     * @param point3D the point whom subtract.
     * @return vector - point.
     */
    public Vector subtract(Point3D point3D) {
        return new Vector( //
                alignZero(this.x.coord - point3D.x.coord),
                alignZero(this.y.coord - point3D.y.coord),
                alignZero(this.z.coord - point3D.z.coord));
    }

    /**
     *calculates the distance squared between two points
     * @param point3D point3D.
     * @return distance squared.
     */
    public double distanceSquared(Point3D point3D) {
        double dx = this.x.coord - point3D.x.coord;
        double dy = this.y.coord - point3D.y.coord;
        double dz = this.z.coord - point3D.z.coord;
        return dx * dx + dy * dy + dz * dz;
    }

    /**
     * Distance between this point and another point
     *
     * @param point3D another point
     * @return the calculated distance
     */
    public double distance(Point3D point3D) {
        return Math.sqrt(distanceSquared(point3D));
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

}
