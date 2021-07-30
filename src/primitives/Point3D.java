package primitives;

import static primitives.Util.random;

/**
 * class Point3D is the basic class representing a point for Cartesian
 * coordinate system.
 */
public class Point3D {
    /**
     * Points in 3D space according to the axes
     */
    final Coordinate x;
    final Coordinate y;
    final Coordinate z;
    /**
     * static filed to get the "zero point"
     */
    public static Point3D PointZERO = new Point3D(0, 0, 0);


    /**
     * c-tor of point3D class that gets 3 coordinates
     *
     * @param x coordinate for x axis
     * @param y coordinate for y axis
     * @param z coordinate for z axis
     */
    public Point3D(Coordinate x, Coordinate y, Coordinate z) {
        this(x.coord, y.coord, z.coord);
    }

    /**
     * primary c-tor
     *
     * @param x coordinate for x axis
     * @param y coordinate for y axis
     * @param z coordinate for z axis
     */
    public Point3D(double x, double y, double z) {
        this.x = new Coordinate(x);
        this.y = new Coordinate(y);
        this.z = new Coordinate(z);

    }


    /**
     * Checks if two points are equal
     *
     * @param o
     * @return
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Point3D point3D = (Point3D) o;
        return x.equals(point3D.x) && y.equals(point3D.y) && z.equals(point3D.z);
    }

    /**
     * @param point3D
     * @return distanceSquared of a point
     */
    public double distanceSquared(Point3D point3D) {
        final double x1 = x.coord;
        final double y1 = y.coord;
        final double z1 = z.coord;
        final double x2 = point3D.x.coord;
        final double y2 = point3D.y.coord;
        final double z2 = point3D.z.coord;

        return ((x2 - x1) * (x2 - x1) + (y2 - y1) * (y2 - y1) + (z2 - z1) * (z2 - z1));
    }

    /**
     * @param point3D
     * @return distance
     */
    public double distance(Point3D point3D) {
        return Math.sqrt(distanceSquared(point3D));
    }

    /**
     * @param vector
     * @return the sum of point and a vector
     */
    public Point3D add(Vector vector) {
        return new Point3D(
                this.x.coord + vector.head.x.coord,
                this.y.coord + vector.head.y.coord,
                this.z.coord + vector.head.z.coord);
    }

    /**
     * @param point3D
     * @return subtract between point and vector
     */
    public Vector subtract(Point3D point3D) {
        if (point3D.equals(this)) {
            throw new IllegalArgumentException("cannot create Vector to Point(0,0,0)");
        }
        return new Vector(
                x.coord - point3D.x.coord,
                y.coord - point3D.y.coord,
                z.coord - point3D.z.coord
        );
    }
    /**
     * Function to the center of the square,<br>
     * which receives a square,<br>
     * and returns a random point on the square
     *
     * @param dir    The normal exiting the square,<br>
     *               together with the radius or height and length representing the
     *               square,
     * @param width  of the square
     * @param height of the square
     * @return Returns a random point on the square
     */
    public Point3D randomPointOnRectangle(Vector dir, double width, double height) {
        Vector firstNormal = dir.createOrthogonalVector();
        Vector secondNormal = firstNormal.crossProduct(dir).normalize();
        Point3D randomCirclePoint = this;
        double r;
        double wHalf = width / 2;
        r = random(0, wHalf);
        double x = random(-r, r);
        double hHalf = height / 2;
        r = random(0, hHalf);
        double y = random(-r, r);
        if (x != 0)
            randomCirclePoint = randomCirclePoint.add(firstNormal.scale(x));
        if (y != 0)
            randomCirclePoint = randomCirclePoint.add(secondNormal.scale(y));
        return randomCirclePoint;
    }

    /**
     * find the Absolute minimum Coordinate
     *
     * @return if Coordinate x is minimum return 'x'<br>
     * if Coordinate y is minimum return 'y'<br>
     * if Coordinate z is minimum return 'z'<br>
     * if all Coordinates are equal return 'x'
     */
    public char findAbsoluteMinimumCoordinate() {
        double minimum = this.x.coord < 0 ? -this.x.coord : this.x.coord; // abs(x)
        char index = 'x';
        double y = this.y.coord < 0 ? -this.y.coord : this.y.coord; // abs(y)
        if (y < minimum) {
            minimum = y;
            index = 'y';
        }
        double z = this.z.coord < 0 ? -this.z.coord : this.z.coord; // abs(z)
        if (z < minimum) {
            index = 'z';
        }
        return index;
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
}