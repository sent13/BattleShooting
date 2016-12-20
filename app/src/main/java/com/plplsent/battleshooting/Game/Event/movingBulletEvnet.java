package com.plplsent.battleshooting.Game.Event;


import com.plplsent.battleshooting.Game.DPoint;
import com.plplsent.battleshooting.Game.Field;

public class movingBulletEvnet extends BulletEvent {
    private final DPoint POSITION;
    protected final int INDEX;

    public movingBulletEvnet(Field.Team team, int index, DPoint point) {
        super(team);
        INDEX = index;
        POSITION = point;
    }

    @Override
    public void apply(Field field) {
        field.getBallet(TEAM,INDEX).setPos(POSITION);
    }
}
