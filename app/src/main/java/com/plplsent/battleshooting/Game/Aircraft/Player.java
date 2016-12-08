package com.plplsent.battleshooting.Game.Aircraft;

import com.plplsent.battleshooting.Game.DPoint;

class Player {
    private DPoint position;
    public Player(double x,double y) {
        position = new DPoint(x,y);
    }

    void setPosition(DPoint pos) {
        this.position = pos;
    }
}
