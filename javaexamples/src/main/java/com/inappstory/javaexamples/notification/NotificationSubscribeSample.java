package com.inappstory.javaexamples.notification;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.inappstory.javaexamples.R;
import com.inappstory.sdk.AppearanceManager;
import com.inappstory.sdk.InAppStoryManager;
import com.inappstory.sdk.stories.outercallbacks.common.reader.StoryData;
import com.inappstory.sdk.stories.outercallbacks.storieslist.ListCallback;
import com.inappstory.sdk.stories.outercallbacks.storieslist.ListCallbackAdapter;
import com.inappstory.sdk.stories.outercallbacks.storieslist.ListScrollCallback;
import com.inappstory.sdk.stories.outercallbacks.storieslist.ListScrollCallbackAdapter;
import com.inappstory.sdk.stories.ui.list.ShownStoriesListItem;
import com.inappstory.sdk.stories.ui.list.StoriesList;

import java.util.List;

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
        InAppStoryManager.getInstance().setShowStoryCallback((storyData, showStoryAction) -> {

        });
        InAppStoryManager.getInstance().setCloseStoryCallback((slideData, closeReader) -> {

        });
        boolean adapterCallback = false;
        if (adapterCallback) {
            storiesList.setCallback(new ListCallbackAdapter() {
                @Override
                public void storiesLoaded(int size, String feed) {

                }
            });
            storiesList.setScrollCallback(new ListScrollCallbackAdapter() {
                @Override
                public void onVisibleAreaUpdated(List<ShownStoriesListItem> list) {

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
                public void itemClick(StoryData storyData, int i) {

                }
            });
            storiesList.setScrollCallback(new ListScrollCallback() {
                @Override
                public void scrollStart() {

                }

                @Override
                public void onVisibleAreaUpdated(List<ShownStoriesListItem> list) {

                }

                @Override
                public void scrollEnd() {

                }
            });
        }
        storiesList.loadStories();
    }
}
