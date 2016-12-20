package com.plplsent.battleshooting.Game.Entity.Bullets;

import com.plplsent.battleshooting.Game.DPoint;
import com.plplsent.battleshooting.Game.Entity.Entity;


final class Bullet extends Entity implements Comparable<Bullet>{
    private final DPoint SPEED_VECTOR;
    private final int ID;

    Bullet(DPoint position, DPoint speedVector,int id) {
        super(position);
        SPEED_VECTOR = speedVector;
        ID = id;
    }

    @Override
    public void update() {
        move(SPEED_VECTOR);
    }

    @Override
    public int compareTo(Bullet o) {
        return getID()-o.getID();
    }

    public int getID() {
        return ID;
    }
}
