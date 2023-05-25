package com.inappstory.kotlinexamples.notification

import android.os.Bundle
import android.view.View
import android.widget.FrameLayout
import androidx.appcompat.app.AppCompatActivity
import com.inappstory.kotlinexamples.R
import com.inappstory.kotlinexamples.utils.ListShimmerView
import com.inappstory.sdk.stories.ui.list.StoriesList
import com.inappstory.sdk.AppearanceManager
import com.inappstory.sdk.InAppStoryManager
import com.inappstory.sdk.stories.outercallbacks.storieslist.ListCallbackAdapter
import com.inappstory.sdk.stories.outercallbacks.storieslist.ListCallback
import com.inappstory.sdk.stories.outercallbacks.common.reader.*
import com.inappstory.sdk.stories.utils.Sizes
import kotlinx.coroutines.*

class NotificationSubscribeSample : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shimmer_list)
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
        source: SourceType,
        showStoryAction: ShowStoryAction
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
        val shimmer = findViewById<ListShimmerView>(R.id.shimmer)
        val shimmerLayout = findViewById<FrameLayout>(R.id.shimmerLayout)
        shimmer.imageWidth = Sizes.dpToPxExt(120).toFloat()
        shimmerLayout.visibility = View.VISIBLE
        storiesList.appearanceManager = AppearanceManager()
        InAppStoryManager.getInstance()
            .setShowStoryCallback { id, title, tags, slidesCount, source, showStoryAction ->
                doSmthOnShowStory(id, title, tags, slidesCount, source, showStoryAction)
            }
        InAppStoryManager.getInstance()
            .setCloseStoryCallback { id, title, tags, slidesCount, index, action, source ->
                doSmthOnCloseStory(id, title, tags, slidesCount, index, action, source)
            }
        val adapterCallback = false
        if (adapterCallback) {
            storiesList.setCallback(object : ListCallbackAdapter() {
                override fun storiesLoaded(size: Int, feed: String) {
                    hideShimmer(shimmerLayout)
                }

                override fun loadError(feed: String) {
                    hideShimmer(shimmerLayout)
                }

            })
        } else {
            storiesList.setCallback(object : ListCallback {
                override fun storiesLoaded(
                    size: Int,
                    feed: String
                ) {

                    hideShimmer(shimmerLayout)
                }

                override fun storiesUpdated(
                    size: Int,
                    feed: String
                ) {

                }

                override fun loadError(feed: String) {
                    hideShimmer(shimmerLayout)
                }

                override fun itemClick(
                    id: Int,
                    listIndex: Int,
                    title: String,
                    tags: String,
                    slidesCount: Int,
                    isFavoriteList: Boolean,
                    feed: String
                ) {
                }
            })
        }
        storiesList.loadStories()
    }

    fun hideShimmer(layout: FrameLayout) {
        GlobalScope.launch {
            delay(500)
            withContext(Dispatchers.Main) {
                layout.visibility = View.GONE
            }
        }
    }
}