package com.plplsent.battleshooting.Game;

import android.graphics.RectF;

import com.plplsent.battleshooting.Game.Entity.Bullets.Bullets;
import com.plplsent.battleshooting.Game.Entity.TeamGroup.TeamGroup;
import com.plplsent.battleshooting.Game.Event.Event;

public interface GameAPI {

    void addEvent(Event event);


    void update();

    boolean isTouchingPlayer(float touchedX, float touchedY);

    void fireBallet();

    void movePlayer(float touchedX, float touchedY);

    RectF getPlayerRectF();

    Bullets getBullets(TeamGroup.Team team);

    RectF getEnemyRectF();
}
