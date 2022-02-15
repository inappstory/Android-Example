package com.inappstory.javaexamples.single;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatEditText;

import com.inappstory.javaexamples.R;
import com.inappstory.sdk.AppearanceManager;
import com.inappstory.sdk.InAppStoryManager;
import com.inappstory.sdk.exceptions.DataException;
import com.inappstory.sdk.stories.ui.list.StoriesList;

public class SingleStorySample extends AppCompatActivity {

    AppearanceManager appearanceManager = new AppearanceManager();
    AppCompatEditText storyIdText;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single);
        showStories();
        storyIdText = findViewById(R.id.story_id);
        findViewById(R.id.show_story).setOnClickListener(v -> showSingle(storyIdText.getText().toString()));
    }

    private void showSingle(String storyId) {
        if (storyId == null || storyId.isEmpty()) return;
        InAppStoryManager.getInstance().showStory(storyId, SingleStorySample.this, appearanceManager);
    }

    private void showStories() {
        StoriesList storiesList = findViewById(R.id.stories_list);
        storiesList.setAppearanceManager(appearanceManager);
        try {
            storiesList.loadStories();
        } catch (DataException e) {
            e.printStackTrace();
        }
    }
}
