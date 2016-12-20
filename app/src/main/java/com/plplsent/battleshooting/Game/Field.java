package com.plplsent.battleshooting.Game;


import com.plplsent.battleshooting.Game.Entity.Bullets.Bullets;
import com.plplsent.battleshooting.Game.Entity.Entity;
import com.plplsent.battleshooting.Game.Entity.Players.Players;
import com.plplsent.battleshooting.Game.Event.Event;

public class Field {
    private final Players players;
    private final Bullets bullets;

    public enum Team {
        ME,ENEMY
    }
    private Field() {
        players = new Players();
        bullets = new Bullets();
    }

    public Entity getPlayer(Team team) {
        return players.getPlayer(team);
    }


    public Entity getBallet(Team team, int index) {
        return bullets.getBullet(team,index);
    }
    public void createBullet(Team team,DPoint startPos){
        bullets.createNew(team,startPos);
    }
}
