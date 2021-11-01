package com.inappstory.demo.notification;

import android.os.Bundle;
import android.os.Handler;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.inappstory.demo.R;
import com.inappstory.sdk.AppearanceManager;
import com.inappstory.sdk.InAppStoryManager;
import com.inappstory.sdk.eventbus.CsEventBus;
import com.inappstory.sdk.eventbus.CsSubscribe;
import com.inappstory.sdk.eventbus.CsThreadMode;
import com.inappstory.sdk.exceptions.DataException;
import com.inappstory.sdk.stories.outercallbacks.common.reader.CloseReader;
import com.inappstory.sdk.stories.outercallbacks.common.reader.CloseStoryCallback;
import com.inappstory.sdk.stories.outercallbacks.common.reader.ShowStoryCallback;
import com.inappstory.sdk.stories.outercallbacks.common.reader.SourceType;
import com.inappstory.sdk.stories.outercallbacks.storieslist.ListCallback;
import com.inappstory.sdk.stories.outercallbacks.storieslist.ListCallbackAdapter;
import com.inappstory.sdk.stories.outerevents.ClickOnStory;
import com.inappstory.sdk.stories.outerevents.StoriesLoaded;
import com.inappstory.sdk.stories.ui.list.StoriesList;

public class NotificationSubscribeSample extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base_list);
        showStories();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    private void showStories() {
        StoriesList storiesList = findViewById(R.id.stories_list);
        storiesList.setAppearanceManager(new AppearanceManager());
        InAppStoryManager.getInstance().setShowStoryCallback(new ShowStoryCallback() {
            @Override
            public void showStory(int id,
                                  String title,
                                  String tags,
                                  int slidesCount,
                                  SourceType source) {

            }
        });
        InAppStoryManager.getInstance().setCloseStoryCallback(new CloseStoryCallback() {
            @Override
            public void closeStory(int id,
                                   String title,
                                   String tags,
                                   int slidesCount,
                                   int index,
                                   CloseReader action,
                                   SourceType source) {

            }
        });
        boolean adapterCallback = false;
        try {
            if (adapterCallback) {
                storiesList.setCallback(new ListCallbackAdapter() {
                    @Override
                    public void storiesLoaded(int size) {

                    }
                });
            } else {
                storiesList.setCallback(new ListCallback() {
                    @Override
                    public void storiesLoaded(int size) {

                    }

                    @Override
                    public void loadError() {

                    }

                    @Override
                    public void itemClick(int id,
                                          int listIndex,
                                          String title,
                                          String tags,
                                          int slidesCount,
                                          boolean isFavoriteList) {

                    }
                });
            }
            storiesList.loadStories();
        } catch (DataException e) {
            e.printStackTrace();
        }
    }


}
