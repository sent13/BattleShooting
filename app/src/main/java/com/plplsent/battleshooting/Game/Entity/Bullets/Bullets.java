package com.plplsent.battleshooting.Game.Entity.Bullets;


import com.plplsent.battleshooting.Game.Entity.TeamGroup.TeamGroup;
import com.plplsent.battleshooting.Utils.DPoint;
import com.plplsent.battleshooting.Game.Entity.Entity;
import com.plplsent.battleshooting.Game.Entity.TeamGroup.GroupEntry;

import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public final class Bullets implements GroupEntry{

    private final BulletLifeManager LIFE_MANAGER;
    private Set<Bullet> bulletSet;

    Bullets(BulletLifeManager manager) {
        bulletSet =  new HashSet<>();
        LIFE_MANAGER = manager;
    }
    public Set<Bullet> getBullets(){
        return Collections.unmodifiableSet(bulletSet);
    }

    @Override
    public void update() {
        Iterator it = bulletSet.iterator();
        while(it.hasNext()){
            Bullet bullet = ((Bullet) it.next());
            bullet.update();
            if(LIFE_MANAGER.isDead(bullet)){
                it.remove();
            }
        }

    }

    public boolean isOverlap(Entity e){
        for (Bullet bullet : bulletSet) {
            if (bullet.getRect().intersect(e.getRect())) {
                return true;
            }
        }
        return false;
    }
    public void create(DPoint position, TeamGroup.Team team){
        bulletSet.add(LIFE_MANAGER.create(position,team));
    }

    static final class Bullet extends Entity{
        private final DPoint SPEED_VECTOR;

        Bullet(DPoint position, DPoint speedVector) {
            super(position,new DPoint(5,5));
            SPEED_VECTOR = speedVector;
        }

        @Override
        public void update() {
            move(SPEED_VECTOR);
        }

        //change

    }
}
