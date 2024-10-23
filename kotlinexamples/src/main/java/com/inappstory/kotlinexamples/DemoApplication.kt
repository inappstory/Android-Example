package com.inappstory.kotlinexamples

import android.app.Application
import android.util.Log
import com.inappstory.kotlinexamples.share.CustomShareSample
import com.inappstory.sdk.InAppStoryManager

class DemoApplication : Application() {
    var shareListener: CustomShareSample? = null

    override fun onCreate() {
        super.onCreate()
        InAppStoryManager.initSDK(applicationContext)
        InAppStoryManager.Builder()
            .userId(getUserId())
            .apiKey(getApiKey())
            .sandbox(true)
            //.testKey(getTestKey())
            //.context(applicationContext)
            .create()

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
            return "testUserId2112"
        }
    }


    fun getTestKey(): String? {
        return ""
    }

    fun getApiKey(): String? {
        return "HDGXt7z1WVQoaN_IzLv8KdRl5f_Ghxdo"
    }
}