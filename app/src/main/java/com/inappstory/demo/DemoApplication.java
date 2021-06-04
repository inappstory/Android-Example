package com.inappstory.demo;

import android.app.Application;

import com.inappstory.sdk.InAppStoryManager;
import com.inappstory.sdk.exceptions.DataException;

import java.util.ArrayList;

public class DemoApplication extends Application {

    public static final String USER_ID = "cs_default_user";

    public void onCreate() {
        super.onCreate();
        try {
            new InAppStoryManager.Builder()
                    .userId(USER_ID)
                    .context(getApplicationContext())
                    .create();
        } catch (DataException e) {
            return;
        }
    }
}
