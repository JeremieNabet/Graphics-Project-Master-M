package primitives;

public class Ray {
    /**
     * the point p0 about the ray
     */
    final Point3D p0;
    /**
     * the vector about the ray
     */
    final Vector dir;

    public Ray(Point3D p0, Vector dir) {
        this.p0 = p0;
        this.dir = dir.normalized();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        if (o instanceof Ray) return false;
        Ray ray = (Ray) o;
        return p0.equals(ray.p0) && dir.equals(ray.dir);
    }

    /**
     * function get p0 about the Ray
     *
     * @return p0
     */
    public Point3D getP0() {
        return p0;
    }

    /**
     * function get direction about the Vector
     *
     * @return dir
     */
    public Vector getDir() {
        return dir;
    }

    /**
     * function toString about the class Ray
     *
     * @return string
     */
    @Override
    public String toString() {
        return "Ray{" + "p0=" + p0 + ", dir=" + dir + '}';
    }

    /**
     *
     * @param t
     * @return
     */
    public Point3D getPoint(double t) {
        return p0.add(dir.scale(t));
    }
}
