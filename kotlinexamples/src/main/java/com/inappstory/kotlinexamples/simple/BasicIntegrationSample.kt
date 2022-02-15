package com.inappstory.kotlinexamples.simple

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.inappstory.kotlinexamples.R
import com.inappstory.sdk.stories.ui.list.StoriesList
import com.inappstory.sdk.AppearanceManager
import com.inappstory.sdk.exceptions.DataException

class BasicIntegrationSample : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_base_list)
        showStories()
    }

    private fun showStories() {
        val storiesList = findViewById<StoriesList>(R.id.stories_list)
        storiesList.setAppearanceManager(AppearanceManager())
        try {
            storiesList.loadStories()
        } catch (e: DataException) {
            e.printStackTrace()
        }
    }
}