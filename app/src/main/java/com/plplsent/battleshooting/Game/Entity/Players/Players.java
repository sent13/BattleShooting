package com.plplsent.battleshooting.Game.Entity.Players;


import com.plplsent.battleshooting.Game.DPoint;
import com.plplsent.battleshooting.Game.Entity.Entity;
import com.plplsent.battleshooting.Game.Field;

public class Players {
    private Player mePlayer;
    private Player enemyPlayer;
    public Players() {
        mePlayer = new Player(new DPoint(0d,0d), Field.Team.ME);
        enemyPlayer = new Player(new DPoint(0d,0d), Field.Team.ENEMY);
    }

    public Player getEnemy() {
        return enemyPlayer;
    }

    public Player getMe() {
        return mePlayer;
    }


    public Player getPlayer(Field.Team team) {
        if(team == Field.Team.ME) return mePlayer;
        else return enemyPlayer;
    }
}
