package com.plplsent.battleshooting;

import android.app.Application;

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

        /* DataUtilからの読み込みを書く */
    }

    public List<Result> getResultList() {
        return Collections.unmodifiableList(resultList);
    }

}
