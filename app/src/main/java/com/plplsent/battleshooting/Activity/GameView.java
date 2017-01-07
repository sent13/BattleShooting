package com.plplsent.battleshooting.Activity;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PointF;
import android.graphics.Rect;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;

import com.plplsent.battleshooting.Game.Entity.Entity;
import com.plplsent.battleshooting.Game.Entity.TeamGroup.TeamGroup;
import com.plplsent.battleshooting.Game.Field.Field;
import com.plplsent.battleshooting.Game.GameAPI;
import com.plplsent.battleshooting.R;


public class GameView extends SurfaceView implements SurfaceHolder.Callback, Runnable {
    private final GameAPI gameAPI;
    private SurfaceHolder holder;
    private Thread thread;
    private float scale;
    private Bitmap playerBitMap = BitmapFactory.decodeResource(getResources(), R.drawable.player);
    private final Rect PLAYER_BITMAP_RECT = new Rect(0, 0, playerBitMap.getWidth(), playerBitMap.getHeight());
    private Bitmap EplayerBitMap = BitmapFactory.decodeResource(getResources(), R.drawable.eplayer);

    private Bitmap bulletBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.ballet);
    private final Rect BULLET_BITMAP_RECT = new Rect(0, 0, bulletBitmap.getWidth(), bulletBitmap.getHeight());
    private Bitmap EbulletBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.eballet);

    private float space_x;
    public GameView(Activity context, GameAPI gameAPI) {
        super(context);
        this.gameAPI = gameAPI;
        getHolder().addCallback(this);
        setOnTouchListener(new MyOnTouchListener());
    }


    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        this.holder = holder;
        scale = ((float) (getHeight() / Field.FIELD_SIZE.getY()));
        space_x = Math.abs((getWidth()/scale- ((float) Field.FIELD_SIZE.getX())))/2*scale;

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

        while (thread != null) {
            Canvas canvas = holder.lockCanvas();
            Paint back = new Paint();
            back.setColor(Color.BLACK);
            canvas.drawRect(0, 0, getWidth(), getHeight(), back);
            canvas.translate(space_x,0);
            canvas.scale(scale, scale);

                    back.setColor(Color.GREEN);
            canvas.drawRect(0, 0, ((float) Field.FIELD_SIZE.getX()), ((float) Field.FIELD_SIZE.getY()), back);
            canvas.drawBitmap(playerBitMap, PLAYER_BITMAP_RECT, gameAPI.getPlayerRectF(), new Paint());
            canvas.drawBitmap(EplayerBitMap, PLAYER_BITMAP_RECT, gameAPI.getEnemyRectF(), new Paint());

            for (Entity e : gameAPI.getBullets(TeamGroup.Team.ME).getBullets()) {
                canvas.drawBitmap(bulletBitmap, BULLET_BITMAP_RECT, e.getRectF(), new Paint());
            }

            for (Entity e : gameAPI.getBullets(TeamGroup.Team.ENEMY).getBullets()) {
                canvas.drawBitmap(EbulletBitmap, BULLET_BITMAP_RECT, e.getRectF(), new Paint());
            }

            holder.unlockCanvasAndPost(canvas);

            gameAPI.update();
          /*  try {
                Thread.sleep(100L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }*/
        }
    }

    public void end() {
        thread = null;
    }

    private class MyOnTouchListener implements OnTouchListener {

        private boolean isDraggingPlayer = false;
        PointF oldPoint=new PointF();
        @Override
        public boolean onTouch(View v, MotionEvent event) {
            float touchedX = event.getX() / scale;
            float touchedY = event.getY() / scale;
            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    if (gameAPI.isTouchingPlayer(touchedX, touchedY)) {
                        oldPoint.set(touchedX,touchedY);
                        isDraggingPlayer = true;
                        return true;
                    } else {
                        gameAPI.fireBallet();
                        return true;
                    }
                case MotionEvent.ACTION_UP:
                    if (isDraggingPlayer) isDraggingPlayer = false;
                    return true;
                case MotionEvent.ACTION_MOVE:
                    if (isDraggingPlayer) {

                        gameAPI.movePlayer(touchedX-oldPoint.x, touchedY-oldPoint.y);
                        oldPoint.set(touchedX,touchedY);
                        return true;
                    }
            }
            return false;
        }


    }
}
