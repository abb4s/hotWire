/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author 92463111
 */
public class Point {
    Double x;
    Double y;
    Double z;
    int index=0;
    public Point(Double x,Double y,Double z){
        setX(x);
        setY(y);
        setZ(z);
    }
    public Double getX() {
        return x;
    }

    public Double getY() {
        return y;
    }

    public Double getZ() {
        return z;
    }
    

    public void setX(Double x) {
        this.x = x;
    }

    public void setY(Double y) {
        this.y = y;
    }

    public void setZ(Double z) {
        this.z = z;
    }
    
}
