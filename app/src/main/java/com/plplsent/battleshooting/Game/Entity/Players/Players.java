package com.plplsent.battleshooting.Game.Entity.Players;


import com.plplsent.battleshooting.Game.DPoint;
import com.plplsent.battleshooting.Game.Entity.TeamGroup;

public class Players {
    private Player mePlayer;
    private Player enemyPlayer;
    public Players() {
        mePlayer = new Player(new DPoint(0d,0d), TeamGroup.Team.ME);
        enemyPlayer = new Player(new DPoint(0d,0d), TeamGroup.Team.ENEMY);
    }

    public Player getEnemy() {
        return enemyPlayer;
    }

    public Player getMe() {
        return mePlayer;
    }


    public Player getPlayer(TeamGroup.Team team) {
        if(team == TeamGroup.Team.ME) return mePlayer;
        else return enemyPlayer;
    }
}
