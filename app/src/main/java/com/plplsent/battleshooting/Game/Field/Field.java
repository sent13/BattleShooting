package com.plplsent.battleshooting.Game.Field;


import com.plplsent.battleshooting.Utils.DPoint;
import com.plplsent.battleshooting.Game.Entity.Bullets.BulletGroup;
import com.plplsent.battleshooting.Game.Entity.Entity;
import com.plplsent.battleshooting.Game.Entity.Players.PlayerGroup;
import com.plplsent.battleshooting.Game.Entity.Tea

mGroup.TeamGroup;

public class Field {
    private final PlayerGroup players;
    private final BulletGroup bulletGroup;   
    
    private final DPoint FIELD_POINT = new DPoint(500,500);
    private Field() {
        players = new PlayerGroup();
        bulletGroup = new BulletGroup(FIELD_POINT);
    }

    public Entity getPlayer(TeamGroup.Team team) {
        return players.getAll(team);
    }


    public void createBullet(TeamGroup.Team team, DPoint startPos){
        bulletGroup.getAll(team).create(startPos);
    }
}
