package com.inappstory.kotlinexamples.custom

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatTextView
import com.inappstory.kotlinexamples.ImageLoader
import com.inappstory.kotlinexamples.R
import com.inappstory.sdk.AppearanceManager
import com.inappstory.sdk.exceptions.DataException
import com.inappstory.sdk.stories.ui.list.StoriesList
import com.inappstory.sdk.stories.ui.views.IStoriesListItem
import java.io.File

class CustomCellSample : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_base_list)
        showStories()
    }

    private fun generateAppearanceManager(): AppearanceManager {
        return AppearanceManager()
            .csListItemInterface(object : IStoriesListItem {
                override fun getView(): View {
                    return LayoutInflater.from(this@CustomCellSample)
                        .inflate(R.layout.custom_story_list_item, null, false)
                }

                override fun getVideoView(): View? {
                    return null
                }

                override fun setId(view: View, i: Int) {}
                override fun setTitle(itemView: View, title: String, color: Int) {
                    (itemView.findViewById<View>(R.id.title) as AppCompatTextView).text =
                        title
                    if (color != null) {
                        (itemView.findViewById<View>(R.id.title) as AppCompatTextView).setTextColor(
                            color
                        )
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
        val storiesList = findViewById<StoriesList>(R.id.stories_list)
        storiesList.setAppearanceManager(generateAppearanceManager())
        try {
            storiesList.loadStories()
        } catch (e: DataException) {
            e.printStackTrace()
        }
    }

    fun showImage(url: String?, backgroundColor: Int, imageView: ImageView) {
        if (!url.isNullOrEmpty()) {
            val bmp =  ImageLoader.decodeFile(File(url))
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