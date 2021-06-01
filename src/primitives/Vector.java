package primitives;

/**
 * Vector class implements a 3-dimensional vector in Cartesian Coordinate System
 */
public class Vector {
    /**
     * The head point about the vector
     */
    Point3D head;

    /**
     * primary constructor for Vector class
     * first constructor, we receive 3 double
     */
    public Vector(double x, double y, double z) {
        this(new Point3D(x, y, z));
    }

    /**
     * second constructor, we receive on point 3D
     * that is not the vector 0
     */
    public Vector(Point3D p) {
        if (Point3D.ZERO.equals(p))
            throw new IllegalArgumentException("Zero vector is not allowed");
        this.head = p;
    }

    /**
     * we use to the function add of the point 3D who compose the vector that we received
     *
     * @param vector
     * @return
     */
    public Vector add(Vector vector) {
        return new Vector(this.head.add(vector));
    }

    /**
     * we use to the function subtract of the point 3D who compose the vector that we received
     *
     * @param vector
     * @return
     */
    public Vector subtract(Vector vector) {
        return this.head.subtract(vector.head);
    }

    /**
     * if vector b is collineary with our vector than exception
     */
    public Vector crossProduct(Vector vector) {
        return new Vector((this.head.y.coord * vector.head.z.coord) - (this.head.z.coord * vector.head.y.coord)
                , (this.head.z.coord * vector.head.x.coord) - (this.head.x.coord * vector.head.z.coord),
                (this.head.x.coord * vector.head.y.coord) - (this.head.y.coord * vector.head.x.coord));
    }

    /**
     * This function allows me to multiply the coordinates of
     * the vector received
     * with the vector I have, and add them together
     * Xa*Xb+Ya*Yb+Za*Zb
     *
     * @param vector
     * @return the product (double)
     */
    public double dotProduct(Vector vector) {
        return ((this.head.x.coord * vector.head.x.coord) + //
                (this.head.y.coord * vector.head.y.coord) + //
                (this.head.z.coord * vector.head.z.coord));
    }

    /**
     * calculates the squared length of the vector.
     *
     * @return vector's squared length..
     */
    public double lengthSquared() {
        return (this.head.x.coord) * (this.head.x.coord) //
                + (this.head.y.coord) * (this.head.y.coord) //
                + (this.head.z.coord) * (this.head.z.coord);
    }

    /**
     * the length about the vector
     *
     * @return te length
     */
    public double length() {
        return Math.sqrt(lengthSquared());
    }

    /**
     * A normalize operation that
     *
     * @return this
     */
    public Vector normalize() {
        double len = length();
        head = new Point3D(this.head.x.coord / len, this.head.y.coord / len, this.head.z.coord / len);
        return this;
    }

    /**
     * we return a new vector that is multiplicative all
     * coordinate of the point 3 D  of our vector
     */
    public Vector scale(double scalar) {
        return new Vector( //
                (scalar * this.head.x.coord), //
                (scalar * this.head.y.coord), //
                (scalar * this.head.z.coord));
    }

    /**
     * A normalized operation that
     *
     * @return a new vector
     */
    public Vector normalized() {
        return new Vector(this.head).normalize();
    }


    /**
     * function getHead to have the the head about the vector
     *
     * @return head
     */
    public Point3D getHead() {
        return head;
    }

    /*************** Admin *****************/
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        if (!(o instanceof Vector)) return false;
        Vector vector = (Vector) o;
        return head.equals(vector.head);
    }

    @Override
    public String toString() {
        return "Vector{" + "head=" + head + '}';
    }

}
