package com.plplsent.battleshooting.Game.Event;


import com.plplsent.battleshooting.Game.DPoint;
import com.plplsent.battleshooting.Game.Entity.TeamGroup;
import com.plplsent.battleshooting.Game.Field;

public class PlayerEvent implements Event{
    private final TeamGroup.Team TEAM;
    private final DPoint DELTA_MOVE;

    public PlayerEvent(TeamGroup.Team team, DPoint delatMove) {
        TEAM = team;
        DELTA_MOVE = delatMove;
    }

    @Override
    public void apply(Field field) {
        field.getPlayer(TEAM).move(DELTA_MOVE);
    }
}
