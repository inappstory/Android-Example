package com.inappstory.demo.customreader;

import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.inappstory.demo.R;
import com.inappstory.sdk.AppearanceManager;
import com.inappstory.sdk.exceptions.DataException;
import com.inappstory.sdk.stories.ui.list.StoriesList;
import com.inappstory.sdk.stories.utils.Sizes;

public class ReaderCustomizationSample extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base_list);
        showStories();
    }


    private AppearanceManager generateAppearanceManager() {
        AppearanceManager appearanceManager =
                new AppearanceManager();
                        //.csHasLike(true)
                      //  .csHasShare(true)
        appearanceManager.csClosePosition(AppearanceManager.BOTTOM_LEFT);
        appearanceManager.csStoryReaderAnimation(AppearanceManager.ANIMATION_DEPTH);
                   //     .csTimerGradientEnable(false)
        appearanceManager.csNavBarColor(Color.BLACK);
                 //       .csNightNavBarColor(Color.DKGRAY)
                     //   .csCloseIcon(R.drawable.custom_close_icon)
                  //      .csShareIcon(R.drawable.share_state)
                 //       .csLikeIcon(R.drawable.like_state)
                 //       .csDislikeIcon(R.drawable.dislike_state);
        return appearanceManager;
    }

    private void showStories() {
        StoriesList storiesList = findViewById(R.id.stories_list);
        storiesList.setAppearanceManager(generateAppearanceManager());
        try {
            storiesList.loadStories();
        } catch (DataException e) {
            e.printStackTrace();
        }
    }


}
