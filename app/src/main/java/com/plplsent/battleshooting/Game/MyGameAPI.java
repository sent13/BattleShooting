package com.plplsent.battleshooting.Game;

import android.util.Log;

import com.plplsent.battleshooting.Game.Entity.TeamGroup.TeamGroup;
import com.plplsent.battleshooting.Game.Event.CreateBulletEvent;
import com.plplsent.battleshooting.Game.Event.Event;
import com.plplsent.battleshooting.Game.Event.PlayerPositionEvent;
import com.plplsent.battleshooting.Game.Field.Field;
import com.plplsent.battleshooting.Network.MyNetwork;

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
        if (isGameEnd()) {
            field.update();
            doingEvent();
            networkUpdate();
        } else {
            if (field.getWinTeam() == TeamGroup.Team.ME)
                NETWORK.sendMessage("win");
        }
    }


    private void networkUpdate() {
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
        sendMessage.add(new PlayerPositionEvent(TeamGroup.Team.ENEMY, field.getPlayer(TeamGroup.Team.ME).getPosition()));
        sendMessage.addAll(NotSendData);
        NETWORK.send(sendMessage);
    }

    /**
     * たまっているイベントを処理します。
     */
    private void doingEvent() {
        Event e;
        while (null != (e = eventDeque.poll())) {
            if (e instanceof CreateBulletEvent && e.getTeam() == TeamGroup.Team.ME) {
                NotSendData.add(e.changeTEAM());
            }
            e.apply(field);
        }
    }

}
