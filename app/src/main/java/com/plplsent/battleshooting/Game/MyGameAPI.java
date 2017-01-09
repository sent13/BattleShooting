package com.plplsent.battleshooting.Game;

import android.graphics.RectF;
import android.util.Log;

import com.plplsent.battleshooting.Game.Entity.Bullets.Bullets;
import com.plplsent.battleshooting.Game.Entity.TeamGroup.TeamGroup;
import com.plplsent.battleshooting.Game.Event.CreateBulletEvent;
import com.plplsent.battleshooting.Game.Event.Event;
import com.plplsent.battleshooting.Game.Event.PlayerMoveEvent;
import com.plplsent.battleshooting.Game.Event.PlayerPositionEvent;
import com.plplsent.battleshooting.Game.Field.Field;
import com.plplsent.battleshooting.Network.MyNetwork;
import com.plplsent.battleshooting.StopWatch;
import com.plplsent.battleshooting.Utils.DPoint;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class MyGameAPI implements GameAPI {
    private final MyNetwork NETWORK;

    //Queue of Input and Network Events
    private Queue<Event> eventDeque = new ArrayDeque<>();

    //Queue of Sent Message via NetWork
    private Queue<Event> NotSendData = new LinkedList<>();

    private Field field;
    private Long lastReceive;


    public MyGameAPI(MyNetwork network) {
        NETWORK = network;
        field = new Field();
    }

    public boolean isGameEnd() {
        return field.isEnd();
    }

    /**
     * イベントを待ち行列に追加します。
     *
     * @param event
     */
    @Override
    public void addEvent(Event event) {
        eventDeque.add(event);
    }

    @Override
    public void update() {
        if (!isGameEnd()) {
            StopWatch.start();
            doingEvent();
            StopWatch.printMicroTime("doing Event :");
            field.update();
            StopWatch.printMicroTime("field Update :");
            networkUpdate();
            StopWatch.printMicroTime("Network Update :");


        } else {
            if (field.getWinTeam() == TeamGroup.Team.ME)
                NETWORK.sendMessage("win");
        }
    }

    @Override
    public boolean isTouchingPlayer(float touchedX, float touchedY) {
        return field.getPlayer(TeamGroup.Team.ME).getRectF().contains(touchedX, touchedY);
    }

    @Override
    public void fireBallet() {
        this.addEvent(new CreateBulletEvent(TeamGroup.Team.ME, field.getPlayer(TeamGroup.Team.ME).getPosition()));
    }

    @Override
    public void movePlayer(float touchedX, float touchedY) {
        Log.i("tag", "move x :" + touchedX + " y :" + touchedY);
        this.addEvent(new PlayerMoveEvent(TeamGroup.Team.ME, new DPoint(touchedX, touchedY)));
    }

    @Override
    public RectF getPlayerRectF() {
        return field.getPlayer(TeamGroup.Team.ME).getRectF();
    }

    @Override
    public Bullets getBullets(TeamGroup.Team team) {
        return field.getBullet(team);
    }

    @Override
    public RectF getEnemyRectF() {
        return field.getPlayer(TeamGroup.Team.ENEMY).getRectF();
    }


    private void networkUpdate() {
        if (lastReceive == null) {
            lastReceive = System.currentTimeMillis();
            return;
        }
        if (NETWORK.isLose()) {
            field.lose();
        }
        NETWORK.sendMessage("alive");
        this.sendUpdateInfo();
        doingEvent();

        if (NETWORK.isReceiveNewData()) {
            lastReceive = System.currentTimeMillis();
        }
        if (System.currentTimeMillis() - lastReceive > 10000) {
            Log.e("tag", "ERROR CONNECTION HAS EXPIRED");
        }

    }

    private void sendUpdateInfo() {
        List<Event> sendMessage = new ArrayList<>();
        PlayerPositionEvent event = new PlayerPositionEvent(TeamGroup.Team.ME, field.getPlayer(TeamGroup.Team.ME).getPosition());
        sendMessage.add(event.changeTEAM());
        sendMessage.addAll(NotSendData);
        NotSendData.clear();
        NETWORK.send(sendMessage);
    }

    /**
     * たまっているイベントを処理します。
     */
    private void doingEvent() {
        Event e;
        while (null != (e = NETWORK.getEvent().poll())) {

            Log.i("tag", e.getClass().toString() + " class Event Network");
            e.apply(field);
        }

        while (null != (e = eventDeque.poll())) {
            if (e instanceof CreateBulletEvent && e.getTeam() == TeamGroup.Team.ME) {
                NotSendData.add(e.changeTEAM());
            }
            Log.i("tag", e.getClass().toString() + " class Event");
            e.apply(field);
        }
    }

}
