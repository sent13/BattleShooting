package com.plplsent.battleshooting.Game;

import java.io.Serializable;

/**
 * Created by sent13 on 16/12/13.
 */
public class Date implements Serializable{
    int month;
    int day;
    int hour;
    int minute;

    public Date(int month,int day,int hour,int minute){
        this.month=month;
        this.day=day;
        this.hour=hour;
        this.minute=minute;
    }
}
