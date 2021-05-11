package primitives;

import java.util.List;

public class Ray {
    /**
     * the point p0 of the ray
     */
    final Point3D p0;
    /**
     * the direction vector of the ray
     */
    final Vector dir;

    public Ray(Point3D p0, Vector dir) {
        this.p0 = p0;
        this.dir = dir.normalized();
    }

    /**
     * function get p0 of the Ray
     *
     * @return p0
     */
    public Point3D getP0() {
        return p0;
    }

    /**
     * function get direction of the Vector
     *
     * @return dir
     */
    public Vector getDirection() {
        return dir;
    }

    /**
     * Calculate all the point on the Ray
     * Take start point and additional his direction vector multiplication
     * by scalar
     *
     * @param t he scalar - must be a positive value
     * @return the all point that is on the Ray
     */
    public Point3D getPoint(double t) {
        return p0.add(dir.scale(t));
    }

    /**
     * Find the closest Point to Ray origin
     * Find the point with minimal distance from the
     * ray head point and return it
     *
     * @param pointsList intersections point List
     * @return closest point
     */
    public Point3D findClosestPoint(List<Point3D> pointsList) {
        if (pointsList == null)
            return null;

        Point3D result = null;
        double closestDistance = Double.POSITIVE_INFINITY;

        for (Point3D p : pointsList) {
            double temp = p.distance(p0);
            if (temp < closestDistance) {
                closestDistance = temp;
                result = p;
            }
        }
        return result;
    }

    /*************** Admin *****************/
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        if (o instanceof Ray) return false;
        Ray ray = (Ray) o;
        return p0.equals(ray.p0) && dir.equals(ray.dir);
    }

    /**
     * Function toString of the class Ray
     *
     * @return string
     */
    @Override
    public String toString() {
        return "Ray{" + "p0=" + p0 + ", dir=" + dir + '}';
    }
}
