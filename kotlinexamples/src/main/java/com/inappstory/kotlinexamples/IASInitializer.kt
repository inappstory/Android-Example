package com.inappstory.kotlinexamples

import android.content.Context
import androidx.startup.Initializer
import com.inappstory.sdk.InAppStoryManager

class IASInitializer : Initializer<InAppStoryManager> {
    override fun create(context: Context): InAppStoryManager {
        InAppStoryManager.initSDK(context, true)
        return InAppStoryManager.Builder()
            .userId(getUserId())
            .apiKey(getApiKey())
            .sandbox(true)
            .create()
    }

    override fun dependencies(): List<Class<out Initializer<*>>> {
        return emptyList()
    }

    fun getUserId(): String {
        return "user0"
    }

    fun getApiKey(): String? {
        return "HDGXt7z1WVQoaN_IzLv8KdRl5f_Ghxdo"
    }

}