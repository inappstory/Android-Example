package com.inappstory.demo.advanced;

import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.inappstory.demo.R;
import com.inappstory.sdk.AppearanceManager;
import com.inappstory.sdk.exceptions.DataException;
import com.inappstory.sdk.stories.api.models.Image;
import com.inappstory.sdk.stories.ui.list.StoriesList;
import com.inappstory.sdk.stories.utils.Sizes;

public class AdvancedCellSample extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base_list);
        showStories();
    }

    private AppearanceManager generateAppearanceManager(boolean isHQ) {
        AppearanceManager appearanceManager =
                new AppearanceManager()
                        .csListItemWidth(Sizes.dpToPxExt(110))
                        .csListItemHeight(Sizes.dpToPxExt(140))
                        .csListItemTitleColor(Color.RED)
                        .csListItemTitleSize(Sizes.dpToPxExt(12))
                        .csListItemMargin(Sizes.dpToPxExt(4))
                        .csCustomFont(createTypeface())
                        .csCoverQuality(isHQ ? Image.QUALITY_HIGH : Image.QUALITY_MEDIUM)
                        .csListItemBorderColor(Color.GREEN);
        return appearanceManager;
    }

    private Typeface createTypeface() {
        Typeface typeface = Typeface.createFromAsset(getAssets(), "synerga_pro_reg.otf");

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            typeface = getResources().getFont(R.font.font1);
        }

        return typeface;
    }

    private void showStories() {
        StoriesList storiesList = findViewById(R.id.stories_list);
        storiesList.setAppearanceManager(generateAppearanceManager(
                getIntent().getBooleanExtra("isHQ", false)));
        try {
            storiesList.loadStories();
        } catch (DataException e) {
            e.printStackTrace();
        }
    }


}
