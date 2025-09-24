package com.inappstory.kotlinexamples.iam

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatEditText
import com.inappstory.kotlinexamples.R
import com.inappstory.kotlinexamples.simple.BasicIntegrationSample
import com.inappstory.sdk.InAppStoryManager
import com.inappstory.sdk.UseManagerInstanceCallback
import com.inappstory.sdk.inappmessage.InAppMessageLoadCallback
import com.inappstory.sdk.inappmessage.InAppMessageOpenSettings
import com.inappstory.sdk.inappmessage.InAppMessagePreloadSettings
import com.inappstory.sdk.inappmessage.InAppMessageScreenActions

class InAppMessagingSample : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_inappmessage)
        findViewById<View>(R.id.showIfLoaded).setOnClickListener {
            showIfLoaded()
        }
        findViewById<View>(R.id.show).setOnClickListener {
            showAnyway()
        }
        findViewById<View>(R.id.preload).setOnClickListener {
            preload()
        }
    }

    private fun showAnyway() {
        var id: String? = findViewById<AppCompatEditText>(R.id.iamId).text.toString()
        var event: String? = findViewById<AppCompatEditText>(R.id.eventName).text.toString()
        if (id.isNullOrEmpty()) id = null
        if (event.isNullOrEmpty()) event = null
        InAppStoryManager.useInstance(object : UseManagerInstanceCallback() {
            override fun use(manager: InAppStoryManager) {
                manager.showInAppMessage(
                    InAppMessageOpenSettings().id(id?.toInt()).showOnlyIfLoaded(false).event(event),
                    supportFragmentManager,
                    R.id.inAppMessagesContainer,
                    object : InAppMessageScreenActions {
                        override fun readerIsOpened() {}
                        override fun readerOpenError(error: String) {
                            Log.e("IAM_Error", error)
                        }

                        override fun readerIsClosed() {}
                    }
                )
            }
        })
    }

    override fun onBackPressed() {
        InAppStoryManager.getInstance()?.let {
            if (it.onBackPressed())
                return
        }
        super.onBackPressed()
    }

    private fun showIfLoaded() {
        var id: String? = findViewById<AppCompatEditText>(R.id.iamId).text.toString()
        var event: String? = findViewById<AppCompatEditText>(R.id.eventName).text.toString()
        if (id.isNullOrEmpty()) id = null
        if (event.isNullOrEmpty()) event = null
        InAppStoryManager.useInstance(object : UseManagerInstanceCallback() {
            override fun use(manager: InAppStoryManager) {
                manager.showInAppMessage(
                    InAppMessageOpenSettings().id(id?.toInt()).showOnlyIfLoaded(true).event(event),
                    supportFragmentManager,
                    R.id.inAppMessagesContainer,
                    object : InAppMessageScreenActions {
                        override fun readerIsOpened() {}
                        override fun readerOpenError(error: String) {
                            Log.e("IAM_Error", error)
                        }

                        override fun readerIsClosed() {}
                    }
                )
            }
        })

    }

    private fun preload() {
        var id: String? = findViewById<AppCompatEditText>(R.id.iamId).text.toString()
        if (id.isNullOrEmpty()) id = null
        InAppStoryManager.useInstance(object : UseManagerInstanceCallback() {
            override fun use(manager: InAppStoryManager) {
                if (id == null) {
                    manager.preloadInAppMessages(null)
                } else {
                    manager.preloadInAppMessages(
                        InAppMessagePreloadSettings().inAppMessageIds(
                            listOf(id)
                        ),
                        null
                    )
                }
            }
        })
    }
}