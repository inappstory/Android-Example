package com.inappstory.kotlinexamples.share

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.inappstory.kotlinexamples.DemoApplication
import com.inappstory.kotlinexamples.R
import com.inappstory.sdk.AppearanceManager
import com.inappstory.sdk.InAppStoryManager
import com.inappstory.sdk.stories.ui.list.StoriesList

class CustomShareSample : AppCompatActivity() {

    val shareCallback: CustomShareCallback = CustomShareCallback()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (applicationContext as DemoApplication).shareListener = this
        setContentView(R.layout.activity_base_list)
        showStories()
    }

    private fun showStories() {
        val storiesList = findViewById<StoriesList>(R.id.stories_list)
        storiesList.appearanceManager = AppearanceManager()
        storiesList.loadStories()
    }

    override fun onStart() {
        super.onStart()
        InAppStoryManager.getInstance()?.setShareCallback(shareCallback)
    }

    override fun onStop() {
        InAppStoryManager.getInstance()?.setShareCallback(null)
        super.onStop()
    }

    override fun onDestroy() {
        (applicationContext as DemoApplication).apply {
            if (shareListener == this@CustomShareSample)
                shareListener = null
        }
        super.onDestroy()
    }

}