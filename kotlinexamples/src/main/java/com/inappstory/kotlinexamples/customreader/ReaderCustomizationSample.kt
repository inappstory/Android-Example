package com.inappstory.kotlinexamples.customreader

import android.graphics.Color
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.inappstory.kotlinexamples.R
import com.inappstory.sdk.AppearanceManager
import com.inappstory.sdk.exceptions.DataException
import com.inappstory.sdk.stories.ui.list.StoriesList

class ReaderCustomizationSample : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_base_list)
        showStories()
    }

    private fun generateAppearanceManager(): AppearanceManager {
        return AppearanceManager()
            .csClosePosition(AppearanceManager.BOTTOM_LEFT)
            .csStoryReaderAnimation(AppearanceManager.ANIMATION_DEPTH)
            .csNavBarColor(Color.BLACK)
    }

    private fun showStories() {
        val storiesList = findViewById<StoriesList>(R.id.stories_list)
        storiesList.setAppearanceManager(generateAppearanceManager())
        try {
            storiesList.loadStories()
        } catch (e: DataException) {
            e.printStackTrace()
        }
    }
}