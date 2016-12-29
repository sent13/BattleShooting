package com.plplsent.battleshooting.Activity;

import android.app.Application;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.games.Game;
import com.google.android.gms.games.Games;
import com.plplsent.battleshooting.GameApplication;
import com.plplsent.battleshooting.R;
import com.plplsent.battleshooting.Utils.Result;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sent13 on 2016/12/29.
 */

public class ShowResultActivity extends AppCompatActivity{
    GameApplication application;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.show_result);
        application=(GameApplication) this.getApplication();
        ListView listView=(ListView)findViewById(R.id.result_list);

        ArrayAdapter adapter=new ArrayAdapter(this,android.R.layout.simple_list_item_1, application.getResultList());
        listView.setAdapter(adapter);

        Button returnBtn=(Button)findViewById(R.id.return_btn);

        returnBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }


}
