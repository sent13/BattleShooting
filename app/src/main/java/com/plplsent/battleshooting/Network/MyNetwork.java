package com.plplsent.battleshooting.Network;

import android.util.Log;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.games.Games;
import com.google.android.gms.games.multiplayer.Participant;
import com.google.android.gms.games.multiplayer.realtime.RealTimeMessage;
import com.google.android.gms.games.multiplayer.realtime.RealTimeMessageReceivedListener;
import com.plplsent.battleshooting.Game.Event.Event;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.io.StreamCorruptedException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.LinkedBlockingQueue;

public class MyNetwork implements RealTimeMessageReceivedListener{
    private final GoogleApiClient client;
    private final String ROOM_ID;
    private final BlockingQueue<Event> info = new LinkedBlockingQueue<>();
    Participant otherPlayer;
    private boolean isReceive = false;
    private boolean isLose = false;

    public MyNetwork(GoogleApiClient client,Participant p1,String RoomID) {
        this.client = client;
        otherPlayer = p1;
        ROOM_ID = RoomID;
    }

    public void send(List<Event> event) {
        Log.i("tag","sendMessage");
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutput out;
        try {
            out = new ObjectOutputStream(bos);
            out.writeObject(event);
            out.flush();
            byte[] bytes = bos.toByteArray();
            Games.RealTimeMultiplayer.sendReliableMessage(client,null, bytes, ROOM_ID, otherPlayer.getParticipantId());
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

    public void onRealTimeMessageReceived(RealTimeMessage realTimeMessage) {
        isReceive =  true;
        Log.i("tag","received");
        byte[] msg  = realTimeMessage.getMessageData();
        ObjectInputStream in = null;
        try {

            in = new ObjectInputStream(new ByteArrayInputStream(msg));

            try{
                Object o = in.readObject();
                if (o instanceof List) {
                    Log.i("tag","adding Network Event");
                    List<Event> updatedInfo = (List<Event>) o;
                    info.addAll(updatedInfo);
                }else{
                    Log.e("tag","データがリストじゃない");
                }
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
                Log.e("tag", "不正なデータです ");
            }
        }catch (StreamCorruptedException e) {
            //Data was not Objects
            Log.i("tag","getMessage : "+new String(msg));
            if(new String(msg).equals("WIN")){
                isLose = true;
            }
        }catch (IOException e){
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

    public boolean isLose(){
        return isLose;
    }
    public boolean isReceiveNewData(){
        boolean result = isReceive;
        isReceive = false;
        return result;
    }
    public BlockingQueue<Event> getEvent(){
        return info;
    }

    public void sendMessage(String msg) {
        Log.i("tag","send message : "+ msg);
        Games.RealTimeMultiplayer.sendReliableMessage(client,null, msg.getBytes(),
                ROOM_ID, otherPlayer.getParticipantId());
    }
}
