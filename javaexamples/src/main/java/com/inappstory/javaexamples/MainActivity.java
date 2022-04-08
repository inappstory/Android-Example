package com.inappstory.javaexamples;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatCheckBox;

import com.inappstory.javaexamples.advanced.AdvancedCellSample;
import com.inappstory.javaexamples.custom.CustomCellSample;
import com.inappstory.javaexamples.customreader.ReaderCustomizationSample;
import com.inappstory.javaexamples.favorites.FavoritesSample;
import com.inappstory.javaexamples.feeds.FeedSample;
import com.inappstory.javaexamples.goodswidget.CustomCellWidgetSample;
import com.inappstory.javaexamples.goodswidget.CustomWidgetSample;
import com.inappstory.javaexamples.goodswidget.DefaultWidgetSample;
import com.inappstory.javaexamples.notification.NotificationSubscribeSample;
import com.inappstory.javaexamples.onboarding.OnboardingSample;
import com.inappstory.javaexamples.simple.BasicIntegrationSample;
import com.inappstory.javaexamples.single.SingleStorySample;
import com.inappstory.javaexamples.tagsplaceholders.TagsPlaceholdersSample;
import com.inappstory.javaexamples.userchange.UserChangeSample;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        AppCompatCheckBox isHQcb = findViewById(R.id.isHQ);
        findViewById(R.id.simple).setOnClickListener(v -> showActivity(BasicIntegrationSample.class));
        findViewById(R.id.advanced).setOnClickListener(v -> showActivity(AdvancedCellSample.class, isHQcb.isChecked()));
        findViewById(R.id.custom).setOnClickListener(v -> showActivity(CustomCellSample.class));
        findViewById(R.id.favorites).setOnClickListener(v -> showActivity(FavoritesSample.class));
        findViewById(R.id.custom_reader).setOnClickListener(v -> showActivity(ReaderCustomizationSample.class));
        findViewById(R.id.onboarding).setOnClickListener(v -> showActivity(OnboardingSample.class));
        findViewById(R.id.single).setOnClickListener(v -> showActivity(SingleStorySample.class));
        findViewById(R.id.feeds).setOnClickListener(v -> showActivity(FeedSample.class));
        findViewById(R.id.change_user).setOnClickListener(v -> showActivity(UserChangeSample.class));
        findViewById(R.id.tags).setOnClickListener(v -> showActivity(TagsPlaceholdersSample.class));
        findViewById(R.id.notification).setOnClickListener(v -> showActivity(NotificationSubscribeSample.class));
        findViewById(R.id.base_carousel).setOnClickListener(v -> showActivity(DefaultWidgetSample.class));
        findViewById(R.id.custom_carousel).setOnClickListener(v -> showActivity(CustomCellWidgetSample.class));
        findViewById(R.id.custom_goods_widget).setOnClickListener(v -> showActivity(CustomWidgetSample.class));
    }

    private void showActivity(Class activityClass) {
        Intent intent = new Intent(MainActivity.this, activityClass);
        startActivity(intent);
    }


    private void showActivity(Class activityClass, boolean isHQ) {
        Intent intent = new Intent(MainActivity.this, activityClass);
        intent.putExtra("isHQ", isHQ);
        startActivity(intent);
    }


}
