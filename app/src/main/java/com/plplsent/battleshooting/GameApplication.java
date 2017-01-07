package com.plplsent.battleshooting;

import android.app.Activity;
import android.app.Application;
import android.content.IntentSender;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.games.Games;
import com.plplsent.battleshooting.Activity.MainMenuActivity;
import com.plplsent.battleshooting.Utils.ObjectStream;
import com.plplsent.battleshooting.Utils.Result;

import java.io.File;
import java.util.Collections;
import java.util.List;

public class GameApplication extends Application{

    static final String FILE_NAME="resultData.obj";
    ObjectStream<Result> objectStream;
    private GoogleApiClient mGoogleApiClient;
    private Activity currentActivity;
    public static final int GOOGLEAPI_CONNECTCODE = 0x111;
    @Override
    public void onCreate(){
        objectStream=new ObjectStream<>(new File(getFilesDir(),FILE_NAME));
        GoogleAPIConnectionListener connectionListener = new GoogleAPIConnectionListener();
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(connectionListener)
                .addApi(Games.API).addScope(Games.SCOPE_GAMES)
                .build();
        /**
        addResult(new Result(100,new Date(12,29,15,43)));
        addResult(new Result(140,new Date(12,29,15,20)));
        addResult(new Result(110,new Date(12,29,15,50)));**/
    }

    public void connectGoogleAPI(){
        mGoogleApiClient.connect();
    }


    public void disConnectGoogleAPI(){
        mGoogleApiClient.disconnect();
    }

    public void addResult(Result result){
        objectStream.save(result);
    }

    public List<Result> getResultList() {
        List<Result> results=objectStream.read();
        Collections.sort(results);
        return Collections.unmodifiableList(results);
    }

    public GoogleApiClient getGoogleApiClient(){
        if(mGoogleApiClient==null) throw new NullPointerException("apiClientが設定されていません");
        return mGoogleApiClient;
    }
    public void setGoogleApiClient(GoogleApiClient client){
        mGoogleApiClient = client;
    }

    public void setCurrentActivity(Activity activity){
        currentActivity = activity;
    }
    public Activity getCurrentActivity(){
        return currentActivity;
    }

    public class GoogleAPIConnectionListener implements GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener {


        @Override
        public void onConnected(@Nullable Bundle bundle) {

        }

        @Override
        public void onConnectionSuspended(int i) {
            Log.e("tag", "connectionSuspend");
            mGoogleApiClient.reconnect();
        }

        @Override
        public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
            if (!connectionResult.hasResolution()) {
                GooglePlayServicesUtil.getErrorDialog(connectionResult.getErrorCode(), currentActivity, 0).show();
                return;
            }

            try {
                connectionResult.startResolutionForResult(currentActivity, GOOGLEAPI_CONNECTCODE);
            } catch (IntentSender.SendIntentException e) {
                throw new RuntimeException("FatalError occurred while signIn to google play games", e);
            }

        }

    }

}
