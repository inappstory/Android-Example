package com.inappstory.demo.notification;

import android.os.Bundle;
import android.os.Handler;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.inappstory.demo.R;
import com.inappstory.sdk.AppearanceManager;
import com.inappstory.sdk.eventbus.CsEventBus;
import com.inappstory.sdk.eventbus.CsSubscribe;
import com.inappstory.sdk.eventbus.CsThreadMode;
import com.inappstory.sdk.exceptions.DataException;
import com.inappstory.sdk.stories.outerevents.ClickOnStory;
import com.inappstory.sdk.stories.outerevents.StoriesLoaded;
import com.inappstory.sdk.stories.ui.list.StoriesList;

public class NotificationSubscribeSample extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base_list);
        CsEventBus.getDefault().register(this);
        showStories();
    }

    //We'll get event in main thread
    @CsSubscribe(threadMode = CsThreadMode.MAIN)
    public void doSmth1(StoriesLoaded storiesLoadedEvent) {
        if (storiesLoadedEvent.getCount() > 0) {
            doSmth();
        }
    }

    void doSmth() {
        Toast.makeText(NotificationSubscribeSample.this, "Event example", Toast.LENGTH_LONG).show();
    }


    //We'll get event not in main thread
    @CsSubscribe
    public void doSmth2(ClickOnStory clickOnStoryEvent) {
        new Handler(getMainLooper()).post(new Runnable() {
            @Override
            public void run() {
                if (clickOnStoryEvent.getListIndex() == 1)
                    doSmth();
            }
        });
    }

    @Override
    protected void onDestroy() {
        CsEventBus.getDefault().unregister(this);
        super.onDestroy();
    }

    private void showStories() {
        StoriesList storiesList = findViewById(R.id.stories_list);
        storiesList.setAppearanceManager(new AppearanceManager());
        try {
            storiesList.loadStories();
        } catch (DataException e) {
            e.printStackTrace();
        }
    }


}
