package com.plplsent.battleshooting.Activity;

import android.os.Bundle;

import com.google.android.gms.games.Games;
import com.google.android.gms.games.multiplayer.Participant;
import com.google.android.gms.games.multiplayer.realtime.Room;
import com.plplsent.battleshooting.Game.MyGameAPI;
import com.plplsent.battleshooting.GameApplication;
import com.plplsent.battleshooting.Network.MessageListener;
import com.plplsent.battleshooting.Network.MyNetwork;

public class GameActivity extends MyBase2Activity {

    public static final String ROOM_KEY = "room";
    public Room room;
    MyNetwork network;
    GameView gameView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        room = getIntent().getParcelableExtra(ROOM_KEY);

        Participant otherPlayer = getOtherPlayer(room);
        network = new MyNetwork(((GameApplication) getApplication()).getGoogleApiClient(),
                otherPlayer,room.getRoomId());
        MessageListener.getInstance().setNetWork(network);
        gameView =new GameView(this,new MyGameAPI(network));
        setContentView(gameView);
    }






    private Participant getOtherPlayer(Room room) {

        Participant p = null;
        boolean flag = false;
        String myID = room.getParticipantId(Games.Players.getCurrentPlayerId(((GameApplication) getApplication()).getGoogleApiClient()));
        for (Participant participant : room.getParticipants()) {
            if (!participant.getParticipantId().equals(myID)) {
                flag = true;
                p = participant;
            }
        }
        if (!flag) {
            throw new IllegalStateException("the other Player has not benn found");
        }
        return p;
    }

    private void exit(){
        gameView.end();
        MessageListener.getInstance().removeNetWork();
    }

    @Override
    public void onBackPressed() {
        exit();
        finish();
    }
}
