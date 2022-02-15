package com.inappstory.kotlinexamples.advanced

import android.graphics.Color
import android.graphics.Typeface
import android.os.Build
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.inappstory.kotlinexamples.R
import com.inappstory.sdk.AppearanceManager
import com.inappstory.sdk.exceptions.DataException
import com.inappstory.sdk.stories.api.models.Image
import com.inappstory.sdk.stories.ui.list.StoriesList
import com.inappstory.sdk.stories.utils.Sizes

class AdvancedCellSample : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_base_list)
        showStories()
    }

    private fun generateAppearanceManager(isHQ: Boolean): AppearanceManager {
        return AppearanceManager()
            .csListItemWidth(Sizes.dpToPxExt(110))
            .csListItemHeight(Sizes.dpToPxExt(140))
            .csListItemTitleColor(Color.RED)
            .csListItemRadius(Sizes.dpToPxExt(4))
            .csListItemTitleSize(Sizes.dpToPxExt(12))
            .csListItemMargin(Sizes.dpToPxExt(4))
            .csCustomFont(createTypeface()) //If you want to share this font to dialogs in stories, set this appearanceManager as global
            .csCoverQuality(if (isHQ) Image.QUALITY_HIGH else Image.QUALITY_MEDIUM)
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
        storiesList.setAppearanceManager(
            generateAppearanceManager(
                intent.getBooleanExtra("isHQ", false)
            )
        )
        try {
            storiesList.loadStories()
        } catch (e: DataException) {
            e.printStackTrace()
        }
    }
}
