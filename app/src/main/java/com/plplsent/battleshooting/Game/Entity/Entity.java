package com.plplsent.battleshooting.Game.Entity;


import android.graphics.RectF;

import com.plplsent.battleshooting.Game.DPoint;

public abstract class Entity {
    private final DPoint SIZE;
    private DPoint position;
    private RectF rectF;
    public Entity(DPoint position,DPoint size) {
        this.position = position;
        SIZE = size;
        rectF=new RectF();
        setRect();
    }

    public DPoint getPosition(){
        return position;
    }
    public DPoint getSize(){
        return this.SIZE;
    }

    public RectF getRect(){
        return rectF;
    }
    public void move(DPoint deltaPosition){
        position = position.add(deltaPosition);
        setRect();
    }

    private void setRect() {
        rectF.set((float)( position.getX()-getSize().getX()/2),(float)(position.getY()-getSize().getY()/2),
                (float)(position.getX()+getSize().getX()/2),(float)(position.getY()+getSize().getY()/2));
    }

    public abstract void update();

    public void setPos(DPoint pos) {
        this.position = pos;
        setRect();
    }

    public boolean intersects(Entity e){
        return this.rectF.intersect(e.getRect());
    }
}
