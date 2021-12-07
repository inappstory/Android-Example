package com.inappstory.demo.userchange;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.inappstory.demo.R;
import com.inappstory.sdk.AppearanceManager;
import com.inappstory.sdk.InAppStoryManager;
import com.inappstory.sdk.exceptions.DataException;
import com.inappstory.sdk.stories.ui.list.StoriesList;

import java.util.UUID;

import static com.inappstory.demo.DemoApplication.USER_ID;

public class UserChangeSample extends AppCompatActivity {

    AppearanceManager appearanceManager = new AppearanceManager();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);
        showStories();
        findViewById(R.id.change_user).setOnClickListener(v -> {
            String newUser = UUID.randomUUID().toString().substring(0, 8);
            try {
                InAppStoryManager.getInstance().setUserId(newUser);
                //showStories();
            } catch (DataException e) {
                e.printStackTrace();
            }
        });
    }

    @Override
    protected void onDestroy() {
        try {
            InAppStoryManager.getInstance().setUserId(USER_ID);
        } catch (DataException e) {
            e.printStackTrace();
        }
        super.onDestroy();
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
