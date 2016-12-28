package com.plplsent.battleshooting.Game.Event;

import com.plplsent.battleshooting.Game.Entity.TeamGroup.TeamGroup;


abstract class BaseEvent implements Event {
    protected final TeamGroup.Team TEAM;

    BaseEvent(TeamGroup.Team team) {
        TEAM = team;
    }
    TeamGroup.Team getTeam(){
	return TEAM;
    }

}
