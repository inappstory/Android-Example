package com.inappstory.kotlinexamples.tagsplaceholders

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.inappstory.kotlinexamples.R
import com.inappstory.sdk.AppearanceManager
import com.inappstory.sdk.InAppStoryManager
import com.inappstory.sdk.exceptions.DataException
import com.inappstory.sdk.stories.ui.list.StoriesList
import java.util.ArrayList
import java.util.HashMap

class TagsPlaceholdersSample : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_placeholders)
        findViewById<View>(R.id.refresh).setOnClickListener { v: View? ->
            setTags()
            addTags()
            removeTags()
            setPlaceholders()
        }
        showStories()
    }

    private fun removeTags() {
        val removedTags = ArrayList<String>()
        removedTags.add("tag3")
        InAppStoryManager.getInstance().removeTags(removedTags)
    }

    private fun addTags() {
        val additionalTags = ArrayList<String>()
        additionalTags.add("tag3")
        additionalTags.add("tag4")
        InAppStoryManager.getInstance().addTags(additionalTags)
    }

    private fun setTags() {
        val tags = ArrayList<String>()
        tags.add("tag1")
        tags.add("tag2")
        InAppStoryManager.getInstance().tags = tags
    }

    private fun setPlaceholders() {
        val placeholders = HashMap<String, String>()
        placeholders["replaceme1"] = "value1"
        placeholders["replaceme2"] = "value2"
        InAppStoryManager.getInstance().setPlaceholders(placeholders)
        InAppStoryManager.getInstance().setPlaceholder("replaceme2", "value3")
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

    private val tagsAndPlaceholders: Unit
        private get() {
            InAppStoryManager.getInstance().getTags()
            InAppStoryManager.getInstance().getTagsString()
            InAppStoryManager.getInstance().getPlaceholders()
        }

    override fun onDestroy() {
        InAppStoryManager.getInstance().setTags(null)
        InAppStoryManager.getInstance().setPlaceholders(HashMap<String, String>())
        super.onDestroy()
    }
}