package com.plplsent.battleshooting.Game.Event;


import com.plplsent.battleshooting.Utils.DPoint;
import com.plplsent.battleshooting.Game.Entity.TeamGroup.TeamGroup;
import com.plplsent.battleshooting.Game.Field.Field;

import java.io.Serializable;

public abstract class PlayerEvent implements Event,Serializable {
    protected final TeamGroup.Team TEAM;
    protected final DPoint POSITION;

    public PlayerEvent(TeamGroup.Team team, DPoint pos) {
        TEAM = team;
        POSITION = pos;
    }



    @Override
    public TeamGroup.Team getTeam() {
        return TEAM;
    }

}
