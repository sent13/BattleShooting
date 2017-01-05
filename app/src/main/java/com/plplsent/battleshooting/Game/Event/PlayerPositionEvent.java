package com.plplsent.battleshooting.Game.Event;


import com.plplsent.battleshooting.Game.Entity.TeamGroup.TeamGroup;
import com.plplsent.battleshooting.Game.Field.Field;
import com.plplsent.battleshooting.Utils.DPoint;

public class PlayerPositionEvent extends PlayerEvent {
    public PlayerPositionEvent(TeamGroup.Team team, DPoint pos) {
        super(team, pos);
    }

    @Override
    public void apply(Field field) {
        field.getPlayer(TEAM).setPos(POSITION);
    }

    @Override
    public Event changeTEAM() {
        TeamGroup.Team team = this.TEAM== TeamGroup.Team.ENEMY? TeamGroup.Team.ME: TeamGroup.Team.ENEMY;
        DPoint pos = new DPoint(Field.FIELD_SIZE.getX()-POSITION.getX(),Field.FIELD_SIZE.getY()-POSITION.getY());
        return new PlayerPositionEvent(team, pos);
    }
}
