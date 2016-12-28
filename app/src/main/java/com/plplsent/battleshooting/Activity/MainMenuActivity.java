package com.plplsent.battleshooting.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.games.Games;
import com.google.android.gms.games.multiplayer.Participant;
import com.google.android.gms.games.multiplayer.realtime.Room;
import com.google.android.gms.games.multiplayer.realtime.RoomConfig;
import com.google.android.gms.games.multiplayer.realtime.RoomUpdateListener;
import com.plplsent.battleshooting.Game.GameAPI;
import com.plplsent.battleshooting.Game.MyGameAPI;
import com.plplsent.battleshooting.Network.MyNetwork;
import com.plplsent.battleshooting.R;

public class MainMenuActivity extends AppCompatActivity implements GoogleApiClient.ConnectionCallbacks ,GoogleApiClient.OnConnectionFailedListener, RoomUpdateListener {
    private GoogleApiClient client;
    private final int WAITINGROOM_REQUESTCODE = 0;
    private GameView gameView;
    private MyNetwork network;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_menu);

        client = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .enableAutoManage(this,this)
                .addApi(Games.API).addScope(Games.SCOPE_GAMES)
                .addOnConnectionFailedListener(this)
                .build();

        findViewById(R.id.quickmatchbutton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startQuickMatch();
            }
        });
    }

    private void startQuickMatch() {
        Bundle autoMatchCriteria = RoomConfig.createAutoMatchCriteria(1,1, 0); //二人ﾌﾟﾚｲ
        RoomConfig.Builder rtmConfigBuilder = RoomConfig.builder(this);
        rtmConfigBuilder.setAutoMatchCriteria(autoMatchCriteria);
        rtmConfigBuilder.setMessageReceivedListener(network);
        Games.RealTimeMultiplayer.create(client, rtmConfigBuilder.build());
    }


    private void StartGame() {
        setContentView(gameView);
    }

    private void exitGame(){
        setContentView(R.layout.main_menu);
        network=null;
        gameView=null;
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode== WAITINGROOM_REQUESTCODE){
            StartGame();
        }
    }


    @Override
    public void onConnected(@Nullable Bundle bundle) {
        Toast.makeText(this,"GoogleAPI Connected",Toast.LENGTH_LONG).show();
    }

    @Override
    public void onConnectionSuspended(int i) {
        if (gameView == null) {
            exitGame();
        }
        Toast.makeText(this,"GoogleAPIConnection Suspended",Toast.LENGTH_LONG).show();
        client.reconnect();
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        throw new IllegalStateException(connectionResult.toString());
    }

    @Override
    public void onRoomCreated(int i, Room room) {

        Intent in = Games.RealTimeMultiplayer.getWaitingRoomIntent(client, room, Integer.MAX_VALUE);
        startActivityForResult(in, WAITINGROOM_REQUESTCODE); //ShowWaitingRoom
    }

    @Override
    public void onJoinedRoom(int i, Room room) {

    }

    @Override
    public void onLeftRoom(int i, String s) {
        exitGame();
    }

    @Override
    public void onRoomConnected(int i, Room room) {
        Participant p=null;
        boolean flag = false;
        String myID = room.getParticipantId(Games.Players.getCurrentPlayerId(client));
        for (Participant participant : room.getParticipants()) {
            if(!participant.getParticipantId().equals(myID)){
                flag= true;
                p=participant;
            }
        }
        if(flag){
            throw new IllegalStateException("the other Player has not benn found");
        }
        GameAPI api = new MyGameAPI();
        network = new MyNetwork(api,client,room.getRoomId(),p);
        gameView = new GameView(this,api);
    }

}
