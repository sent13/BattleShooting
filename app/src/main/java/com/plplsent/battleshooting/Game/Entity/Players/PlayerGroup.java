package com.plplsent.battleshooting.Game.Entity.Players;


import com.plplsent.battleshooting.Utils.DPoint;
import com.plplsent.battleshooting.Game.Entity.TeamGroup.TeamGroup;

public class PlayerGroup extends TeamGroup<Player>{
    public PlayerGroup() {
        super(new Player(new DPoint(0d,0d)),
                new Player(new DPoint(0d,0d)));
    }


}
