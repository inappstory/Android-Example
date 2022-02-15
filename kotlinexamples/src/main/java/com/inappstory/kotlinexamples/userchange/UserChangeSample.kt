package com.inappstory.kotlinexamples.userchange

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatEditText
import com.inappstory.kotlinexamples.DemoApplication.Companion.USER_ID
import com.inappstory.kotlinexamples.R
import com.inappstory.kotlinexamples.favorites.StoryFavoritesActivity
import com.inappstory.sdk.AppearanceManager
import com.inappstory.sdk.InAppStoryManager
import com.inappstory.sdk.exceptions.DataException
import com.inappstory.sdk.stories.ui.list.StoriesList
import java.util.*

class UserChangeSample : AppCompatActivity() {
    var appearanceManager: AppearanceManager = AppearanceManager()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user)
        val newUser = UUID.randomUUID().toString().substring(0, 8)
        (findViewById<View>(R.id.userId) as AppCompatEditText).setText(newUser)
        findViewById<View>(R.id.change_user).setOnClickListener {
            try {
                InAppStoryManager.getInstance().userId = (findViewById<View>(R.id.userId) as AppCompatEditText).text.toString()
                //showStories();
            } catch (e: DataException) {
                e.printStackTrace()
            }
        }
        showStories()
    }

    override fun onDestroy() {
        try {
            InAppStoryManager.getInstance().userId = USER_ID
        } catch (e: DataException) {
            e.printStackTrace()
        }
        super.onDestroy()
    }

    private fun showStories() {
        val storiesList = findViewById<StoriesList>(R.id.stories_list)
        storiesList.setAppearanceManager(appearanceManager.csHasFavorite(true))
        storiesList.setOnFavoriteItemClick {
            val intent = Intent(this@UserChangeSample, StoryFavoritesActivity::class.java)
            startActivity(intent)
        }
        try {
            storiesList.loadStories()
        } catch (e: DataException) {
            e.printStackTrace()
        }
    }
}