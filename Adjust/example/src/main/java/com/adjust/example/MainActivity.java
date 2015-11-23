package com.adjust.example;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.adjust.sdk.Adjust;
import com.adjust.sdk.AdjustEvent;

import java.util.Locale;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

public class MainActivity extends ActionBarActivity {
    private static final String EVENT_TOKEN_SIMPLE      = "{YourEventToken}";
    private static final String EVENT_TOKEN_REVENUE     = "{YourEventToken}";
    private static final String EVENT_TOKEN_CALLBACK    = "{YourEventToken}";
    private static final String EVENT_TOKEN_PARTNER     = "{YourEventToken}";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent intent = getIntent();
        Uri data = intent.getData();
        Adjust.appWillOpenUrl(data);

        // Adjust UI according to SDK state.
        Button btnEnableDisableSDK = (Button)findViewById(R.id.btnEnableDisableSDK);

        if (Adjust.isEnabled()) {
            btnEnableDisableSDK.setText(R.string.txt_disable_sdk);
        } else {
            btnEnableDisableSDK.setText(R.string.txt_enable_sdk);
        }

        String versionNo = BuildConfig.VERSION_NAME;
        Log.d("Adjust", String.format(Locale.US, "BuildConfig.VERSION_NAME %s", versionNo));

        Log.d("Adjust", String.format(Locale.US, "Build.VERSION.SDK_INT %d", Build.VERSION.SDK_INT));

        Log.d("Adjust", String.format(Locale.US, "Build.VERSION.RELEASE %s", Build.VERSION.RELEASE));

        Log.d("Adjust", String.format(Locale.US, "Build.VERSION.INCREMENTAL %s", Build.VERSION.INCREMENTAL));

        try {
            PackageInfo pInfo = getPackageManager().getPackageInfo(getPackageName(), 0);
            String version = pInfo.versionName;
            Log.d("Adjust", String.format(Locale.US, "pInfo.versionName %s", version));

            int verCode = pInfo.versionCode;
            Log.d("Adjust", String.format(Locale.US, "pInfo.versionCode %d", verCode));
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }

        ScheduledExecutorService scheduler;
        scheduler = Executors.newSingleThreadScheduledExecutor();

        final Context thisContext = this;
        /*
        scheduler.submit(new Runnable() {
            @Override
            public void run() {
                InstanceID instanceID = InstanceID.getInstance(thisContext);
                String token = null;
                try {
                    token = instanceID.getToken("593856252581", GoogleCloudMessaging.INSTANCE_ID_SCOPE);
                } catch (IOException e) {
                    Log.e("Adjust", "onCreate: " + e.getLocalizedMessage());
                    e.printStackTrace();
                }

                Log.i("Adjust", "GCM Registration Token: " + token);
            }
        });
        */
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void onTrackSimpleEventClick(View v) {
        AdjustEvent event = new AdjustEvent(EVENT_TOKEN_SIMPLE);

        Adjust.trackEvent(event);
    }

    public void onTrackRevenueEventClick(View v) {
        AdjustEvent event = new AdjustEvent(EVENT_TOKEN_REVENUE);

        // Add revenue 1 cent of an euro.
        event.setRevenue(0.01, "EUR");

        Adjust.trackEvent(event);
    }

    public void onTrackCallbackEventClick(View v) {
        AdjustEvent event = new AdjustEvent(EVENT_TOKEN_CALLBACK);

        // Add callback parameters to this parameter.
        event.addCallbackParameter("key", "value");

        Adjust.trackEvent(event);
    }

    public void onTrackPartnerEventClick(View v) {
        AdjustEvent event = new AdjustEvent(EVENT_TOKEN_PARTNER);

        // Add partner parameters to this parameter.
        event.addPartnerParameter("foo", "bar");

        Adjust.trackEvent(event);
    }

    public void onEnableDisableOfflineModeClick(View v) {
        if (((Button)v).getText().equals(
                getApplicationContext().getResources().getString(R.string.txt_enable_offline_mode))) {
            Adjust.setOfflineMode(true);
            ((Button)v).setText(R.string.txt_disable_offline_mode);
        } else {
            Adjust.setOfflineMode(false);
            ((Button)v).setText(R.string.txt_enable_offline_mode);
        }
    }

    public void onEnableDisableSDKClick(View v) {
        if (Adjust.isEnabled()) {
            Adjust.setEnabled(false);
            ((Button)v).setText(R.string.txt_enable_sdk);
        } else {
            Adjust.setEnabled(true);
            ((Button)v).setText(R.string.txt_disable_sdk);
        }
    }

    public void onIsSDKEnabledClick(View v) {
        if (Adjust.isEnabled()) {
            Toast.makeText(getApplicationContext(), R.string.txt_sdk_is_enabled,
                    Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(getApplicationContext(), R.string.txt_sdk_is_disabled,
                    Toast.LENGTH_SHORT).show();
        }
    }
}
