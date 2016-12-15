package com.plplsent.battleshooting.Activity;

import android.content.Context;
import android.view.SurfaceView;

import com.plplsent.battleshooting.Game.GameAPI;
import com.plplsent.battleshooting.Game.MyGameAPI;
import com.plplsent.battleshooting.Network.MyNetwork;


public class GameView extends SurfaceView {
    private final GameAPI gameAPI;

    public GameView(Context context, GameAPI gameAPI) {
        super(context);
        this.gameAPI = gameAPI;

    }
}
