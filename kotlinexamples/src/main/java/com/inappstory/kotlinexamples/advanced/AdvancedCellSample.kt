package com.inappstory.kotlinexamples.advanced

import android.graphics.Color
import android.graphics.Typeface
import android.os.Build
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.inappstory.kotlinexamples.R
import com.inappstory.sdk.AppearanceManager
import com.inappstory.sdk.stories.api.models.Image
import com.inappstory.sdk.stories.ui.list.StoriesList
import com.inappstory.sdk.stories.ui.reader.StoriesGradientObject
import com.inappstory.sdk.stories.utils.Sizes

class AdvancedCellSample : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_base_list)
        showStories()
    }

    private fun generateAppearanceManager(isHQ: Boolean): AppearanceManager {
        val gradient = StoriesGradientObject()
            .csColors(
                mutableListOf(
                    Color.parseColor("#90000000"),
                    Color.parseColor("#00000000"),
                    Color.parseColor("#00000000")
                )
            )
            .csLocations(mutableListOf(0f, 0.2f, 1f))
            .csGradientHeight(-1)
        return AppearanceManager()
            .csListOpenedItemBorderColor(Color.RED)
            //.csListOpenedItemBorderVisibility(true)
            /*.csListItemHeight(Sizes.dpToPxExt(100))
            .csListItemRatio(1.2f)
            .csListItemTitleColor(Color.RED)
            .csTimerGradientEnable(true)
            .csTimerGradient(gradient)
            .csListItemRadius(Sizes.dpToPxExt(4))
            .csListItemTitleSize(Sizes.dpToPxExt(12))
            .csListItemMargin(Sizes.dpToPxExt(4))
            .csCustomFont(createTypeface()) //If you want to share this font to dialogs in stories, set this appearanceManager as global
            .csCoverQuality(if (isHQ) Image.QUALITY_HIGH else Image.QUALITY_MEDIUM)*/
            .csListItemBorderColor(Color.GREEN)
    }

    private fun createTypeface(): Typeface {

        var typeface = Typeface.createFromAsset(
            assets, "synerga_pro_reg.otf"
        )
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            typeface = resources.getFont(R.font.font1)
        }
        return typeface
    }

    private fun showStories() {
        val storiesList: StoriesList = findViewById(R.id.stories_list)
        storiesList.appearanceManager = generateAppearanceManager(
            intent.getBooleanExtra("isHQ", false)
        )
        storiesList.loadStories()
    }
}
