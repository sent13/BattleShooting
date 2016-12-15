package com.plplsent.battleshooting.Game;

import com.plplsent.battleshooting.Game.Event.Event;

import java.util.ArrayDeque;
import java.util.Deque;

public class MyGameAPI implements GameAPI{
    Deque<Event> eventDeque = new ArrayDeque<>();
    Field field;
    public MyGameAPI() {
    }

    /**
     * イベントを待ち行列に追加します。
     * @param event
     */
    @Override
    public void addEvent(Event event) {
        eventDeque.offerLast(event);
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
