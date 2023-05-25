package com.inappstory.javaexamples.notification;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.inappstory.javaexamples.R;
import com.inappstory.sdk.AppearanceManager;
import com.inappstory.sdk.InAppStoryManager;
import com.inappstory.sdk.stories.outercallbacks.storieslist.ListCallback;
import com.inappstory.sdk.stories.outercallbacks.storieslist.ListCallbackAdapter;
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
        InAppStoryManager.getInstance().setShowStoryCallback((id, title, tags, slidesCount, source, action) -> {

        });
        InAppStoryManager.getInstance().setCloseStoryCallback((id, title, tags, slidesCount, index, action, source) -> {

        });
        boolean adapterCallback = false;
        if (adapterCallback) {
            storiesList.setCallback(new ListCallbackAdapter() {
                @Override
                public void storiesLoaded(int size, String feed) {

                }
            });
        } else {
            storiesList.setCallback(new ListCallback() {
                @Override
                public void storiesLoaded(int size, String feed) {

                }

                @Override
                public void storiesUpdated(int i, String s) {

                }

                @Override
                public void loadError(String feed) {

                }

                @Override
                public void itemClick(int id,
                                      int listIndex,
                                      String title,
                                      String tags,
                                      int slidesCount,
                                      boolean isFavoriteList,
                                      String feed) {

                }
            });
        }
        storiesList.loadStories();
    }
}
