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
import com.inappstory.sdk.stories.outercallbacks.common.reader.ShowStoryCallback
import com.inappstory.sdk.stories.outercallbacks.common.reader.SourceType
import com.inappstory.sdk.stories.outercallbacks.common.reader.CloseStoryCallback
import com.inappstory.sdk.stories.outercallbacks.common.reader.CloseReader
import com.inappstory.sdk.stories.outercallbacks.storieslist.ListCallbackAdapter
import com.inappstory.sdk.stories.outercallbacks.storieslist.ListCallback
import com.inappstory.sdk.exceptions.DataException
import com.inappstory.sdk.stories.outercallbacks.common.onboarding.OnboardingLoadCallback
import com.inappstory.sdk.stories.callbacks.OnFavoriteItemClick
import com.inappstory.sdk.stories.utils.Sizes
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

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
        val shimmer = findViewById<ListShimmerView>(R.id.shimmer)
        val shimmerLayout = findViewById<FrameLayout>(R.id.shimmerLayout)
        shimmer.imageWidth = Sizes.dpToPxExt(120).toFloat()
        shimmerLayout.visibility = View.VISIBLE
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
        } catch (e: DataException) {
            e.printStackTrace()
        }
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
