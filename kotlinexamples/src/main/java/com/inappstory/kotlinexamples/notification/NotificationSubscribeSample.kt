package com.inappstory.kotlinexamples.notification

import android.os.Bundle
import android.view.View
import android.widget.FrameLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.inappstory.kotlinexamples.R
import com.inappstory.kotlinexamples.utils.ListShimmerView
import com.inappstory.sdk.stories.ui.list.StoriesList
import com.inappstory.sdk.AppearanceManager
import com.inappstory.sdk.InAppStoryManager
import com.inappstory.sdk.stories.outercallbacks.storieslist.ListCallbackAdapter
import com.inappstory.sdk.stories.outercallbacks.storieslist.ListCallback
import com.inappstory.sdk.stories.outercallbacks.common.reader.*
import com.inappstory.sdk.stories.outercallbacks.storieslist.ListScrollCallback
import com.inappstory.sdk.stories.outercallbacks.storieslist.ListScrollCallbackAdapter
import com.inappstory.sdk.stories.ui.list.ShownStoriesListItem
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

    private fun showStories() {
        val storiesList = findViewById<StoriesList>(R.id.stories_list)
        val shimmer = findViewById<ListShimmerView>(R.id.shimmer)
        val shimmerLayout = findViewById<FrameLayout>(R.id.shimmerLayout)
        shimmer.imageWidth = Sizes.dpToPxExt(120).toFloat()
        shimmerLayout.visibility = View.VISIBLE
        storiesList.appearanceManager = AppearanceManager()
        InAppStoryManager.getInstance()
            .setShowStoryCallback { storyData, action -> }
        InAppStoryManager.getInstance()
            .setCloseStoryCallback { storyData, closeReader -> }
        val adapterCallback = false
        if (adapterCallback) {
            storiesList.setCallback(object : ListCallbackAdapter() {
                override fun storiesLoaded(size: Int,
                                           feed: String?,
                                           storyData: MutableList<StoryData>?
                ) {
                    hideShimmer(shimmerLayout)
                }

                override fun loadError(feed: String) {
                    hideShimmer(shimmerLayout)
                }

            })
            storiesList.setScrollCallback(object : ListScrollCallbackAdapter() {
                override fun onVisibleAreaUpdated(items: MutableList<ShownStoriesListItem>?) {

                }
            })
        } else {
            storiesList.setCallback(object : ListCallback {
                override fun storiesLoaded(size: Int, feed: String?, p2: MutableList<StoryData>?) {

                    hideShimmer(shimmerLayout)
                }

                override fun storiesUpdated(size: Int, feed: String?, p2: MutableList<StoryData>?) {

                }

                override fun loadError(feed: String) {
                    hideShimmer(shimmerLayout)
                }

                override fun itemClick(
                    storyData: StoryData?,
                    index: Int
                ) {
                }
            })
            storiesList.setScrollCallback(object : ListScrollCallback {
                override fun scrollStart() {

                }

                override fun onVisibleAreaUpdated(items: MutableList<ShownStoriesListItem>?) {

                }

                override fun scrollEnd() {

                }
            })
        }
        storiesList.loadStories()
    }

    fun hideShimmer(layout: FrameLayout) {
        lifecycleScope.launch {
            delay(500)
            withContext(Dispatchers.Main) {
                layout.visibility = View.GONE
            }
        }
    }
}