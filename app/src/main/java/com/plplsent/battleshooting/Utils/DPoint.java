package com.plplsent.battleshooting.Utils;


import java.io.Serializable;


public class DPoint implements Serializable{
    private double x;
    private double y;

    public DPoint(double x, double y) {
        this.x = x;
        this.y = y;
    }
    public double getX(){
        return x;
    }
    public double getY(){
        return y;
    }
    public DPoint add(DPoint dPoint){
        return new DPoint(getX()+dPoint.getX(),getY()+dPoint.getY());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DPoint dPoint = (DPoint) o;

        if (Double.compare(dPoint.x, x) != 0) return false;
        return Double.compare(dPoint.y, y) == 0;

    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        temp = Double.doubleToLongBits(x);
        result = (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(y);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }
}
