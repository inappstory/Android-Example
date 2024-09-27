package com.inappstory.kotlinexamples.single

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatEditText
import com.inappstory.kotlinexamples.R
import com.inappstory.sdk.AppearanceManager
import com.inappstory.sdk.InAppStoryManager
import com.inappstory.sdk.stories.ui.list.StoriesList

class SingleStorySample : AppCompatActivity() {
    var appearanceManager: AppearanceManager = AppearanceManager()
    private lateinit var storyIdText: AppCompatEditText
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_single)
        showStories()
        storyIdText = findViewById(R.id.story_id)
        findViewById<View>(R.id.show_story).setOnClickListener { v: View? ->
            showSingle(
                storyIdText.text.toString()
            )
        }
    }

    private fun showSingle(storyId: String?) {
        if (storyId.isNullOrEmpty()) return
        InAppStoryManager.getInstance()
            .showStory(storyId, this@SingleStorySample, appearanceManager)
    }

    private fun showStories() {
        val storiesList = findViewById<StoriesList>(R.id.stories_list)
        storiesList.appearanceManager = appearanceManager
        storiesList.loadStories()
    }
}