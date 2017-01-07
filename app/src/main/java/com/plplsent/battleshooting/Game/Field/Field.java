package com.plplsent.battleshooting.Game.Field;


import com.plplsent.battleshooting.Game.Entity.Bullets.BulletGroup;
import com.plplsent.battleshooting.Game.Entity.Bullets.Bullets;
import com.plplsent.battleshooting.Game.Entity.Entity;
import com.plplsent.battleshooting.Game.Entity.Players.PlayerGroup;
import com.plplsent.battleshooting.Game.Entity.TeamGroup.TeamGroup;
import com.plplsent.battleshooting.Utils.DPoint;

import java.util.Set;

public class Field {
    private final PlayerGroup players;
    private final BulletGroup bulletGroup;
    public static final DPoint FIELD_SIZE = new DPoint( 9 * 100,16 * 100);
    private boolean isEnd = false;
    private TeamGroup.Team winTeam;

    public Field() {
        players = new PlayerGroup();
        bulletGroup = new BulletGroup(FIELD_SIZE);
    }

    public Entity getPlayer(TeamGroup.Team team) {
        return players.get(team);
    }


    public void createBullet(TeamGroup.Team team, DPoint startPos) {
        bulletGroup.get(team).create(startPos,team);
    }

    public void update() {
        if(!isEnd){
            //自分の玉と敵の位置のみ判定
            if (bulletGroup.get(TeamGroup.Team.ME).isOverlap(getPlayer(TeamGroup.Team.ENEMY))) {
                win();
            }
            bulletGroup.get(TeamGroup.Team.ME).update();
            bulletGroup.get(TeamGroup.Team.ENEMY).update();
        }
    }


    public void win(){
        isEnd = true;
        winTeam = TeamGroup.Team.ME;
    }
    public void lose(){
        isEnd = true;
        winTeam = TeamGroup.Team.ENEMY;
    }
    public boolean isEnd() {
        return isEnd;
    }

    public TeamGroup.Team getWinTeam() {
        if(!isEnd){
            throw new IllegalStateException("まだ勝敗が決まっていません");
        }
        return winTeam;
    }

    public Bullets getBullet(TeamGroup.Team team) {
        return bulletGroup.get(team);
    }
}
