package com.plplsent.battleshooting.Game.Entity.Bullets;

import com.plplsent.battleshooting.Game.DPoint;
import com.plplsent.battleshooting.Game.Entity.Entity;


final class Bullet extends Entity{
    private final DPoint SPEED_VECTOR;

    Bullet(DPoint position, DPoint speedVector) {
        super(position,new DPoint(5,5));
        SPEED_VECTOR = speedVector;
    }

    @Override
    public void update() {
        move(SPEED_VECTOR);
    }

}
