package com.plplsent.battleshooting.Utils;

import java.io.Serializable;

/**
 * Created by sent13 on 16/12/13.
 */
public class Date implements Serializable{
    final private int month;
    final private int day;
    final private int hour;
    final private int minute;

    public Date(int month,int day,int hour,int minute){
        this.month=month;
        this.day=day;
        this.hour=hour;
        this.minute=minute;
    }
}
