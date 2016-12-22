package com.plplsent.battleshooting.Game.Event;


import com.plplsent.battleshooting.Game.DPoint;
import com.plplsent.battleshooting.Game.Field;

public class CreateBulletEvnet extends BaseEvent{
    private final DPoint START_POSITION;

    public CreateBulletEvnet(Field.Team team, DPoint startPos) {
        super(team);
        START_POSITION = startPos;

    }

    @Override
    public void apply(Field field) {
        field.createBullet(TEAM,START_POSITION);
    }
}
