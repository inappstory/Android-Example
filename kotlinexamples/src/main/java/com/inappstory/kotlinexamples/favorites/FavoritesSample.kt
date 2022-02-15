package com.inappstory.kotlinexamples.favorites

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import android.widget.RelativeLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import com.inappstory.kotlinexamples.R
import com.inappstory.sdk.AppearanceManager
import com.inappstory.sdk.exceptions.DataException
import com.inappstory.sdk.imageloader.ImageLoader
import com.inappstory.sdk.stories.callbacks.OnFavoriteItemClick
import com.inappstory.sdk.stories.ui.list.StoriesList
import com.inappstory.sdk.stories.ui.views.IGetFavoriteListItem
import com.inappstory.sdk.stories.ui.views.IStoriesListItem
import com.inappstory.sdk.stories.utils.Sizes

class FavoritesSample : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_base_list)
        showStories()
    }

    private fun generateSimpleAppearanceManager(): AppearanceManager {
        return AppearanceManager().csHasFavorite(true)
    }

    private fun generateCustomAppearanceManager(): AppearanceManager {
        return AppearanceManager()
            .csHasFavorite(true)
            .csListItemInterface(object : IStoriesListItem {

                override fun getView(): View {
                    return LayoutInflater.from(this@FavoritesSample)
                        .inflate(R.layout.custom_story_list_item, null, false)
                }

                override fun getVideoView(): View? {
                    return null
                }

                override fun setId(view: View, i: Int) {}
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
            .csFavoriteListItemInterface(object : IGetFavoriteListItem {
                override fun getFavoriteItem(): View {
                    return LayoutInflater.from(this@FavoritesSample)
                        .inflate(R.layout.custom_story_list_item_favorite, null, false)
                }

                override fun bindFavoriteItem(favCell: View, backColors: List<Int>, count: Int) {
                    val imageViewLayout: RelativeLayout = favCell.findViewById(R.id.container)
                    imageViewLayout.removeAllViews()
                    val title: AppCompatTextView = favCell.findViewById(R.id.title)
                    title.text = "Favorites"
                    bindFavoriteCellImages(imageViewLayout, null, backColors, count)
                }

                override fun setImages(
                    favCell: View,
                    list: List<String>,
                    backColors: List<Int>,
                    count: Int
                ) {
                    val imageViewLayout: RelativeLayout = favCell.findViewById(R.id.container)
                    imageViewLayout.removeAllViews()
                    bindFavoriteCellImages(imageViewLayout, list, backColors, count)
                }
            })
    }

    fun bindFavoriteCellImages(
        imageViewLayout: RelativeLayout,
        list: List<String>?, backColors: List<Int>, count: Int
    ) {
        val context: Context = this@FavoritesSample
        if (count > 0) {
            val image1 = AppCompatImageView(context)
            val image2 = AppCompatImageView(context)
            val piece2: RelativeLayout.LayoutParams
            when (count) {
                1 -> {
                    image1.layoutParams = RelativeLayout.LayoutParams(
                        RelativeLayout.LayoutParams.MATCH_PARENT,
                        RelativeLayout.LayoutParams.MATCH_PARENT
                    )
                    image1.scaleType = ImageView.ScaleType.CENTER_CROP
                    showImage(list?.get(0), backColors[0], image1)
                    imageViewLayout.addView(image1)
                }
                else -> {
                    piece2 = RelativeLayout.LayoutParams(
                        Sizes.dpToPxExt(42),
                        RelativeLayout.LayoutParams.MATCH_PARENT
                    )
                    image1.layoutParams = RelativeLayout.LayoutParams(
                        Sizes.dpToPxExt(42),
                        RelativeLayout.LayoutParams.MATCH_PARENT
                    )
                    piece2.addRule(RelativeLayout.ALIGN_PARENT_RIGHT, RelativeLayout.TRUE)
                    image2.layoutParams = piece2
                    image1.scaleType = ImageView.ScaleType.CENTER_CROP
                    image2.scaleType = ImageView.ScaleType.CENTER_CROP
                    showImage(list?.get(0), backColors[0], image1)
                    showImage(list?.get(1), backColors[1], image2)
                    imageViewLayout.addView(image1)
                    imageViewLayout.addView(image2)
                }
            }
        }
    }

    private fun showStories() {
        val storiesList: StoriesList = findViewById<StoriesList>(R.id.stories_list)
        storiesList.setAppearanceManager(generateSimpleAppearanceManager())
        storiesList.setOnFavoriteItemClick {
            val intent = Intent(this@FavoritesSample, StoryFavoritesActivity::class.java)
            startActivity(intent)
        }
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