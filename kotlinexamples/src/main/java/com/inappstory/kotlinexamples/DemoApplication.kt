package com.inappstory.kotlinexamples

import android.app.Application
import android.util.Log
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

class DemoApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        try {
            InAppStoryManager.Builder()
                .userId(getUserId())
                //.testKey(getTestKey())
                .context(applicationContext)
                .create()
        } catch (e: DataException) {
            e.printStackTrace()
            return
        }

        InAppStoryManager.logger = object : InAppStoryManager.IASLogger {
            override fun showELog(p0: String?, p1: String?) {
                Log.d(p0, p1.orEmpty())
            }

            override fun showDLog(p0: String?, p1: String?) {
                Log.d(p0, p1.orEmpty())
            }
        }
    }

    companion object USER {
        fun getUserId(): String {
            TODO("Not implemented yet")
        }
    }


    fun getTestKey(): String? {
        TODO("Not implemented yet")
    }
}