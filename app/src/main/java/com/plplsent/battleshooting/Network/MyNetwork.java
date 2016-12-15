package com.plplsent.battleshooting.Network;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.games.Games;
import com.google.android.gms.games.multiplayer.Participant;
import com.google.android.gms.games.multiplayer.realtime.RealTimeMessage;
import com.google.android.gms.games.multiplayer.realtime.RealTimeMessageReceivedListener;
import com.plplsent.battleshooting.Game.Ballet;
import com.plplsent.battleshooting.Game.BalletInfo;
import com.plplsent.battleshooting.Game.DPoint;
import com.plplsent.battleshooting.Game.PlayerInfo;
import com.plplsent.battleshooting.Game.UpdateInfo;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MyNetwork implements RealTimeMessageReceivedListener {
    private final GoogleApiClient client;
    private final String roomID;
    private final Participant participant;
    private List<UpdateInfo> info;

    public MyNetwork(GoogleApiClient client, String roomID, Participant p) {
        this.client = client;
        this.roomID = roomID;
        participant = p;
    }

    public void send(DPoint playerPos, List<Ballet> ballets) {
        List<UpdateInfo> info = new ArrayList<>();
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

    @Override
    public void onRealTimeMessageReceived(RealTimeMessage realTimeMessage) {
        ByteArrayInputStream bis = new ByteArrayInputStream(realTimeMessage.getMessageData());
        ObjectInput in = null;
        DataT
        try {
            in = new ObjectInputStream(bis);
            Object o = in.readObject();
            if (o instanceof List) {
                List<UpdateInfo> updatedInfo = (List<UpdateInfo>) o;
                info = Collections.synchronizedList(updatedInfo);
            }
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

    List<UpdateInfo> getEvents() {
        return info;
    }
}
