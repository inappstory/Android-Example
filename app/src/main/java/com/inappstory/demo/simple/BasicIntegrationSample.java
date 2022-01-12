package com.inappstory.demo.simple;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.ViewStub;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.inappstory.demo.R;
import com.inappstory.sdk.AppearanceManager;
import com.inappstory.sdk.InAppStoryManager;
import com.inappstory.sdk.exceptions.DataException;
import com.inappstory.sdk.stories.ui.list.StoriesList;

public class BasicIntegrationSample extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base_list);
        showStories();

        String s = null;
        s.length();
    }

    StoriesList storiesList;

    private void showStories() {

        storiesList = findViewById(R.id.stories_list);
        storiesList.setAppearanceManager(new AppearanceManager());
        try {
            storiesList.loadStories();
        } catch (DataException e) {
            e.printStackTrace();
        }

    }


}
