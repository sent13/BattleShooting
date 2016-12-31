package com.plplsent.battleshooting.Network;


import android.net.Network;

import com.google.android.gms.games.multiplayer.realtime.RealTimeMessage;
import com.google.android.gms.games.multiplayer.realtime.RealTimeMessageReceivedListener;

public class MessageListener implements RealTimeMessageReceivedListener{
    private MyNetwork network = null;
    public MessageListener() {
    }
    public void setNetWork(MyNetwork netWork){
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
        network.onRealTimeMessageReceived(realTimeMessage);
    }
}
