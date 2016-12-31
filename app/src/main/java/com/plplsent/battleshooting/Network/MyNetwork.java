package com.plplsent.battleshooting.Network;

import android.util.Log;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.games.multiplayer.Participant;
import com.google.android.gms.games.multiplayer.realtime.RealTimeMessage;
import com.google.android.gms.games.multiplayer.realtime.RealTimeMessageReceivedListener;
import com.plplsent.battleshooting.Game.Event.Event;
import com.plplsent.battleshooting.Game.GameAPI;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.util.Collections;
import java.util.List;

public class MyNetwork{
    private final GoogleApiClient client;
    private List<Event> info;
    private GameAPI gameAPI;
    Participant otherPlayer;
    public MyNetwork(GameAPI gameAPI,GoogleApiClient client,Participant p1) {
        this.client = client;
        this.gameAPI = gameAPI;
        otherPlayer = p1;
    }
/**
    public void send(DPoint playerPos, List<> ballets) {
        List<Event> info = new ArrayList<>();
        info.add(new PlayerInfo(playerPos));
        for (Ballet ballet : ballets) {
            info.add(new BalletInfo(ballet.getID(), ballet.getPosition()));
        }
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutput out = null;
        try {
            out = new ObjectOutputStream(bos);
            out.writeObject(info);
            out.flush();
            byte[] bytes = bos.toByteArray();

            Games.RealTimeMultiplayer.sendReliableMessage(client, null, bytes, roomID, participant.getParticipantId());
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                bos.close();
            } catch (IOException ex) {
                // ignore close exception
            }
        }
    }
*/

    void onRealTimeMessageReceived(RealTimeMessage realTimeMessage) {
        ByteArrayInputStream bis = new ByteArrayInputStream(realTimeMessage.getMessageData());
        ObjectInput in = null;
        try {
            in = new ObjectInputStream(bis);
            Object o = in.readObject();
            if (o instanceof List) {
                List<Event> updatedInfo = (List<Event>) o;
                info = Collections.synchronizedList(updatedInfo);
            }
            Log.e("tag","不正なデータです");
        } catch (IOException | ClassNotFoundException e) {
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

    public List<Event> getEvent(){
        return info;
    }

}
