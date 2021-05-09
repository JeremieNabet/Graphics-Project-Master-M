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
        if (Point3D.ZERO.equals(this.head))
            throw new IllegalArgumentException("Zero vector is not allowed");
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
     * @param vector
     * @return
     */
    public Vector add(Vector vector) {
        return new Vector(this.head.add(vector));
    }

    /**
     * we use to the function subtract of the point 3D who compose the vector that we received
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
        return ((this.head.x.coord) * (vector.head.x.coord)) +
                (this.head.y.coord * vector.head.y.coord) +
                (this.head.z.coord * vector.head.z.coord);
    }

    /**
     * @return
     */
    public double lengthSquared() {
        return (this.head.x.coord) * (this.head.x.coord)
                + (this.head.y.coord) * (this.head.y.coord)
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
    public Vector scale(double scalary) {
        return new Vector((scalary * this.head.x.coord), (scalary * this.head.y.coord),
                (scalary * this.head.z.coord));
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
     * equals override function
     *
     * @param o object to compare
     * @return true or false
     */

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        if (!(o instanceof Vector)) return false;
        Vector vector = (Vector) o;
        return head.equals(vector.head);
    }

    /**
     * function getHead to have the the head about the vector
     *
     * @return head
     */
    public Point3D getHead() {
        return head;
    }

    /**
     * function toString
     *
     * @return the head of the Vector
     */
    @Override
    public String toString() {
        return "Vector{" + "head=" + head + '}';
    }



    /**
     * This function allows me to know the rotation of my vector
     * @param axis axis of rotation
     * @param theta angle of rotation
     */
    public void rotateVector(Vector axis, double theta) {
        double x = this.head.getX();
        double y = this.head.getY();
        double z = this.head.getZ();

        double u = axis.head.getX();
        double v = axis.head.getY();
        double w = axis.head.getZ();

        double v1 = u * x + v * y + w * z;

        double thetaRad = Math.toRadians(theta);

        double xPrime = u * v1 * (1d - Math.cos(thetaRad))
                + x * Math.cos(thetaRad)
                + (-w * y + v * z) * Math.sin(thetaRad);

        double yPrime = v * v1 * (1d - Math.cos(thetaRad))
                + y * Math.cos(thetaRad)
                + (w * x - u * z) * Math.sin(thetaRad);

        double zPrime = w * v1 * (1d - Math.cos(thetaRad))
                + z * Math.cos(thetaRad)
                + (-v * x + u * y) * Math.sin(thetaRad);

        this.head = new Point3D(xPrime, yPrime, zPrime);
    }
}
