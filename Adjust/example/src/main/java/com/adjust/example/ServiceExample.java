package com.adjust.example;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

/**
 * Created by pfms on 16/03/16.
 */
public class ServiceExample extends Service {

    public ServiceExample() {
        super();

        Log.d("example", "ServiceExample constructor");
    }

    @Override
    public IBinder onBind(Intent intent) {
        Log.d("example", "ServiceExample onBind");

        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d("example", "ServiceExample onCreate");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        int startDefaultOption = super.onStartCommand(intent, flags, startId);
        Log.d("example", "ServiceExample onCreate");

        return startDefaultOption;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d("example", "ServiceExample onDestroy");
    }

}
