package com.inappstory.javaexamples.onboarding;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.inappstory.javaexamples.R;
import com.inappstory.sdk.AppearanceManager;
import com.inappstory.sdk.InAppStoryManager;
import com.inappstory.sdk.stories.outercallbacks.common.onboarding.OnboardingLoadCallback;
import com.inappstory.sdk.stories.ui.list.StoriesList;


public class OnboardingSample extends AppCompatActivity {

    AppearanceManager appearanceManager = new AppearanceManager();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base_list);
        InAppStoryManager.getInstance().setOnboardingLoadCallback(new OnboardingLoadCallback() {
            @Override
            public void onboardingLoad(int count, String feed) {

            }
        });
        InAppStoryManager.getInstance().showOnboardingStories(OnboardingSample.this, appearanceManager);
        showStories();
    }


    private void showStories() {
        StoriesList storiesList = findViewById(R.id.stories_list);
        storiesList.setAppearanceManager(appearanceManager);
        storiesList.loadStories();
    }


}
