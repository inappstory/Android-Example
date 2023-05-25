package com.inappstory.javaexamples.feeds;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatEditText;

import com.inappstory.javaexamples.R;
import com.inappstory.sdk.AppearanceManager;
import com.inappstory.sdk.InAppStoryManager;
import com.inappstory.sdk.stories.ui.list.StoriesList;

public class FeedSample extends AppCompatActivity {

    AppearanceManager appearanceManager = new AppearanceManager();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed);

        StoriesList storiesList = findViewById(R.id.stories_list);
        storiesList.setAppearanceManager(appearanceManager.csHasFavorite(false));
        findViewById(R.id.show_onboarding).setOnClickListener(v -> {
            String feed = ((AppCompatEditText)findViewById(R.id.feed)).getText().toString();
            InAppStoryManager.getInstance()
                    .showOnboardingStories(feed, FeedSample.this, appearanceManager);
        });
        findViewById(R.id.show_onboarding).setOnClickListener(v -> {
            String feed = ((AppCompatEditText)findViewById(R.id.feed)).getText().toString();
            storiesList.setFeed(feed);

            storiesList.loadStories();
        });
        storiesList.loadStories();
    }


}
