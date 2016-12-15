package com.plplsent.battleshooting.Game;

import com.plplsent.battleshooting.Game.DPoint;

public class Player {
    private DPoint position;
    public Player(double x,double y) {
        position = new DPoint(x,y);
    }

    void setPosition(DPoint pos) {
        this.position = pos;
    }
}
