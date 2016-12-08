package com.plplsent.battleshooting.Network;

import com.google.android.gms.games.multiplayer.realtime.RealTimeMessage;
import com.google.android.gms.games.multiplayer.realtime.RealTimeMessageReceivedListener;
import com.google.android.gms.games.multiplayer.realtime.Room;
import com.google.android.gms.nearby.messages.internal.Update;
import com.plplsent.battleshooting.Game.UpdateInfo;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.util.Collections;
import java.util.List;
import java.util.Queue;

public class MyNetwork implements RealTimeMessageReceivedListener {
    private List<UpdateInfo> infos;
    public MyNetwork(Room room) {

    }
    @Override
    public void onRealTimeMessageReceived(RealTimeMessage realTimeMessage) {
        ByteArrayInputStream bis = new ByteArrayInputStream(realTimeMessage.getMessageData());
        ObjectInput in = null;
        try {
            in = new ObjectInputStream(bis);
            Object o = in.readObject();
            if(o instanceof List){
                List<UpdateInfo> updatedInfo = (List<UpdateInfo>)o;
                infos = Collections.synchronizedList(updatedInfo);
            }
        }catch (IOException | ClassNotFoundException e){
            e.printStackTrace();
        } finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (IOException ex) {
                // ignore close exception
            }
        }
    }

    List<UpdateInfo> getEvents(){
        return infos;
    }
}
