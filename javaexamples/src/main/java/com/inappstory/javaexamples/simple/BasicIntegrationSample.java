package com.inappstory.javaexamples.simple;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.inappstory.javaexamples.R;
import com.inappstory.sdk.AppearanceManager;
import com.inappstory.sdk.stories.ui.list.StoriesList;

public class BasicIntegrationSample extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base_list);
        showStories();
    }

    boolean startStop = false;

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    StoriesList storiesList;

    private void showStories() {

        storiesList = findViewById(R.id.stories_list);
        storiesList.setAppearanceManager(new AppearanceManager());

        storiesList.loadStories();
    }


}
