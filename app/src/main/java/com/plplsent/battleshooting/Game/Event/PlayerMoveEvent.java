package com.plplsent.battleshooting.Game.Event;


import com.plplsent.battleshooting.Game.Entity.Players.Player;
import com.plplsent.battleshooting.Game.Entity.TeamGroup.TeamGroup;
import com.plplsent.battleshooting.Game.Field.Field;
import com.plplsent.battleshooting.Utils.DPoint;

public class PlayerMoveEvent extends PlayerEvent {
    public PlayerMoveEvent(TeamGroup.Team team, DPoint deltaMove) {
        super(team, deltaMove);
    }

    @Override
    public void apply(Field field) {
        field.getPlayer(TEAM).move(POSITION);
    }
    @Override
    public Event changeTEAM() {
        TeamGroup.Team team = this.TEAM== TeamGroup.Team.ENEMY? TeamGroup.Team.ME: TeamGroup.Team.ENEMY;
        DPoint pos = new DPoint(Field.FIELD_SIZE.getX()-POSITION.getX(),Field.FIELD_SIZE.getY()-POSITION.getY());
        return new PlayerMoveEvent(team, pos);
    }
}
