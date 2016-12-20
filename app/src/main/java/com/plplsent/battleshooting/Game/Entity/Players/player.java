package com.plplsent.battleshooting.Game.Entity.Players;


import com.plplsent.battleshooting.Game.DPoint;
import com.plplsent.battleshooting.Game.Entity.Entity;
import com.plplsent.battleshooting.Game.Event.Event;
import com.plplsent.battleshooting.Game.Field;

class Player extends Entity {

    private final Field.Team TEAM;

    Player(DPoint position, Field.Team team) {
        super(position);
        TEAM = team;
    }
    public Field.Team getTeam(){
        return TEAM;
    }

    @Override
    public void update() {

    }
}
