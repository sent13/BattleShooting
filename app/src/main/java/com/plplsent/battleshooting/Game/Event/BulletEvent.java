package com.plplsent.battleshooting.Game.Event;

import com.plplsent.battleshooting.Game.Field;


abstract class BulletEvent implements Event {
    protected final Field.Team TEAM;

    BulletEvent(Field.Team team) {
        TEAM = team;
    }

}
