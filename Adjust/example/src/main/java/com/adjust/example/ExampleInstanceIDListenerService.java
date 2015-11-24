package com.adjust.example;

import android.util.Log;

import com.google.android.gms.gcm.GoogleCloudMessaging;
import com.google.android.gms.iid.InstanceID;
import com.google.android.gms.iid.InstanceIDListenerService;

import java.io.IOException;

/**
 * Created by pfms on 23/11/15.
 */
public class ExampleInstanceIDListenerService extends InstanceIDListenerService {
    public void onTokenRefresh() {
        InstanceID instanceID = InstanceID.getInstance(this);
        String token = null;
        try {
            token = instanceID.getToken("example-51a0f", GoogleCloudMessaging.INSTANCE_ID_SCOPE);
        } catch (IOException e) {
            Log.e("Adjust", "onTokenRefresh: " + e.getLocalizedMessage());
            e.printStackTrace();
        }

        Log.i("Adjust", "GCM Registration Token: " + token);
    }

    private void refreshAllTokens() {
        InstanceID iid = InstanceID.getInstance(this);
    }

}
