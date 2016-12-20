package com.plplsent.battleshooting.Game;


import java.io.Serializable;


public class DPoint implements Serializable{
    private double x;
    private double y;

    public DPoint(double x, double y) {
        this.x = x;
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
}
