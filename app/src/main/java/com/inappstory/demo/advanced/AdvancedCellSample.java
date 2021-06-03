package com.inappstory.demo.advanced;

import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.inappstory.demo.R;
import com.inappstory.sdk.AppearanceManager;
import com.inappstory.sdk.exceptions.DataException;
import com.inappstory.sdk.stories.ui.list.StoriesList;
import com.inappstory.sdk.stories.utils.Sizes;

public class AdvancedCellSample extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base_list);
        showStories();
    }

    private AppearanceManager generateAppearanceManager() {
        AppearanceManager appearanceManager =
                new AppearanceManager()
                        .csListItemWidth(Sizes.dpToPxExt(110))
                        .csListItemHeight(Sizes.dpToPxExt(140))
                        .csListItemTitleColor(Color.RED)
                        .csListItemTitleSize(Sizes.dpToPxExt(12))
                        .csListItemMargin(Sizes.dpToPxExt(4))
                        .csListItemBorderColor(Color.GREEN);
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
