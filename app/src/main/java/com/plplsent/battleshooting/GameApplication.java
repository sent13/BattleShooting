package com.plplsent.battleshooting;

import android.app.Application;
import android.widget.ArrayAdapter;

import com.plplsent.battleshooting.Utils.Date;
import com.plplsent.battleshooting.Utils.ObjectStream;
import com.plplsent.battleshooting.Utils.Result;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by sent13 on 2016/12/14.
 */

public class GameApplication extends Application{

    static final String FILE_NAME="resultData.obj";
    ObjectStream<Result> objectStream;

    @Override
    public void onCreate(){
        objectStream=new ObjectStream<>(new File(getFilesDir(),FILE_NAME));


        /* 最初の一回だけ確認のために有効にしてくれ
        addResult(new Result(100,new Date(12,29,15,43)));
        addResult(new Result(140,new Date(12,29,15,20)));
        addResult(new Result(110,new Date(12,29,15,50))); */
    }

    public void addResult(Result result){
        objectStream.save(result);
    }

    public List<Result> getResultList() {
        List<Result> results=objectStream.read();
        Collections.sort(results);
        return Collections.unmodifiableList(results);
    }

}
