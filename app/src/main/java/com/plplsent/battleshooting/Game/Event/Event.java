package com.plplsent.battleshooting.Game.Event;

import com.plplsent.battleshooting.Game.Entity.TeamGroup.TeamGroup;
import com.plplsent.battleshooting.Game.Field.Field;

public interface Event {
    void apply(Field field);
    TeamGroup.Team getTeam();

    Event changeTEAM();
}
