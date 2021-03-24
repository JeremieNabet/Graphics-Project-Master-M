package primitives;

import java.awt.*;
import java.util.Objects;

public class Vector {
    protected Point3D head;

    public Vector(Coordinate x , Coordinate y , Coordinate z) {
        if( (x == null) && ( y == null)  && (z ==null))
        {
            throw new IllegalArgumentException("cannot be a vector");
        }
        Point3D temp  = new Point3D(x,y,z) ;
        this.head = temp;
    }

    public Vector(double x , double y, double z) {

        if( (x == 0) && ( y == 0)  && (z == 0 ) )
        {
            throw new IllegalArgumentException("cannot be a firstVectorZero");
        }
        Point3D temp  = new Point3D(x,y,z) ;
        this.head = temp;
    }
    public Vector (Vector vector )
    {
        this.head = vector.head ;
    }
    public Vector (Point3D point3D)
    {
        if ((point3D.x.coord == 0) && (point3D.y.coord == 0) && (point3D.z.coord == 0))
        {
            throw new IllegalAccessError();
        }
        else
            this.head = point3D ;
    }
    public void setPt (Point3D pt)
    {
        this.head = pt ;
    }

    public Vector add(Vector vector){
        return new Vector(this.head.add(vector));
    }
    public Vector subtract(Vector vector){
        return this.head.subtract(vector.head);
    }

    public Vector crossProduct(Vector vector){
        if(vector.head.x.coord%this.head.x.coord==0&&vector.head.y.coord
                %this.head.y.coord==0&&vector.head.z.coord%this.head.z.coord==0)
        {
            throw new  ArithmeticException("devide by zero");
        }
        return  new Vector((this.head.y.coord*vector.head.z.coord)-(this.head.z.coord*vector.head.y.coord)
                ,(this.head.z.coord*vector.head.x.coord)-(this.head.x.coord*vector.head.z.coord),
                (this.head.x.coord*vector.head.y.coord)-(this.head.y.coord*vector.head.x.coord);

    }
    public double dotProduct(Vector vector)
    {
        return ((this.head.x.coord)*(vector.head.x.coord)) +
                (this.head.y.coord*vector.head.y.coord) +
                (this.head.z.coord*vector.head.z.coord);
    }
    public double lengthSquared(){
        return 3.4;
    }
    public double length(){

        return Math.sqrt((this.head.x.coord)*(this.head.x.coord)
                +(this.head.y.coord)*(this.head.y.coord)
                +(this.head.z.coord)*(this.head.z.coord));
    }
    public Vector normalize(){
       double xa = this.head.x.coord;
       double xb = this.head.x.coord;
       double xc = this.head.x.coord;
        Point3D temp = new Point3D(xa,xb,xc);
        Vector nouveau = new Vector((temp));
        if(nouveau.length() == 0){
            throw new ArithmeticException("Eroor! divide by zero");
        }
        this.head.x = new Coordinate(xa/nouveau.length());
        this.head.y = new Coordinate(xb/nouveau.length());
        this.head.z = new Coordinate(xc/nouveau.length());
        return this;
    }
    public Vector scale(double scalary){
        return new Vector((scalary*this.head.x.coord), (scalary*this.head.y.coord),(scalary*this.head.z.coord));
    }
    public Vector normalized()
    {
        return new Vector(this.normalize());
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
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
