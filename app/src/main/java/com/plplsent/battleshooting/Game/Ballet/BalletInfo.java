package com.plplsent.battleshooting.Game.Ballet;

import android.view.SurfaceView;

import com.plplsent.battleshooting.Game.DPoint;
import com.plplsent.battleshooting.Game.Field;
import com.plplsent.battleshooting.Game.UpdateInfo;

import java.io.Serializable;

public final class BalletInfo implements UpdateInfo ,Serializable{
    final private int id;
    final private DPoint point;

    public BalletInfo(int id, DPoint point) {
        this.id = id;
        this.point = point;
    }

    @Override
    public void apply(Field field) {
        field.getBallet(id).setPosition(point);
    }
}
