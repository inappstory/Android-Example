package com.inappstory.kotlinexamples

import android.app.Application
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
                .userId("")
                .sandbox(false) // .testKey("vyOQr1M9A42CyoAic6lgJupiorcI-0Fp")
                .context(applicationContext)
                .create()
        } catch (e: DataException) {
            e.printStackTrace()
            return
        }
    }

    companion object {
        const val USER_ID = "cs_default_user1"
    }
}