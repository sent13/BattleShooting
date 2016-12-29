package com.plplsent.battleshooting;

import android.app.Application;

import com.plplsent.battleshooting.Utils.Date;
import com.plplsent.battleshooting.Utils.Result;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by sent13 on 2016/12/14.
 */

public class GameApplication extends Application{

    private List<Result> resultList;

    @Override
    public void onCreate(){
        resultList=new ArrayList<>();
        addResult(new Result(100,new Date(12,29,15,43)));
        addResult(new Result(130,new Date(12,29,15,20)));
        addResult(new Result(110,new Date(12,29,15,50)));

        /* DataUtilからの読み込みを書く */
    }

    public void addResult(Result result){
        resultList.add(result);
        Collections.sort(resultList);
    }

    public List<Result> getResultList() {
        return Collections.unmodifiableList(resultList);
    }

}
