package com.plplsent.battleshooting.Network;

import android.os.Debug;
import android.util.Log;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.games.Games;
import com.google.android.gms.games.multiplayer.Participant;
import com.google.android.gms.games.multiplayer.realtime.RealTimeMessage;
import com.google.android.gms.games.multiplayer.realtime.RealTimeMessageReceivedListener;
import com.plplsent.battleshooting.Game.Event.Event;
import com.plplsent.battleshooting.Game.GameAPI;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.nio.ByteBuffer;
import java.util.Collections;
import java.util.List;

public class MyNetwork{
    private final GoogleApiClient client;
    private final String ROOMID;
    private List<Event> info;
    Participant otherPlayer;
    boolean isRecieve = false;
    public MyNetwork(GoogleApiClient client,Participant p1,String RoomID) {
        this.client = client;
        otherPlayer = p1;
        ROOMID = RoomID;
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
        isRecieve =  true;
        byte[] msg  = realTimeMessage.getMessageData();
        try {
            ByteArrayInputStream bis = new ByteArrayInputStream(msg);
            ObjectInput in = new ObjectInputStream(bis);
            try{
                Object o = in.readObject();
                if (o instanceof List) {
                    List<Event> updatedInfo = (List<Event>) o;
                    info = Collections.synchronizedList(updatedInfo);
                }
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
                Log.e("tag","不正なデータです ");
            } finally {
                try {
                    if (in != null) {
                        in.close();
                    }
                } catch (IOException ex) {
                    // ignore close exception
                }
            }
        }catch (IOException e) {
            //Data was not Objects
            Log.i("tag","getMessage : "+new String(msg));
        }


    }

    public boolean isExistNewData(){
        boolean result = isRecieve;
        isRecieve = false;
        return result;
    }
    public List<Event> getEvent(){
        return info;
    }

    public void sendMessage(String msg) {
        Log.i("tag","send message : "+ msg);
        Games.RealTimeMultiplayer.sendReliableMessage(client, null, msg.getBytes(),
                ROOMID, otherPlayer.getParticipantId());
    }
}
