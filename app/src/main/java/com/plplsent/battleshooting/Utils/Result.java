package com.plplsent.battleshooting.Utils;

import com.plplsent.battleshooting.Utils.Date;

import java.io.Serializable;

/**
 * Created by sent13 on 16/12/13.
 */
public class Result implements Serializable,Comparable<Result>{
    final private int score;
    final private Date date;

    public Result(int score,Date date){
        this.score=score;
        this.date=date;
    }

    @Override
    public String toString(){
        return ""+score+"\t\t"+date.toString();
    }

    @Override
    public int compareTo(Result another) {
        return another.score-score;
    }
}
