package com.plplsent.battleshooting.Game.Event;

import com.plplsent.battleshooting.Game.Field;


abstract class BaseEvent implements Event {
    protected final Field.Team TEAM;

    BaseEvent(Field.Team team) {
        TEAM = team;
    }
    Field.Team getTeam(){
	return TEAM;
    }

}
