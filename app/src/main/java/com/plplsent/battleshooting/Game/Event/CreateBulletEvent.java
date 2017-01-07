package com.plplsent.battleshooting.Game.Event;


import com.plplsent.battleshooting.Utils.DPoint;
import com.plplsent.battleshooting.Game.Entity.TeamGroup.TeamGroup;
import com.plplsent.battleshooting.Game.Field.Field;

import java.io.Serializable;

public final class CreateBulletEvent implements Event,Serializable {
    private final DPoint START_POSITION;
    private final TeamGroup.Team TEAM;
    public CreateBulletEvent(TeamGroup.Team t,DPoint startPos) {
        TEAM = t;
        START_POSITION = startPos;
    }

    @Override
    public void apply(Field field) {
        field.createBullet(TEAM,START_POSITION);
    }
    @Override
    public TeamGroup.Team getTeam() {
        return TEAM;
    }

    @Override
    public Event changeTEAM() {
        TeamGroup.Team team = this.TEAM== TeamGroup.Team.ENEMY? TeamGroup.Team.ME: TeamGroup.Team.ENEMY;
        DPoint pos = new DPoint(Field.FIELD_SIZE.getX()-START_POSITION.getX(),Field.FIELD_SIZE.getY()-START_POSITION.getY());
        return new CreateBulletEvent(team,pos);
    }
}
