package primitives;

import java.util.Objects;

import  static primitives.Point3D.PointZERO;

public class Vector {
    Point3D head;

    /**
     * primary used c-tor
     * @param head point3d
     */
    public Vector(Point3D head) {

        if (head.equals(PointZERO)) {
            throw new IllegalArgumentException("head cannot be Point(0,0,0)");
        }
        this.head = head;
    }

    /**
     * c-tor for x, y, z coordinates
     * @param x coordinate for x axis
     * @param y coordinate for y axis
     * @param z coordinate for z axis
     */
    public Vector(double x, double y, double z) {
        this(new Point3D( x,y,z));
    }

    public Vector(Coordinate x, Coordinate y, Coordinate z) {
        this(new Point3D(x,y,z));
    }

    public Point3D getHead() {
        return new Point3D(head.x, head.y, head.z);
    }

    /**
     *
     * @param vector
     * @return sum of two vectors
     */
    public Vector add(Vector vector) {
        return new Vector(
                this.head.x.coord + vector.head.x.coord,
                this.head.y.coord + vector.head.y.coord,
                this.head.z.coord + vector.head.z.coord);
    }

    /**
     *
     * @param vector
     * @return subtract between two vectors
     */
    public Vector subtract(Vector vector) {
        return new Vector(
                this.head.x.coord - vector.head.x.coord,
                this.head.y.coord - vector.head.y.coord,
                this.head.z.coord - vector.head.z.coord);
    }

    /**
     *
     * @param scalar
     * @return double a scalar with a vector
     */
    public Vector scale(double scalar) {
        if(scalar * this.head.x.coord==0&&scalar * this.head.y.coord==0&&scalar * this.head.z.coord==0){
            int wt;
        }
        return new Vector(
                scalar * this.head.x.coord,
                scalar * this.head.y.coord,
                scalar * this.head.z.coord);
    }

    /**
     *
     * @param vector
     * @return dotProduct between two vectors
     */
    public Double dotProduct(Vector vector) {
        return  (vector.head.x.coord * this.head.x.coord+
                vector.head.y.coord * this.head.y.coord+
                vector.head.z.coord * this.head.z.coord);
    }

    /**
     *
     * @param vector
     * @return crossProduct between two vectors
     */
    public Vector crossProduct(Vector vector) {
        return new Vector(
                this.head.y.coord * vector.head.z.coord - this.head.z.coord * vector.head.y.coord,
                this.head.z.coord * vector.head.x.coord - this.head.x.coord * vector.head.z.coord,
                this.head.x.coord * vector.head.y.coord - this.head.y.coord * vector.head.x.coord);
    }

    /**
     *
     * @return length Squared of vector
     */
    public double lengthSquared() {
        return this.head.distanceSquared(PointZERO);
    }

    /**
     *
     * @return length of vector
     */
    public double length() {
        return Math.sqrt(this.lengthSquared());
    }

    /**
     *
     * @return thw class vector after normal
     */
    public Vector normalize() {
        double length = this.length();
        head = new Point3D(
                (this.head.x.coord) / length,
                (this.head.y.coord) / length,
                (this.head.z.coord) / length);
        return this;
    }

    /**
     *
     * @return the normal
     */
    public Vector normalized() {
        double length = this.length();
        return new Vector(
                (this.head.x.coord) / length,
                (this.head.y.coord) / length,
                (this.head.z.coord) / length);
    }

    /**
     * override func checks if two vectors are equal
     *
     * @param o
     * @return true if its equal
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vector vector = (Vector) o;
        return head.equals(vector.head);
    }

    @Override
    public int hashCode() {
        return Objects.hash(head);
    }

    /**
     * This function return a Vertical Vector to "this" vector <br>
     * (this) most be normalized!!!
     *
     * @return normal vector Vertical to this
     */
    public Vector createOrthogonalVector() {
        double x = head.x.coord, y = head.y.coord, z = head.z.coord;
        switch (head.findAbsoluteMinimumCoordinate()) {
            case 'x': {
                return new Vector(0, -z, y).normalize();
            }
            case 'y': {
                return new Vector(-z, 0, x).normalize();
            }
            case 'z': {
                return new Vector(y, -x, 0).normalize();
            }
            default:
                throw new IllegalArgumentException("Unexpected value: " + head.findAbsoluteMinimumCoordinate());
        }
    }
}