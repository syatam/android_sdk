package com.adjust.example;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.adjust.sdk.Adjust;
import com.adjust.sdk.AdjustAttribution;
import com.adjust.sdk.AdjustConfig;
import com.adjust.sdk.AdjustEvent;
import com.adjust.sdk.LogLevel;
import com.adjust.sdk.OnAttributionChangedListener;

public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent intent = getIntent();
        Uri data = intent.getData();
        Adjust.appWillOpenUrl(data);
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

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onResume() {
        super.onResume();
        Adjust.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        Adjust.onPause();
    }

    public void onTrackSimpleEventClick(View v) {
        AdjustEvent event = new AdjustEvent("uqg17r");

        Adjust.trackEvent(event);
    }

    public void onTrackRevenueEventClick(View v) {
        AdjustEvent event = new AdjustEvent("71iltz");

        // add revenue 1 cent of an euro
        event.setRevenue(0.01, "EUR");

        Adjust.trackEvent(event);
    }

    public void onTrackEventWithCallbackClick(View v) {
        AdjustEvent event = new AdjustEvent("1ziip1");

        // add callback parameters to this parameter
        event.addCallbackParameter("key", "value");

        Adjust.trackEvent(event);
    }

    public void onTrackEventWithPartnerClick(View v) {
        AdjustEvent event = new AdjustEvent("9s4lqn");

        // add partner parameters to this parameter
        event.addPartnerParameter("foo", "bar");

        Adjust.trackEvent(event);
    }

    public void onTeardownClick(View v) {
        Adjust.teardown();
    }

    public void onReinitializeClick(View v) {
        String appToken = "rb4g27fje5ej";
        String environment = AdjustConfig.ENVIRONMENT_SANDBOX;
        AdjustConfig config = new AdjustConfig(this, appToken, environment);

        // change the log level
        config.setLogLevel(LogLevel.VERBOSE);

        // set attribution delegate
        config.setOnAttributionChangedListener(new OnAttributionChangedListener() {
            @Override
            public void onAttributionChanged(AdjustAttribution attribution) {
                Log.d("example", "attribution: " + attribution.toString());
            }
        });

        Adjust.onCreate(config);
    }
}
