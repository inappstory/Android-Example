package com.inappstory.kotlinexamples.favorites

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.inappstory.kotlinexamples.R
import com.inappstory.sdk.AppearanceManager
import com.inappstory.sdk.InAppStoryManager
import com.inappstory.sdk.exceptions.DataException
import com.inappstory.sdk.imageloader.ImageLoader
import com.inappstory.sdk.stories.ui.list.StoriesList
import com.inappstory.sdk.stories.ui.views.IStoriesListItem

class StoryFavoritesActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fav)
        showStories()
        findViewById<View>(R.id.removeAll).setOnClickListener {
            if (InAppStoryManager.isNull()) return@setOnClickListener
            InAppStoryManager.getInstance().removeAllFavorites()
        }
    }

    private fun generateAppearanceManager(): AppearanceManager {
        return AppearanceManager()
            .csHasFavorite(true)
            .csIsDraggable(true)
            .csListItemInterface(object : IStoriesListItem {
                override fun getView(): View {
                    return LayoutInflater.from(this@StoryFavoritesActivity)
                        .inflate(R.layout.custom_story_list_item_removable, null, false)
                }

                override fun getVideoView(): View? {
                    return null
                }

                override fun setId(itemView: View, id: Int) {
                    itemView.findViewById<View>(R.id.remove).setOnClickListener { v: View? ->
                        if (InAppStoryManager.isNull()) return@setOnClickListener
                        InAppStoryManager.getInstance().removeFromFavorite(id)
                    }
                }

                override fun setTitle(itemView: View, title: String, color: Int?) {
                    (itemView.findViewById<View>(R.id.title) as AppCompatTextView).also {
                        it.text = title
                        color?.let { color ->
                            it.setTextColor(color)
                        }
                    }
                }

                override fun setImage(itemView: View, url: String, backgroundColor: Int) {
                    showImage(url, backgroundColor, itemView.findViewById(R.id.image))
                }

                override fun setHasAudio(itemView: View, hasAudio: Boolean) {}
                override fun setVideo(view: View, s: String) {}
                override fun setOpened(itemView: View, isOpened: Boolean) {
                    itemView.findViewById<View>(R.id.border).visibility =
                        if (isOpened) View.INVISIBLE else View.VISIBLE
                }
            })
    }

    private fun showStories() {
        val storiesList: StoriesList = findViewById(R.id.favList)
        storiesList.setAppearanceManager(generateAppearanceManager())
        storiesList.layoutManager = GridLayoutManager(
            this@StoryFavoritesActivity, 2,
            RecyclerView.VERTICAL, false
        )
        try {
            storiesList.loadStories()
        } catch (e: DataException) {
            e.printStackTrace()
        }
    }

    fun showImage(url: String?, backgroundColor: Int, imageView: ImageView) {
        if (url != null && url.isNotEmpty()) {
            ImageLoader.getInstance().displayImage(url, -1, imageView)
        } else {
            imageView.setBackgroundColor(backgroundColor)
        }
    }
}