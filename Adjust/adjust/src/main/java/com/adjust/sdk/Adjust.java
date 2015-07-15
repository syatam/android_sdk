//
//  Adjust.java
//  Adjust
//
//  Created by Christian Wellenbrock on 2012-10-11.
//  Copyright (c) 2012-2014 adjust GmbH. All rights reserved.
//  See the file MIT-LICENSE for copying permission.
//

package com.adjust.sdk;

import android.net.Uri;

/**
 * The main interface to Adjust.
 * Use the methods of this class to tell Adjust about the usage of your app.
 * See the README for details.
 */
public class Adjust {

    private static AdjustInstance defaultInstance;

    private Adjust() {
    }

    public static synchronized void getDefaultInstance() {
        if (defaultInstance == null) {
            defaultInstance = new AdjustInstance();
        }
    }

    public static synchronized void onCreate(AdjustConfig adjustConfig) {
        Adjust.getDefaultInstance();

        if (defaultInstance != null) {
            defaultInstance.onCreate(adjustConfig);
        } else {
            AdjustFactory.getLogger().error("Adjust not initialized correctly");
        }
    }

    public static synchronized void trackEvent(AdjustEvent event) {
        if (defaultInstance != null) {
            defaultInstance.trackEvent(event);
        } else {
            AdjustFactory.getLogger().error("Adjust not initialized correctly");
        }
    }

    public static synchronized void onResume() {
        if (defaultInstance != null) {
            defaultInstance.onResume();
        } else {
            AdjustFactory.getLogger().error("Adjust not initialized correctly");
        }
    }

    public static synchronized void onPause() {
        if (defaultInstance != null) {
            defaultInstance.onPause();
        } else {
            AdjustFactory.getLogger().error("Adjust not initialized correctly");
        }
    }

    public static synchronized void setEnabled(boolean enabled) {
        if (defaultInstance != null) {
            defaultInstance.setEnabled(enabled);
        } else {
            AdjustFactory.getLogger().error("Adjust not initialized correctly");
        }
    }

    public static synchronized boolean isEnabled() {
        if (defaultInstance != null) {
            return defaultInstance.isEnabled();
        } else {
            AdjustFactory.getLogger().error("Adjust not initialized correctly");
            return false;
        }
    }

    public static synchronized void appWillOpenUrl(Uri url) {
        if (defaultInstance != null) {
            defaultInstance.appWillOpenUrl(url);
        } else {
            AdjustFactory.getLogger().error("Adjust not initialized correctly");
        }
    }

    public static synchronized void sendReferrer(String referrer) {
        if (defaultInstance != null) {
            defaultInstance.sendReferrer(referrer);
        } else {
            AdjustFactory.getLogger().error("Adjust not initialized correctly");
        }
    }

    public static synchronized void setOfflineMode(boolean enabled) {
        if (defaultInstance != null) {
            defaultInstance.setOfflineMode(enabled);
        } else {
            AdjustFactory.getLogger().error("Adjust not initialized correctly");
        }
    }

    public static synchronized void teardown() {
        if (defaultInstance != null) {
            defaultInstance.teardown();
            defaultInstance = null;
        }
    }
}


