package com.adjust.example;

import android.os.Bundle;
import android.util.Log;

import com.google.android.gms.gcm.GcmListenerService;

/**
 * Created by pfms on 23/11/15.
 */
public class ExampleGcmListenerService extends GcmListenerService {

    @Override
    public void onMessageReceived(String from, Bundle data) {

        for (String key : data.keySet()) {
            Object value = data.get(key);
            Log.d("Adjust", String.format("%s %s (%s)", key,
                    value.toString(), value.getClass().getName()));
        }

        String message = data.getString("message");
        Log.d("Adjust", "From: " + from);
        Log.d("Adjust", "Message: " + message);

        if (from.startsWith("/topics/")) {
            // message received from some topic.
        } else {
            // normal downstream message.
        }

    }
}
