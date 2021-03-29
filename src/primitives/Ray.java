package primitives;

public class Ray {
    /**
     * the point p0 about the ray
     */
    private Point3D p0;
    /**
     * the vector about the ray
     */
    private Vector dir;

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
     * @return p0
     */
    public Point3D getP0() {
        return p0;
    }

    /**
     * function get dir about the Ray
     * @return dir
     */
    public Vector getDir() {
        return dir;
    }

    @Override
    public String toString() {
        return "Ray{" +
                "p0=" + p0 +
                ", dir=" + dir +
                '}';
    }
}
