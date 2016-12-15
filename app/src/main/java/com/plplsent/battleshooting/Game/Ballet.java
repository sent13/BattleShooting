package com.plplsent.battleshooting.Game;

import com.plplsent.battleshooting.Game.DPoint;

/**
 * Created by plpl on 2016/12/08.
 */

public class Ballet {
    private DPoint position;
    private int ID;
    public Ballet(int ID,double x,double y) {
        this.ID = ID;
        this.position = new DPoint(x,y);
    }

    void setPosition(DPoint p){
        position = p;
    }
    public DPoint getPosition(){
        return position;
    }

    public int getID() {
        return ID;
    }
}
