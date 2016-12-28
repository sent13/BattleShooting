package com.plplsent.battleshooting.Game.Entity.Bullets;

import android.graphics.RectF;

import com.plplsent.battleshooting.Utils.DPoint;

public class BulletLifeManager {

    public  final static DPoint BULLET_SPEED = new DPoint(3,3);
    private final RectF SCREEN;

    public BulletLifeManager(float screenWidth, float screenHeight) {
        SCREEN = new RectF(0,0,screenWidth,screenHeight);
    }

    boolean isDead(Bullets.Bullet bullet){
        return !SCREEN.contains(bullet.getRect());
    }

    Bullets.Bullet create(DPoint pos){
        return new Bullets.Bullet(pos,BULLET_SPEED);
    }

}
