package com.plplsent.battleshooting.Game.Entity.Players;


import com.plplsent.battleshooting.Game.Field.Field;
import com.plplsent.battleshooting.Utils.DPoint;
import com.plplsent.battleshooting.Game.Entity.TeamGroup.TeamGroup;

public class PlayerGroup extends TeamGroup<Player>{
    private final static DPoint ME_INIT_POS_RATE = new DPoint(0.5,0.75);
    private final static DPoint ENEMY_INIT_POS_RATE = new DPoint(0.5,0.25);
    public PlayerGroup() {
        super(new Player(new DPoint(Field.FIELD_SIZE.getX()*ME_INIT_POS_RATE.getX(),Field.FIELD_SIZE.getY()*ME_INIT_POS_RATE.getY())),
                new Player(new DPoint(Field.FIELD_SIZE.getX()*ENEMY_INIT_POS_RATE.getX(),Field.FIELD_SIZE.getY()*ENEMY_INIT_POS_RATE.getY())));
    }


}
