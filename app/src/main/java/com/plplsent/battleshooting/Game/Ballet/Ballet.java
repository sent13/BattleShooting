package com.plplsent.battleshooting.Game.Ballet;

import com.plplsent.battleshooting.Game.DPoint;

/**
 * Created by plpl on 2016/12/08.
 */

class Ballet {
    private DPoint position;

    public Ballet(double x,double y) {
        this.position = new DPoint(x,y);
    }

    void setPosition(DPoint p){
        position = p;
    }
    DPoint getPosition(){
        return position;
    }

}
