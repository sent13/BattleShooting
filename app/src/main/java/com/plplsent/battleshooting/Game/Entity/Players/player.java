package com.plplsent.battleshooting.Game.Entity.Players;


import com.plplsent.battleshooting.Game.DPoint;
import com.plplsent.battleshooting.Game.Entity.Entity;
import com.plplsent.battleshooting.Game.Entity.TeamGroup;

class Player extends Entity {

    private final TeamGroup.Team TEAM;

    Player(DPoint position, TeamGroup.Team team) {
        super(position,new DPoint(5,5));
        TEAM = team;
    }
    public TeamGroup.Team getTeam(){
        return TEAM;
    }

    @Override
    public void update() {

    }
}
