package com.inappstory.demo;

import android.app.Application;
import android.util.Log;

import com.inappstory.sdk.InAppStoryManager;
import com.inappstory.sdk.exceptions.DataException;

import java.util.ArrayList;


public class DemoApplication extends Application {

    public static final String USER_ID = "cs_default_user1";

    public void onCreate() {
        super.onCreate();
        InAppStoryManager.logger = new InAppStoryManager.IASLogger() {
            @Override
            public void showELog(String s, String s1) {
                Log.e(s, s1);
            }

            @Override
            public void showDLog(String s, String s1) {
                Log.d(s, s1);
            }
        };
        try {
            new InAppStoryManager.Builder()
                    .userId("")
                    .sandbox(false)
                   // .testKey("vyOQr1M9A42CyoAic6lgJupiorcI-0Fp")
                    .sendStatistic(false)
                    .context(getApplicationContext())
                    .create();
        } catch (DataException e) {
            e.printStackTrace();
            return;
        }
    }
}
