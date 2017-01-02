package com.plplsent.battleshooting.Game.Entity.Bullets;

import com.plplsent.battleshooting.Game.Entity.TeamGroup.TeamGroup;
import com.plplsent.battleshooting.Utils.DPoint;

public class BulletGroup extends TeamGroup<Bullets> {


    public BulletGroup(DPoint field_size) {
        super(new Bullets(new BulletLifeManager((float)field_size.getY(), ((float) field_size.getY())))
                ,new Bullets(new BulletLifeManager(((float) field_size.getX()), (float)field_size.getY())));
    }
}
