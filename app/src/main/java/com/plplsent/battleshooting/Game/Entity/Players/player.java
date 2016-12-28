package com.plplsent.battleshooting.Game.Entity.Players;


import com.plplsent.battleshooting.Utils.DPoint;
import com.plplsent.battleshooting.Game.Entity.Entity;
import com.plplsent.battleshooting.Game.Entity.TeamGroup.GroupEntry;

public class Player extends Entity implements GroupEntry{

    Player(DPoint position) {
        super(position,new DPoint(5,5));
    }
    @Override
    public void update() {

    }

}
