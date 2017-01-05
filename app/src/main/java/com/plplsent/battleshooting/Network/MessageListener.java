package com.plplsent.battleshooting.Network;

import com.google.android.gms.games.multiplayer.realtime.RealTimeMessage;
import com.google.android.gms.games.multiplayer.realtime.RealTimeMessageReceivedListener;

public class MessageListener implements RealTimeMessageReceivedListener  {
    private static MessageListener ourInstance = new MessageListener();
    private RealTimeMessageReceivedListener network = null;

    public static MessageListener getInstance() {
        return ourInstance;
    }

    private MessageListener() {
    }

    public void setNetWork(RealTimeMessageReceivedListener netWork){
        if(netWork == null) throw new IllegalArgumentException("networkが正しくありません");
        this.network = netWork;
    }

    public void removeNetWork(){
        network = null;
    }
    public boolean ready(){
        return network != null;
    }


    @Override
    public void onRealTimeMessageReceived(RealTimeMessage realTimeMessage) {
        if(network==null) throw new NullPointerException("networkがnull");
        network.onRealTimeMessageReceived(realTimeMessage);
    }

    public RealTimeMessageReceivedListener getNetWork() {
        return network;
    }
}
