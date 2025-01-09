package com.inappstory.kotlinexamples.onboarding

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.inappstory.kotlinexamples.R
import com.inappstory.sdk.stories.ui.list.StoriesList
import com.inappstory.sdk.AppearanceManager
import com.inappstory.sdk.InAppStoryManager

class OnboardingSample : AppCompatActivity() {
    var appearanceManager = AppearanceManager()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_base_list)
        InAppStoryManager.getInstance().setOnboardingLoadCallback(
            OnboardingCallback(this)
        )
        InAppStoryManager.getInstance()
            .showOnboardingStories(this@OnboardingSample, appearanceManager)
        showStories()
    }

    private fun showStories() {
        val storiesList = findViewById<StoriesList>(R.id.stories_list)
        storiesList.setAppearanceManager(appearanceManager)
        storiesList.loadStories()
    }
}