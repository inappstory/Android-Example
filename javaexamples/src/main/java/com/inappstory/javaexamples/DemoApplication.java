package com.inappstory.javaexamples;

import android.app.Application;
import android.util.Log;

import com.inappstory.sdk.InAppStoryManager;


public class DemoApplication extends Application {

    public void onCreate() {
        super.onCreate();

        InAppStoryManager.initSDK(getApplicationContext());
        new InAppStoryManager.Builder()
                .userId(getUserId())
                .apiKey(getApiKey())
               // .sandbox(true)
                //.testKey(getTestKey())
                //.context(getApplicationContext())
                .create();
        InAppStoryManager.logger = new InAppStoryManager.IASLogger() {
            @Override
            public void showELog(String s, String s1) {
                Log.d(s, s1);
            }

            @Override
            public void showDLog(String s, String s1) {
                Log.d(s, s1);
            }
        };

    }

    public static String getUserId() {
        /*TODO*/
        return "21312";
        //throw new UnsupportedOperationException("Not implemented yet");
    }

    public String getApiKey() {
        return "";
    }

    public String getTestKey() {
        /*TODO*/
        throw new UnsupportedOperationException("Not implemented yet");
    }
}
