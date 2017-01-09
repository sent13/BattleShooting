package com.plplsent.battleshooting.Activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.test.mock.MockApplication;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.games.Games;
import com.google.android.gms.games.GamesActivityResultCodes;
import com.google.android.gms.games.GamesStatusCodes;
import com.google.android.gms.games.multiplayer.realtime.Room;
import com.google.android.gms.games.multiplayer.realtime.RoomConfig;
import com.google.android.gms.games.multiplayer.realtime.RoomUpdateListener;
import com.plplsent.battleshooting.GameApplication;
import com.plplsent.battleshooting.Network.MessageListener;
import com.plplsent.battleshooting.R;

public class MainMenuActivity extends MyBaseActivity {
    private final int WAITINGROOM_REQUESTCODE = 1;
    private final int GAME_START = 2;
    private Room room = null;
    private RoomUpdateListener roomUpdateListener;

    private Intent gameIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_menu);

        ((GameApplication) getApplication()).connectGoogleAPI();
        componentInit();
    }

    @Override
    protected void onDestroy() {
        ((GameApplication)getApplication()).disConnectGoogleAPI();
        super.onDestroy();
    }

    private void startQuickMatch() {
        if (!((GameApplication) getApplication()).getGoogleApiClient().isConnected()) {
            ((GameApplication) getApplication()).getGoogleApiClient().connect();
        }
        roomUpdateListener = new MyRoomUpdateListener();
        Bundle autoMatchCriteria = RoomConfig.createAutoMatchCriteria(1, 1, 0); //二人ﾌﾟﾚｲ
        RoomConfig.Builder rtmConfigBuilder = RoomConfig.builder(roomUpdateListener);
        rtmConfigBuilder.setAutoMatchCriteria(autoMatchCriteria);
        rtmConfigBuilder.setMessageReceivedListener(MessageListener.getInstance());
        Games.RealTimeMultiplayer.create(((GameApplication) getApplication()).getGoogleApiClient(), rtmConfigBuilder.build());
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
    protected void onActivityResult(int request, int response, Intent intent) {
        super.onActivityResult(request, response, intent);
        if (request == WAITINGROOM_REQUESTCODE) {
            if (response == Activity.RESULT_OK) {
                // (start game)
                if (gameIntent.hasExtra(GameActivity.ROOM_KEY)) {
                    startActivityForResult(gameIntent,GAME_START);
                }
            } else if (response == Activity.RESULT_CANCELED) {
                // Waiting room was dismissed with the back button. The meaning of this
                // action is up to the game. You may choose to leave the room and cancel the
                // match, or do something else like minimize the waiting room and
                // continue to connect in the background.

                // in this example, we take the simple approach and just leave the room:
                Games.RealTimeMultiplayer.leave(((GameApplication) getApplication()).getGoogleApiClient(), roomUpdateListener, room.getRoomId());
            } else if (response == GamesActivityResultCodes.RESULT_LEFT_ROOM) {
                // player wants to leave the room.
                Games.RealTimeMultiplayer.leave(((GameApplication) getApplication()).getGoogleApiClient(), roomUpdateListener, room.getRoomId());
            }

        }else if(request == GAME_START){
            Log.i("tag","Game has finished");
            Games.RealTimeMultiplayer.leave(((GameApplication) getApplication()).getGoogleApiClient(), roomUpdateListener, room.getRoomId());
        } else if (request == GameApplication.GOOGLEAPI_CONNECTCODE ) {
            if (response == RESULT_OK) {
                ((GameApplication) getApplication()).getGoogleApiClient().connect();
            } else {
                throw new RuntimeException("Google APIに接続できませんでした。");
            }
        }
    }

    /*
        public void canPlay() {
            waitingStartTime = waitingStartTime==0?System.currentTimeMillis():waitingStartTime;
            if (messageListener.getNetWork().isReceiveNewData()) {
                waitingStartTime = System.currentTimeMillis();
            } else {
                displayToast("接続が切れています......");
                if ((System.currentTimeMillis() - waitingStartTime) > WAITING_TIME_LIMIT) {
                    displayToast("切断されました");
                    Handler h = new Handler(getApplication().getMainLooper());
                    gameView.end();
                    h.post(new Runnable() {
                        @Override
                        public void run() {
                            MainMenuActivity.this.exitGame();
                        }
                    });
                }
            }
        }*/
    private void displayToast(final String msg) {
        Handler h = new Handler(getApplication().getMainLooper());
        h.post(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(MainMenuActivity.this, msg, Toast.LENGTH_SHORT).show();
            }
        });
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
            Intent i = Games.RealTimeMultiplayer.getWaitingRoomIntent(((GameApplication) getApplication()).getGoogleApiClient(), room, Integer.MAX_VALUE);
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
            Intent i = Games.RealTimeMultiplayer.getWaitingRoomIntent(((GameApplication) getApplication()).getGoogleApiClient(), room, Integer.MAX_VALUE);
            startActivityForResult(i, WAITINGROOM_REQUESTCODE);
        }

        @Override
        public void onLeftRoom(int i, String roomID) {
            Log.i("tag", "onLeftRoom");
        }

        @Override
        public void onRoomConnected(int i, Room room) {
            MainMenuActivity.this.room = room;
            gameIntent = new Intent(MainMenuActivity.this, GameActivity.class);
            gameIntent.putExtra(GameActivity.ROOM_KEY, room);
        }
    }
}


            /**
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

