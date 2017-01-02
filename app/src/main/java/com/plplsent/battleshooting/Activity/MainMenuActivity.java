package com.plplsent.battleshooting.Activity;

import android.app.Activity;
import android.content.Intent;
import android.content.IntentSender;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.games.Games;
import com.google.android.gms.games.GamesActivityResultCodes;
import com.google.android.gms.games.GamesStatusCodes;
import com.google.android.gms.games.multiplayer.Participant;
import com.google.android.gms.games.multiplayer.realtime.Room;
import com.google.android.gms.games.multiplayer.realtime.RoomConfig;
import com.google.android.gms.games.multiplayer.realtime.RoomUpdateListener;
import com.plplsent.battleshooting.Game.GameAPI;
import com.plplsent.battleshooting.Game.MyGameAPI;
import com.plplsent.battleshooting.Network.MessageListener;
import com.plplsent.battleshooting.Network.MyNetwork;
import com.plplsent.battleshooting.R;

public class MainMenuActivity extends AppCompatActivity  {
    protected GoogleApiClient client;
    private final int WAITINGROOM_REQUESTCODE = 0;
    private final int GOOGLEAPI_CONNECTCODE=1;
    private Room room = null;
    private MessageListener messageListener;
    private RoomUpdateListener roomUpdateListener;
    private boolean isPlaying = false;
    private GameView gameView;
    private Long WAITING_TIME_LIMIT = 5000L;
    private Long waitingStartTime = 0L;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_menu);

        GoogleAPIConnectionListener connectionCallbacks = new GoogleAPIConnectionListener();

        client = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(connectionCallbacks)
                .enableAutoManage(this,connectionCallbacks)
                .addApi(Games.API).addScope(Games.SCOPE_GAMES)
                .build();

        componentInit();
        messageListener = new MessageListener();
    }

    private void startQuickMatch() {
        if(!client.isConnected()){
            client.connect();
        }
        roomUpdateListener = new MyRoomUpdateListener();
        Bundle autoMatchCriteria = RoomConfig.createAutoMatchCriteria(1,1, 0); //二人ﾌﾟﾚｲ
        RoomConfig.Builder rtmConfigBuilder = RoomConfig.builder(roomUpdateListener);
        rtmConfigBuilder.setAutoMatchCriteria(autoMatchCriteria);
        rtmConfigBuilder.setMessageReceivedListener(messageListener);
        Games.RealTimeMultiplayer.create(client, rtmConfigBuilder.build());
    }


    private void StartGame() {
        if (room == null || !messageListener.ready() || gameView == null) {
            throw new NullPointerException("roomがnullかメッセージリスナーがセットされいないかゲームビューがnull");
        }else {
            isPlaying = true;
            setContentView(gameView);
        }
    }

    private void prepareGame(Participant otherPlayer,String RoomID){
        MyNetwork network = new MyNetwork(client,otherPlayer,RoomID);
        GameAPI api = new MyGameAPI(network);
        gameView = new GameView(MainMenuActivity.this,api);
        messageListener.setNetWork(network);
    }
    private void exitGame(){
        Log.i("tag","exitGame");
        if(isPlaying) {
            if(client.isConnected()) Games.RealTimeMultiplayer.leave(client, roomUpdateListener, room.getRoomId());
            setContentView(R.layout.main_menu);
            messageListener.removeNetWork();
            roomUpdateListener =null;
            isPlaying = false;
            room = null;
            gameView = null;
            componentInit();
        }else{
            Log.i("tag","プレイ中でない状態でexitGameが呼ばれています");
        }
    }

    private void componentInit() {

        findViewById(R.id.quickmatchbutton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startQuickMatch();
            }
        });

        findViewById(R.id.show_result_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainMenuActivity.this, ShowResultActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i("tag","onstop");
        if(isPlaying) exitGame();
        client.disconnect();
    }

    @Override
    public void onBackPressed() {
        //super.onBackPressed();
        if(isPlaying) exitGame();
        else super.onBackPressed();
    }

    @Override
    protected void onActivityResult(int request, int response, Intent intent) {
        super.onActivityResult(request, response, intent);

        if (request == WAITINGROOM_REQUESTCODE) {
            if (response == Activity.RESULT_OK) {
                // (start game)
                StartGame();
            }
            else if (response == Activity.RESULT_CANCELED) {
                // Waiting room was dismissed with the back button. The meaning of this
                // action is up to the game. You may choose to leave the room and cancel the
                // match, or do something else like minimize the waiting room and
                // continue to connect in the background.

                // in this example, we take the simple approach and just leave the room:
                Games.RealTimeMultiplayer.leave(client, roomUpdateListener, room.getRoomId());
            }
            else if (response == GamesActivityResultCodes.RESULT_LEFT_ROOM) {
                // player wants to leave the room.
                Games.RealTimeMultiplayer.leave(client, roomUpdateListener, room.getRoomId());
            }

        }else if(request == GOOGLEAPI_CONNECTCODE){
            if (response == RESULT_OK) {
                client.connect();
            }else{
                throw new RuntimeException("Google APIに接続できませんでした。");
            }
        }
    }

    public void canPlay() {
        if(messageListener.getNetWork().isExistNewData()){
            waitingStartTime = System.currentTimeMillis();
        }else{
            Toast.makeText(this,"接続が切れています,,,,,",Toast.LENGTH_SHORT).show();
            if((System.currentTimeMillis()-waitingStartTime) >WAITING_TIME_LIMIT){
                Toast.makeText(this,"切断されました",Toast.LENGTH_LONG).show();
                exitGame();
            }
        }
    }

    public class GoogleAPIConnectionListener implements GoogleApiClient.ConnectionCallbacks , GoogleApiClient.OnConnectionFailedListener {


        @Override
        public void onConnected(@Nullable Bundle bundle) {

        }

        @Override
        public void onConnectionSuspended(int i) {
            Log.e("tag","connectionSuspend");
            if(isPlaying){
                exitGame();
            }
            client.reconnect();
        }

        @Override
        public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
            if(!connectionResult.hasResolution()){
                GooglePlayServicesUtil.getErrorDialog(connectionResult.getErrorCode(),MainMenuActivity.this,0).show();
                return;
            }

            try{
                connectionResult.startResolutionForResult(MainMenuActivity.this,GOOGLEAPI_CONNECTCODE);
            }catch(IntentSender.SendIntentException e){
                throw new RuntimeException("FatalError occurred while signIn to google play games",e);
            }

        }

    }
    public class MyRoomUpdateListener implements RoomUpdateListener {
        @Override
        public void onRoomCreated(int statusCode, Room room) {
            if (statusCode != GamesStatusCodes.STATUS_OK) {
                // display error
                return;
            }

            MainMenuActivity.this.room = room;

            // get waiting room intent
            Intent i = Games.RealTimeMultiplayer.getWaitingRoomIntent(client, room, Integer.MAX_VALUE);
            startActivityForResult(i, WAITINGROOM_REQUESTCODE);
        }

        @Override
        public void onJoinedRoom(int statusCode, Room room) {
            if (statusCode != GamesStatusCodes.STATUS_OK) {
                // display error
                return;
            }
            MainMenuActivity.this.room = room;

            // get waiting room intent
            Intent i = Games.RealTimeMultiplayer.getWaitingRoomIntent(client, room, Integer.MAX_VALUE);
            startActivityForResult(i, WAITINGROOM_REQUESTCODE);
        }

        @Override
        public void onLeftRoom(int i, String roomID) {
            Log.i("tag","onLeftRoom");
            if(isPlaying) exitGame();
        }

        @Override
        public void onRoomConnected(int i, Room room) {
            MainMenuActivity.this.room = room;
            Participant p = null;
            boolean flag = false;
            String myID = room.getParticipantId(Games.Players.getCurrentPlayerId(client));
            for (Participant participant : room.getParticipants()) {
                if (!participant.getParticipantId().equals(myID)) {
                    flag = true;
                    p = participant;
                }
            }
            if (!flag) {
                throw new IllegalStateException("the other Player has not benn found");
            }
            prepareGame(p,room.getRoomId());
        }
    }
