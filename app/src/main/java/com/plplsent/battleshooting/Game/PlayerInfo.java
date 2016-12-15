package com.plplsent.battleshooting.Game;

import com.plplsent.battleshooting.Game.DPoint;
import com.plplsent.battleshooting.Game.Field;
import com.plplsent.battleshooting.Game.UpdateInfo;

import java.io.Serializable;

public final class PlayerInfo implements UpdateInfo ,Serializable{
    DPoint point;

    public PlayerInfo(DPoint point) {
        this.point = point;
    }

    @Override
    public void apply(Field field) {
        field.getEnemy().setPosition(point);
    }
}

