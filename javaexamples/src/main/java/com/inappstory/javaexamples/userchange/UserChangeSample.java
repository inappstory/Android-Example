package com.inappstory.javaexamples.userchange;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatEditText;

import com.inappstory.javaexamples.DemoApplication;
import com.inappstory.javaexamples.R;
import com.inappstory.javaexamples.favorites.StoryFavoritesActivity;
import com.inappstory.sdk.AppearanceManager;
import com.inappstory.sdk.InAppStoryManager;
import com.inappstory.sdk.stories.ui.list.StoriesList;

import java.util.UUID;


public class UserChangeSample extends AppCompatActivity {

    AppearanceManager appearanceManager = new AppearanceManager();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);
        String newUser = UUID.randomUUID().toString().substring(0, 8);
        ((AppCompatEditText)findViewById(R.id.userId)).setText(newUser);
        findViewById(R.id.change_user).setOnClickListener(v -> {
            InAppStoryManager.getInstance().setUserId(((AppCompatEditText)findViewById(R.id.userId)).getText().toString());
        });
        showStories();
    }

    @Override
    protected void onDestroy() {
        InAppStoryManager.getInstance().setUserId(DemoApplication.getUserId());
        super.onDestroy();
    }

    private void showStories() {

        StoriesList storiesList = findViewById(R.id.stories_list);
        storiesList.setAppearanceManager(appearanceManager.csHasFavorite(true));
        storiesList.setOnFavoriteItemClick(() -> {
            Intent intent = new Intent(UserChangeSample.this, StoryFavoritesActivity.class);
            startActivity(intent);
        });
        storiesList.loadStories();
    }

}
