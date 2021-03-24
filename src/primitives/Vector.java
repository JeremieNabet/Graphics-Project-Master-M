package primitives;

/**
 * Vector class implements a 3-dimensional vector in Cartesian Coordinate System
 */
public class Vector {
    Point3D head;

    public Vector(double x, double y, double z) {
        this.head = new Point3D(x, y, z);
        if (Point3D.ZERO.equals(this.head))
            throw new IllegalArgumentException("Zero vector is not allowed");
    }

    public Vector(Point3D p) {
        if (Point3D.ZERO.equals(p))
            throw new IllegalArgumentException("Zero vector is not allowed");
        this.head = p;
    }

    public Vector add(Vector vector) {
        return new Vector(this.head.add(vector));
    }

    public Vector subtract(Vector vector) {
        return this.head.subtract(vector.head);
    }

    public Vector crossProduct(Vector vector) {
        return new Vector((this.head.y.coord * vector.head.z.coord) - (this.head.z.coord * vector.head.y.coord)
                , (this.head.z.coord * vector.head.x.coord) - (this.head.x.coord * vector.head.z.coord),
                (this.head.x.coord * vector.head.y.coord) - (this.head.y.coord * vector.head.x.coord));
    }

    public double dotProduct(Vector vector) {
        return ((this.head.x.coord) * (vector.head.x.coord)) +
                (this.head.y.coord * vector.head.y.coord) +
                (this.head.z.coord * vector.head.z.coord);
    }

    public double lengthSquared() {
        return (this.head.x.coord) * (this.head.x.coord)
                + (this.head.y.coord) * (this.head.y.coord)
                + (this.head.z.coord) * (this.head.z.coord);
    }

    public double length() {
        return Math.sqrt(lengthSquared());
    }

    public Vector normalize() {
        double len = length();
        head = new Point3D(this.head.x.coord / len, this.head.y.coord / len, this.head.z.coord / len);
        return this;
    }

    public Vector scale(double scalary) {
        return new Vector((scalary * this.head.x.coord), (scalary * this.head.y.coord), (scalary * this.head.z.coord));
    }

    public Vector normalized() {
        return new Vector(this.head).normalize();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        if (!(o instanceof Vector)) return false;
        Vector vector = (Vector) o;
        return head.equals(vector.head);
    }

    public Point3D getHead() {
        return head;
    }

    public void setHead(Point3D head) {
        this.head = head;
    }
}
