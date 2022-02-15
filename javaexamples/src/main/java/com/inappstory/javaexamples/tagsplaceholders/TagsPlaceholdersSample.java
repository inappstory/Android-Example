package com.inappstory.javaexamples.tagsplaceholders;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.inappstory.javaexamples.R;
import com.inappstory.sdk.AppearanceManager;
import com.inappstory.sdk.InAppStoryManager;
import com.inappstory.sdk.exceptions.DataException;
import com.inappstory.sdk.stories.ui.list.StoriesList;

import java.util.ArrayList;
import java.util.HashMap;

public class TagsPlaceholdersSample extends AppCompatActivity {



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_placeholders);
        findViewById(R.id.refresh).setOnClickListener(v -> {
            setTags();
            addTags();
            removeTags();
            setPlaceholders();
        });
        showStories();
    }

    private void removeTags() {
        ArrayList<String> removedTags = new ArrayList<>();
        removedTags.add("tag3");
        InAppStoryManager.getInstance().removeTags(removedTags);
    }

    private void addTags() {
        ArrayList<String> additionalTags = new ArrayList<>();
        additionalTags.add("tag3");
        additionalTags.add("tag4");
        InAppStoryManager.getInstance().addTags(additionalTags);
    }

    private void setTags() {
        ArrayList<String> tags = new ArrayList<>();
        tags.add("tag1");
        tags.add("tag2");
        InAppStoryManager.getInstance().setTags(tags);
    }

    private void setPlaceholders() {
        HashMap<String, String> placeholders = new HashMap<>();
        placeholders.put("replaceme1", "value1");
        placeholders.put("replaceme2", "value2");
        InAppStoryManager.getInstance().setPlaceholders(placeholders);
        InAppStoryManager.getInstance().setPlaceholder("replaceme2", "value3");
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

    private void getTagsAndPlaceholders() {
        InAppStoryManager.getInstance().getTags();

        InAppStoryManager.getInstance().getTagsString();

        InAppStoryManager.getInstance().getPlaceholders();
    }

    @Override
    protected void onDestroy() {
        InAppStoryManager.getInstance().setTags(null);
        InAppStoryManager.getInstance().setPlaceholders(new HashMap<>());
        super.onDestroy();
    }
}
