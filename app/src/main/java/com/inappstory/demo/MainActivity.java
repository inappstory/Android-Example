package com.inappstory.demo;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.inappstory.demo.advanced.AdvancedCellSample;
import com.inappstory.demo.custom.CustomCellSample;
import com.inappstory.demo.customreader.ReaderCustomizationSample;
import com.inappstory.demo.favorites.FavoritesSample;
import com.inappstory.demo.notification.NotificationSubscribeSample;
import com.inappstory.demo.onboarding.OnboardingSample;
import com.inappstory.demo.simple.BasicIntegrationSample;
import com.inappstory.demo.single.SingleStorySample;
import com.inappstory.demo.tagsplaceholders.TagsPlaceholdersSample;
import com.inappstory.demo.userchange.UserChangeSample;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.simple).setOnClickListener(v -> showActivity(BasicIntegrationSample.class));
        findViewById(R.id.advanced).setOnClickListener(v -> showActivity(AdvancedCellSample.class));
        findViewById(R.id.custom).setOnClickListener(v -> showActivity(CustomCellSample.class));
        findViewById(R.id.favorites).setOnClickListener(v -> showActivity(FavoritesSample.class));
        findViewById(R.id.custom_reader).setOnClickListener(v -> showActivity(ReaderCustomizationSample.class));
        findViewById(R.id.onboarding).setOnClickListener(v -> showActivity(OnboardingSample.class));
        findViewById(R.id.single).setOnClickListener(v -> showActivity(SingleStorySample.class));
        findViewById(R.id.change_user).setOnClickListener(v -> showActivity(UserChangeSample.class));
        findViewById(R.id.tags).setOnClickListener(v -> showActivity(TagsPlaceholdersSample.class));
        findViewById(R.id.notification).setOnClickListener(v -> showActivity(NotificationSubscribeSample.class));
    }

    private void showActivity(Class activityClass) {
        Intent intent = new Intent(MainActivity.this, activityClass);
        startActivity(intent);
    }


}