/**
    public class MyRoomStatusUpdateListener implements RoomStatusUpdateListener {
        boolean mPlaying = false;

        // at least 2 players required for our game
        final static int MIN_PLAYERS = 2;

        // returns whether there are enough players to start the game
        boolean shouldStartGame(Room room) {
            int connectedPlayers = 0;
            for (Participant p : room.getParticipants()) {
                if (p.isConnectedToRoom()) ++connectedPlayers;
            }
            return connectedPlayers >= MIN_PLAYERS;
        }

        // Returns whether the room is in a state where the game should be canceled.
        boolean shouldCancelGame(Room room) {
            // TODO: Your game-specific cancellation logic here. For example, you might decide to
            // cancel the game if enough people have declined the invitation or left the room.
            // You can check a participant's status with Participant.getStatus().
            // (Also, your UI should have a Cancel button that cancels the game too)
        }

        @Override
        public void onPeersConnected(Room room, List<String> peers) {
            if (mPlaying) {
                // add new player to an ongoing game
            } else if (shouldStartGame(room)) {
                // start game!
            }
        }

        @Override
        public void onPeersDisconnected(Room room, List<String> peers) {
            if (mPlaying) {
                // do game-specific handling of this -- remove player's avatar
                // from the screen, etc. If not enough players are left for
                // the game to go on, end the game and leave the room.
            } else if (shouldCancelGame(room)) {
                // cancel the game
                Games.RealTimeMultiplayer.leave(client, roomUpdateListener, mRoomId);
            }
        }

        @Override
        public void onPeerDeclined(Room room, List<String> peers) {
            // peer declined invitation -- see if game should be canceled
            if (!mPlaying && shouldCancelGame(room)) {
                Games.RealTimeMultiplayer.leave(client, roomUpdateListener, mRoomId);
            }
        }

        @Override
        public void onPeerLeft(Room room, List<String> peers) {
            // peer left -- see if game should be canceled
            if (!mPlaying && shouldCancelGame(room)) {
                Games.RealTimeMultiplayer.leave(client, roomUpdateListener, mRoomId);
            }
        }

        @Override
        public void onP2PConnected(String s) {

        }

        @Override
        public void onP2PDisconnected(String s) {

        }

        @Override
        public void onConnectedToRoom(Room room) {

        }

        @Override
        public void onDisconnectedFromRoom(Room room) {

        }

        @Override
        public void onRoomConnecting(Room room) {

        }

        @Override
        public void onRoomAutoMatching(Room room) {

        }

        @Override
        public void onPeerInvitedToRoom(Room room, List<String> list) {

        }


        @Override
        public void onPeerJoined(Room room, List<String> list) {

        }
    }
**/
}
