package com.plplsent.battleshooting.Game.Field;


import com.plplsent.battleshooting.Utils.DPoint;
import com.plplsent.battleshooting.Game.Entity.Bullets.BulletGroup;
import com.plplsent.battleshooting.Game.Entity.Entity;
import com.plplsent.battleshooting.Game.Entity.Players.PlayerGroup;
import com.plplsent.battleshooting.Game.Entity.TeamGroup.TeamGroup;

public class Field {
    private final PlayerGroup players;
    private final BulletGroup bulletGroup;
    private final DPoint FIELD_SIZE = new DPoint(16*100,9*100);
    private Field() {
        players = new PlayerGroup();
        bulletGroup = new BulletGroup(FIELD_SIZE);
    }

    public Entity getPlayer(TeamGroup.Team team) {
        return players.getAll(team);
    }


    public void createBullet(TeamGroup.Team team, DPoint startPos){
        bulletGroup.getAll(team).create(startPos);
    }
}
