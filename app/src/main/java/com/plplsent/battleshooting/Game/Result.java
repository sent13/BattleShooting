package com.plplsent.battleshooting.Game;

import java.io.Serializable;

/**
 * Created by sent13 on 16/12/13.
 */
public class Result implements Serializable{
    final private int score;
    final private Date date;

    Result(int score,Date date){
        this.score=score;
        this.date=date;
    }
}
