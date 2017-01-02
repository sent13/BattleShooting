package com.plplsent.battleshooting.Game;

import com.plplsent.battleshooting.Game.Event.Event;
import com.plplsent.battleshooting.Game.Field.Field;
import com.plplsent.battleshooting.Network.MyNetwork;

import java.util.ArrayDeque;
import java.util.Collections;
import java.util.Deque;

public class MyGameAPI implements GameAPI{
    private final MyNetwork NETWORK;
    Deque<Event> eventDeque = new ArrayDeque<>();
    Field field;


    public MyGameAPI(MyNetwork network) {
        NETWORK = network;
    }

    /**
     * イベントを待ち行列に追加します。
     * @param event
     */
    @Override
    public void addEvent(Event event) {
        eventDeque.offerLast(event);
    }

    @Override
    public void update() {
        NETWORK.sendMessage("OMG");
        doingEvent();
    }
    /**
     * たまっているイベントを処理します。
     */
    public void doingEvent(){
        Event e;
        while(null != (e = eventDeque.pollFirst())){
            e.apply(field);
        }
    }



}
