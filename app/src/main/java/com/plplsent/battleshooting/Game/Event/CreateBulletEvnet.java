package com.plplsent.battleshooting.Game.Event;


import com.plplsent.battleshooting.Utils.DPoint;
import com.plplsent.battleshooting.Game.Entity.TeamGroup.TeamGroup;
import com.plplsent.battleshooting.Game.Field.Field;

public class CreateBulletEvnet extends BaseEvent{
    private final DPoint START_POSITION;

    public CreateBulletEvnet(TeamGroup.Team team, DPoint startPos) {
        super(team);
        START_POSITION = startPos;

    }

    @Override
    public void apply(Field field) {
        field.createBullet(TEAM,START_POSITION);
    }
}
