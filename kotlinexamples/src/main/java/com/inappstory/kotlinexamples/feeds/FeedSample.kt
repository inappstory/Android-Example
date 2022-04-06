package com.inappstory.kotlinexamples.feeds

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatEditText
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.inappstory.kotlinexamples.DemoApplication
import com.inappstory.kotlinexamples.R
import com.inappstory.sdk.stories.ui.list.StoriesList
import com.inappstory.sdk.AppearanceManager
import com.inappstory.sdk.InAppStoryManager
import com.inappstory.sdk.eventbus.CsEventBus
import com.inappstory.sdk.eventbus.CsSubscribe
import com.inappstory.sdk.eventbus.CsThreadMode
import com.inappstory.sdk.exceptions.DataException
import com.inappstory.sdk.stories.events.StoriesErrorEvent
import com.inappstory.sdk.stories.outercallbacks.common.errors.ErrorCallback
import com.inappstory.sdk.stories.outercallbacks.common.errors.ErrorCallbackAdapter
import com.inappstory.sdk.stories.outercallbacks.common.onboarding.OnboardingLoadCallback
import com.inappstory.sdk.stories.outercallbacks.storieslist.ListCallback
import com.inappstory.sdk.stories.outercallbacks.storieslist.ListCallbackAdapter

class FeedSample : AppCompatActivity() {
    var appearanceManager: AppearanceManager = AppearanceManager()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_feed)
        val storiesList = findViewById<StoriesList>(R.id.stories_list)
        storiesList.setAppearanceManager(appearanceManager.csHasFavorite(false))
        findViewById<View>(R.id.show_onboarding).setOnClickListener {
            InAppStoryManager.getInstance()
                .showOnboardingStories(
                    (findViewById<View>(R.id.feed)
                            as AppCompatEditText).text.toString(),
                    this@FeedSample,
                    appearanceManager
                )
        }
        findViewById<View>(R.id.change_feed).setOnClickListener {

            storiesList.feed = (findViewById<View>(R.id.feed) as AppCompatEditText).text.toString()
            try {
                storiesList.loadStories()
            } catch (e: DataException) {
                e.printStackTrace()
            }
        }


        try {
            storiesList.loadStories()
        } catch (e: DataException) {
            e.printStackTrace()
        }
    }

}