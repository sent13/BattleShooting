package com.plplsent.battleshooting.Game.Entity.Bullets;


import android.util.SparseArray;

import com.plplsent.battleshooting.Game.DPoint;
import com.plplsent.battleshooting.Game.Entity.Entity;
import com.plplsent.battleshooting.Game.Field;
import com.plplsent.battleshooting.Utils.UnmodifiableSparseArray;

import java.util.EnumMap;
import java.util.Map;

public class Bullets {
    Map<Field.Team,SparseArray<Bullet>> bulletMap = new EnumMap<Field.Team,SparseArray<Bullet>>(Field.Team.class){{
        put(Field.Team.ME,new SparseArray<Bullet>());
        put(Field.Team.ENEMY,new SparseArray<Bullet>());
    }};
    public Bullets() {
    }

    public Entity getBullet(Field.Team team, int index) {
        return bulletMap.get(Field.Team.ME).get(index);
    }

    public void createNew(Field.Team team,DPoint startPos) {
        bulletMap.get(team).
    }
    public UnmodifiableSparseArray<Bullet> getBullets(Field.Team team){
        return new UnmodifiableSparseArray<>(bulletMap.get(team));
    }
}
