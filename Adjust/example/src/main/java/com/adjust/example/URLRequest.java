package com.adjust.example;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by uerceg on 03/12/15.
 */
public class URLRequest {
    public static String forgetDevice(String appToken, String gpsAdid, String androidId) {
        try {
            URL url = new URL(Common.BASE_FORGET_URL);
            Map<String, Object> params = new LinkedHashMap<>();

            params.put(Common.PARAM_APP_TOKEN, appToken);

            if (gpsAdid == null && androidId == null) {
                return "Invalid parameters";
            } else {
                if (gpsAdid != null) {
                    params.put(Common.PARAM_GSP_ADID, gpsAdid);
                } else if (androidId != null) {
                    params.put(Common.PARAM_ANDROID_ID, androidId);
                }
            }

            StringBuilder postData = new StringBuilder();

            for (Map.Entry<String, Object> param : params.entrySet()) {
                if (postData.length() != 0) {
                    postData.append('&');
                }

                postData.append(URLEncoder.encode(param.getKey(), "UTF-8"));
                postData.append('=');
                postData.append(URLEncoder.encode(String.valueOf(param.getValue()), "UTF-8"));
            }

            byte[] postDataBytes = postData.toString().getBytes("UTF-8");

            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            conn.setRequestProperty("Content-Length", String.valueOf(postDataBytes.length));
            conn.setDoOutput(true);
            conn.getOutputStream().write(postDataBytes);

            Reader input = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
            StringWriter sw = new StringWriter();

            int n;
            char[] buffer = new char[1024 * 4];

            while (-1 != (n = input.read(buffer))) {
                sw.write(buffer, 0, n);
            }

            return sw.toString();
        } catch (Exception e) {
            e.printStackTrace();
            return "Exception";
        }
    }
}
