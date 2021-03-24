package primitives;

import java.util.Objects;

public class Point3D {

    protected Coordinate x;
    protected Coordinate y;
    protected Coordinate z;

    public static final Point3D ZERO = new Point3D(0,0,0);
    public static Point3D getZERO() {
        return ZERO;
    }

    public Point3D add(Vector a){
        Point3D point= new Point3D((this.x.coord+a.head.x.coord),(this.y.coord+a.head.y.coord),
                (this.z.coord+a.head.z.coord));
        return point ;
    }

    public Vector subtract(Point3D obj){

        Vector temp=new Vector(this.x.coord-obj.x.coord ,this.y.coord-obj.y.coord,this.z.coord-obj.z.coord);

        return temp;
    }

    public double distanceSquared(Point3D point3D_1, Point3D point3D_2){
        double distance=  ((point3D_1.x.coord-point3D_2.x.coord)*
                (point3D_1.x.coord-point3D_2.x.coord)+
                (point3D_1.y.coord-point3D_2.y.coord)*
                        (point3D_1.y.coord-point3D_2.y.coord)+
                (point3D_1.z.coord-point3D_2.z.coord)*
                        (point3D_1.z.coord-point3D_2.z.coord));
        return distance;
    }

    public double distance (Point3D a , Point3D b)
    /**
     distance between 2 point ^2 after we do the sqrt to get the distance
     */
    {
        double y=distanceSquared(a,b);
        return Math.sqrt(y);
    }

    public Point3D(double x, double y, double z) {
        this.x = new Coordinate(x);
        this.y=new Coordinate(y);
        this.z=new Coordinate(z);
    }
    public Point3D(Coordinate a,Coordinate b,Coordinate c)
    {
        this.x=a;
        this.y=b;
        this.z=c;

    }

    public Point3D (Point3D a) {
        this.x=a.x;
        this.y=a.y;
        this.z=a.z;
    }









    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Point3D)) return false;
        Point3D point3D = (Point3D) o;
        return x.equals(point3D.x) && y.equals(point3D.y) && z.equals(point3D.z);
    }


    @Override
    public String toString() {
        return "Point3D{" +
                "x=" + x +
                ", y=" + y +
                ", z=" + z +
                '}';
    }
    public Coordinate getX() {
        return x;
    }

    public Coordinate getY() {
        return y;
    }

    public Coordinate getZ() {
        return z;
    }

}
