package com.plplsent.battleshooting.Game;


import com.plplsent.battleshooting.Game.Entity.Bullets.Bullets;
import com.plplsent.battleshooting.Game.Entity.Entity;
import com.plplsent.battleshooting.Game.Entity.Players.Players;
import com.plplsent.battleshooting.Game.Entity.TeamGroup;

public class Field {
    private final Players players;
    private final Bullets bullets;

    private Field() {
        players = new Players();
        bullets = new Bullets();
    }

    public Entity getPlayer(TeamGroup.Team team) {
        return players.getPlayer(team);
    }


    public void createBullet(TeamGroup.Team team, DPoint startPos){
        bullets.create(team,startPos);
    }
}
