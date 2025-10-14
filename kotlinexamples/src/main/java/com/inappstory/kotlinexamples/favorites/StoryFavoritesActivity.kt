package com.inappstory.kotlinexamples.favorites

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.inappstory.kotlinexamples.ImageLoader
import com.inappstory.kotlinexamples.R
import com.inappstory.sdk.AppearanceManager
import com.inappstory.sdk.InAppStoryManager
import com.inappstory.sdk.UseManagerInstanceCallback
import com.inappstory.sdk.stories.ui.list.StoriesList
import com.inappstory.sdk.stories.ui.views.IStoriesListItem
import java.io.File

class StoryFavoritesActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fav)
        showStories()
        findViewById<View>(R.id.removeAll).setOnClickListener {
            InAppStoryManager.getInstance()?.removeAllFavorites()
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
                        InAppStoryManager.getInstance()?.removeFromFavorite(id)
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

                override fun setImage(itemView: View, url: String?, backgroundColor: Int) {
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

        storiesList.loadStories()
    }

    fun showImage(url: String?, backgroundColor: Int, imageView: ImageView) {
        if (!url.isNullOrEmpty()) {
            val bmp =  ImageLoader.decodeFile(File(url), imageView.context)
            if (bmp == null) {
                imageView.setBackgroundColor(backgroundColor);
            } else {
                imageView.setImageBitmap(bmp);
            }
        } else {
            imageView.setBackgroundColor(backgroundColor)
        }
    }


}