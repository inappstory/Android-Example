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
                    .sandbox(false)
                   //.testKey(getTestKey())
                    .context(getApplicationContext())
                    .create();
        } catch (DataException e) {
            e.printStackTrace();
            return;
        }

    }

    public String getUserId() {
        /*TODO*/
        throw new UnsupportedOperationException("Not implemented yet");
    }


    public String getTestKey() {
        /*TODO*/
        throw new UnsupportedOperationException("Not implemented yet");
    }
}
