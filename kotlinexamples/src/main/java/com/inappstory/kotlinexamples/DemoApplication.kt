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
            return "user0"
        }
    }

    interface WaitingInterface {
        fun invoke(value: String) {

        }

        fun invoke2() {}
    }

    fun invoke(value: String) {

    }

    fun myMethod() {
        getTestKey()
        getTestKey2(
            object : WaitingInterface {
                override fun invoke(value: String) {
                    //
                }
            }
        )
        val operation = ""
    }

    val lock: Any = Any()

    fun getTestKey2(wait: WaitingInterface) {
        Thread(object : Runnable {
            override fun run() {
                synchronized(lock) {
                    //long time code
                }
            }

        }).start()
        Thread(object : Runnable {
            override fun run() {
                synchronized(lock) {
                    //long time code
                }
            }
        })

    }

    fun getTestKey(): String {
        //long time code
        return ""
    }

    fun getApiKey(): String? {
        return ""
    }
}