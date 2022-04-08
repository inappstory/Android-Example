package com.inappstory.javaexamples;

import android.app.Application;

import com.inappstory.sdk.InAppStoryManager;
import com.inappstory.sdk.exceptions.DataException;


public class DemoApplication extends Application {

    public void onCreate() {
        super.onCreate();

        try {
            new InAppStoryManager.Builder()
                    .userId(getUserId())
                   //.testKey(getTestKey())
                    .context(getApplicationContext())
                    .create();
        } catch (DataException e) {
            e.printStackTrace();
            return;
        }

    }

    public static String getUserId() {
        /*TODO*/
        throw new UnsupportedOperationException("Not implemented yet");
    }


    public String getTestKey() {
        /*TODO*/
        throw new UnsupportedOperationException("Not implemented yet");
    }
}
