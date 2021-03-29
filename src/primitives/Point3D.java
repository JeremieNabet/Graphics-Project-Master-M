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
    public static final Point3D ZERO = new Point3D(0, 0, 0);

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
     * The function substract 2 points
     *
     * @param obj
     * @return the new Vector by this result
     */
    public Vector subtract(Point3D obj) {

        return new Vector(this.x.coord - obj.x.coord, this.y.coord - obj.y.coord, this.z.coord - obj.z.coord);
    }

    /**
     *
     * @param other
     * @return
     */
    public double distanceSquared(Point3D other) {
        double dx = this.x.coord - other.x.coord;
        double dy = this.y.coord - other.y.coord;
        double dz = this.z.coord - other.z.coord;
        return dx * dx + dy * dy + dz * dz;
    }

    /**
     * distance between this point and another point
     *
     * @param other another point
     * @return the calculated distance
     */
    public double distance(Point3D other) {
        return Math.sqrt(distanceSquared(other));
    }

    public Point3D(double x, double y, double z) {
        this.x = new Coordinate(x);
        this.y = new Coordinate(y);
        this.z = new Coordinate(z);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        if (!(o instanceof Point3D)) return false;
        Point3D p = (Point3D) o;
        return x.equals(p.x) && y.equals(p.y) && z.equals(p.z);
    }

    @Override
    public String toString() {
        return "(" + x + "," + y + "," + z +")";
    }

    /**
     * Function is to get the X from coordinate
     * @return x
     */
    public Coordinate getX() {
        return x;
    }

    /**
     * Function is to get the Y from coordinate
     * @return y
     */
    public Coordinate getY() {
        return y;
    }
    /**
     * Function is to get the Z from coordinate
     * @return z
     */
    public Coordinate getZ() {
        return z;
    }
}
