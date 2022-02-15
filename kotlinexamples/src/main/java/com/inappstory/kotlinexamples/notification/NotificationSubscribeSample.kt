package com.inappstory.kotlinexamples.notification

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.inappstory.kotlinexamples.R
import com.inappstory.sdk.stories.ui.list.StoriesList
import com.inappstory.sdk.AppearanceManager
import com.inappstory.sdk.InAppStoryManager
import com.inappstory.sdk.stories.outercallbacks.common.reader.ShowStoryCallback
import com.inappstory.sdk.stories.outercallbacks.common.reader.SourceType
import com.inappstory.sdk.stories.outercallbacks.common.reader.CloseStoryCallback
import com.inappstory.sdk.stories.outercallbacks.common.reader.CloseReader
import com.inappstory.sdk.stories.outercallbacks.storieslist.ListCallbackAdapter
import com.inappstory.sdk.stories.outercallbacks.storieslist.ListCallback
import com.inappstory.sdk.exceptions.DataException
import com.inappstory.sdk.stories.outercallbacks.common.onboarding.OnboardingLoadCallback
import com.inappstory.sdk.stories.callbacks.OnFavoriteItemClick

class NotificationSubscribeSample : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_base_list)
        showStories()
    }

    override fun onDestroy() {
        super.onDestroy()
    }

    private fun doSmthOnShowStory(
        id: Int,
        title: String,
        tags: String,
        slidesCount: Int,
        source: SourceType
    ) {

    }

    private fun doSmthOnCloseStory(
        id: Int,
        title: String,
        tags: String,
        slidesCount: Int,
        index: Int,
        action: CloseReader,
        source: SourceType
    ) {

    }

    private fun showStories() {
        val storiesList = findViewById<StoriesList>(R.id.stories_list)
        storiesList.setAppearanceManager(AppearanceManager())
        InAppStoryManager.getInstance()
            .setShowStoryCallback { id, title, tags, slidesCount, source ->
                doSmthOnShowStory(id, title, tags, slidesCount, source)
            }
        InAppStoryManager.getInstance()
            .setCloseStoryCallback { id, title, tags, slidesCount, index, action, source ->
                doSmthOnCloseStory(id, title, tags, slidesCount, index, action, source)
            }
        val adapterCallback = false
        try {
            if (adapterCallback) {
                storiesList.setCallback(object : ListCallbackAdapter() {
                    override fun storiesLoaded(size: Int) {}
                })
            } else {
                storiesList.setCallback(object : ListCallback {
                    override fun storiesLoaded(size: Int) {}
                    override fun loadError() {}
                    override fun itemClick(
                        id: Int,
                        listIndex: Int,
                        title: String,
                        tags: String,
                        slidesCount: Int,
                        isFavoriteList: Boolean
                    ) {
                    }
                })
            }
            storiesList.loadStories()
        } catch (e: DataException) {
            e.printStackTrace()
        }
    }
}