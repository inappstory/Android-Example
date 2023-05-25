package com.inappstory.javaexamples;

import android.app.Application;
import android.util.Log;

import com.inappstory.sdk.InAppStoryManager;


public class DemoApplication extends Application {

    public void onCreate() {
        super.onCreate();

        new InAppStoryManager.Builder()
                .userId(getUserId())
                .sandbox(true)
                //.testKey(getTestKey())
                .context(getApplicationContext())
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
        return "";
        //throw new UnsupportedOperationException("Not implemented yet");
    }


    public String getTestKey() {
        /*TODO*/
        throw new UnsupportedOperationException("Not implemented yet");
    }
}
