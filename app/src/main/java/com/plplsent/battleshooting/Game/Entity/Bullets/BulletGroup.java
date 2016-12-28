package com.plplsent.battleshooting.Game.Entity.Bullets;

import com.plplsent.battleshooting.Game.Entity.TeamGroup.TeamGroup;

public class BulletGroup extends TeamGroup<Bullets> {


    public BulletGroup(float ScreenWidth,float ScreenHeight) {
        super(new Bullets(new BulletLifeManager(ScreenWidth,ScreenHeight))
                ,new Bullets(new BulletLifeManager(ScreenWidth,ScreenHeight)));
    }
}
