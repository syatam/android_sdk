//
//  RequestHandler.java
//  Adjust
//
//  Created by Christian Wellenbrock on 2013-06-25.
//  Copyright (c) 2013 adjust GmbH. All rights reserved.
//  See the file MIT-LICENSE for copying permission.
//

package com.adjust.sdk;

import android.os.*;

import com.adjust.sdk.threading.*;

import java.io.*;
import java.net.*;

import javax.net.ssl.*;

public class RequestHandler_executor implements IRequestHandler {
    private IPackageHandler packageHandler;
    private ILogger logger;

    public RequestHandler_executor(IPackageHandler packageHandler) {
        this.logger = AdjustFactory.getLogger();
        init(packageHandler);
    }

    @Override
    public void init(IPackageHandler packageHandler) {
        this.packageHandler = packageHandler;
    }

    @Override
    public void sendPackage(final ActivityPackage activityPackage, final int queueSize) {
        ThreadManager.getInstance().execute(new Runnable() {
            @Override
            public void run() {
                sendInternal(activityPackage, queueSize);
            }
        });
    }

    private void sendInternal(ActivityPackage activityPackage, int queueSize) {
        String targetURL = Constants.BASE_URL + activityPackage.getPath();

        try {
            HttpsURLConnection connection = Util.createPOSTHttpsURLConnection(
                    targetURL,
                    activityPackage.getClientSdk(),
                    activityPackage.getParameters(),
                    queueSize);

            ResponseData responseData = Util.readHttpResponse(connection, activityPackage);

            if (responseData.jsonResponse == null) {
                packageHandler.closeFirstPackage(responseData, activityPackage);
                return;
            }

            packageHandler.sendNextPackage(responseData);

        } catch (UnsupportedEncodingException e) {
            sendNextPackage(activityPackage, "Failed to encode parameters", e);
        } catch (SocketTimeoutException e) {
            closePackage(activityPackage, "Request timed out", e);
        } catch (IOException e) {
            closePackage(activityPackage, "Request failed", e);
        } catch (Throwable e) {
            sendNextPackage(activityPackage, "Runtime exception", e);
        }
    }

    // close current package because it failed
    private void closePackage(ActivityPackage activityPackage, String message, Throwable throwable) {
        final String packageMessage = activityPackage.getFailureMessage();
        final String reasonString = Util.getReasonString(message, throwable);
        String finalMessage = String.format("%s. (%s) Will retry later", packageMessage, reasonString);
        logger.error(finalMessage);

        ResponseData responseData = ResponseData.buildResponseData(activityPackage);
        responseData.message = finalMessage;

        packageHandler.closeFirstPackage(responseData, activityPackage);
    }

    // send next package because the current package failed
    private void sendNextPackage(ActivityPackage activityPackage, String message, Throwable throwable) {
        final String failureMessage = activityPackage.getFailureMessage();
        final String reasonString = Util.getReasonString(message, throwable);
        String finalMessage = String.format("%s. (%s)", failureMessage, reasonString);
        logger.error(finalMessage);

        ResponseData responseData = ResponseData.buildResponseData(activityPackage);
        responseData.message = finalMessage;

        packageHandler.sendNextPackage(responseData);
    }
}
