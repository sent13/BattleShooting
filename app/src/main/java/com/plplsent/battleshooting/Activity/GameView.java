package com.plplsent.battleshooting.Activity;

import android.app.Activity;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import com.plplsent.battleshooting.Game.GameAPI;


public class GameView extends SurfaceView implements SurfaceHolder.Callback, Runnable {
    private final GameAPI gameAPI;
    private SurfaceHolder holder;
    private Thread thread;

    public GameView(Activity context, GameAPI gameAPI) {
        super(context);
        this.gameAPI = gameAPI;
        getHolder().addCallback(this);
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        this.holder = holder;
        thread = new Thread(this);
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
        this.holder = holder;
        if (thread != null) {
            thread.start();
        }
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        this.holder = holder;
        thread = null;
    }

    @Override
    public void run() {
        Canvas canvas = holder.lockCanvas();
        Paint p = new Paint();
        p.setColor(Color.GREEN);
        canvas.drawRect(0, 0, 500, 500, p);
        holder.unlockCanvasAndPost(canvas);
        while (thread != null) {
            Log.i("tag", "run");
            gameAPI.update();

        }

        try {
            Thread.sleep(10000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void end() {
        thread = null;
    }
}
