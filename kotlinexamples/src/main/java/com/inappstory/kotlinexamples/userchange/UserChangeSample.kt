package com.inappstory.kotlinexamples.userchange

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatEditText
import com.inappstory.kotlinexamples.DemoApplication
import com.inappstory.kotlinexamples.R
import com.inappstory.kotlinexamples.favorites.StoryFavoritesActivity
import com.inappstory.sdk.AppearanceManager
import com.inappstory.sdk.InAppStoryManager
import com.inappstory.sdk.stories.outercallbacks.common.errors.ErrorCallbackAdapter
import com.inappstory.sdk.stories.ui.list.StoriesList
import java.util.*

class UserChangeSample : AppCompatActivity() {
    private var appearanceManager: AppearanceManager = AppearanceManager()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user)
        InAppStoryManager.getInstance().setErrorCallback(object : ErrorCallbackAdapter() {
            override fun sessionError() {
                runOnUiThread {
                    Toast.makeText(
                        this@UserChangeSample,
                        "Can't open session",
                        Toast.LENGTH_LONG
                    ).show()
                }
            }
        })

        findViewById<View>(R.id.change_user).setOnClickListener {
            var userId: String? =
                (findViewById<View>(R.id.userId) as AppCompatEditText).text.toString()
            if (userId.isNullOrEmpty()) userId = null
            var userSign: String? =
                (findViewById<View>(R.id.userSign) as AppCompatEditText).text.toString()
            if (userSign.isNullOrEmpty()) userSign = null
            userId?.let {
                InAppStoryManager.getInstance().setUserId(it, userSign)
            }
        }
        showStories()
    }

    override fun onDestroy() {
        InAppStoryManager.getInstance().setUserId(DemoApplication.getUserId(), null)
        super.onDestroy()
    }

    private fun showStories() {
        val storiesList = findViewById<StoriesList>(R.id.stories_list)
        storiesList.setAppearanceManager(appearanceManager.csHasFavorite(true))
        storiesList.setOnFavoriteItemClick {
            val intent = Intent(this@UserChangeSample, StoryFavoritesActivity::class.java)
            startActivity(intent)
        }
        storiesList.loadStories()
    }
}