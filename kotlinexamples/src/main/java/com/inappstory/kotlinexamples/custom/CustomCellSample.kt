package com.inappstory.kotlinexamples.custom

import android.content.Context
import android.os.Bundle
import android.util.SizeF
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import com.inappstory.kotlinexamples.ImageLoader
import com.inappstory.kotlinexamples.R
import com.inappstory.sdk.AppearanceManager
import com.inappstory.sdk.CustomCloseIconInterface
import com.inappstory.sdk.CustomDislikeIconInterface
import com.inappstory.sdk.CustomFavoriteIconInterface
import com.inappstory.sdk.CustomLikeIconInterface
import com.inappstory.sdk.CustomRefreshIconInterface
import com.inappstory.sdk.CustomShareIconInterface
import com.inappstory.sdk.CustomSoundIconInterface
import com.inappstory.sdk.ICustomIconState
import com.inappstory.sdk.stories.ui.list.StoriesList
import com.inappstory.sdk.stories.ui.video.VideoPlayer
import com.inappstory.sdk.stories.ui.views.IStoriesListItem
import java.io.File

class CustomCellSample : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_base_list)
        showStories()
    }


    private fun generateAppearanceManager(): AppearanceManager {
        AppearanceManager.getCommonInstance().csCustomIcons(
            object : CustomFavoriteIconInterface() {
                override fun createIconView(context: Context, p1: SizeF?): View {
                    val customButton = CustomButton(context);
                    customButton.setImage(R.drawable.ic_stories_status_favorite);
                    return customButton;
                }

                override fun updateState(iconView: View, state: ICustomIconState) {
                    if (iconView is CustomButton) {
                        iconView.setActive(state.active());
                        iconView.setEnabled(state.enabled());
                    }
                }

                override fun touchEvent(iconView: View, event: MotionEvent?) {
                    if (iconView is CustomButton) {
                        iconView.touch(event)
                    }
                    super.touchEvent(iconView, event)
                }
            },
            object : CustomLikeIconInterface() {
                override fun createIconView(context: Context, p1: SizeF?): View {
                    val customButton = CustomButton(context);
                    customButton.setImage(R.drawable.ic_stories_status_like);
                    return customButton;
                }

                override fun updateState(iconView: View, state: ICustomIconState) {
                    if (iconView is CustomButton) {
                        iconView.setActive(state.active());
                        iconView.setEnabled(state.enabled());
                    }
                }

                override fun touchEvent(iconView: View, event: MotionEvent?) {
                    if (iconView is CustomButton) {
                        iconView.touch(event)
                    }
                    super.touchEvent(iconView, event)
                }
            },
            object : CustomDislikeIconInterface() {
                override fun createIconView(context: Context, p1: SizeF?): View {
                    val customButton = CustomButton(context);
                    customButton.setImage(R.drawable.ic_stories_status_dislike);
                    return customButton;
                }

                override fun updateState(iconView: View, state: ICustomIconState) {
                    if (iconView is CustomButton) {
                        iconView.setActive(state.active());
                        iconView.setEnabled(state.enabled());
                    }
                }

                override fun touchEvent(iconView: View, event: MotionEvent?) {
                    if (iconView is CustomButton) {
                        iconView.touch(event)
                    }
                    super.touchEvent(iconView, event)
                }
            },
            object : CustomShareIconInterface() {
                override fun createIconView(context: Context, p1: SizeF?): View {
                    val customButton = CustomButton(context);
                    customButton.setImage(R.drawable.ic_share_status);
                    return customButton;
                }

                override fun updateState(iconView: View, state: ICustomIconState) {
                    if (iconView is CustomButton) {
                        iconView.setActive(state.active());
                        iconView.setEnabled(state.enabled());
                    }
                }

                override fun touchEvent(iconView: View, event: MotionEvent?) {
                    if (iconView is CustomButton) {
                        iconView.touch(event)
                    }
                    super.touchEvent(iconView, event)
                }
            },
            object : CustomSoundIconInterface() {
                override fun createIconView(context: Context, p1: SizeF?): View {
                    val customButton = CustomButton(context);
                    customButton.setImage(R.drawable.ic_stories_status_sound);
                    return customButton;
                }

                override fun updateState(iconView: View, state: ICustomIconState) {
                    if (iconView is CustomButton) {
                        iconView.setActive(state.active());
                        iconView.setEnabled(state.enabled());
                    }
                }

                override fun touchEvent(iconView: View, event: MotionEvent?) {
                    if (iconView is CustomButton) {
                        iconView.touch(event)
                    }
                    super.touchEvent(iconView, event)
                }
            },
            object : CustomCloseIconInterface() {
                override fun createIconView(context: Context, p1: SizeF?): View {
                    val customButton = CustomButton(context);
                    customButton.setImage(R.drawable.ic_stories_close);
                    return customButton;
                }

                override fun touchEvent(iconView: View, event: MotionEvent?) {
                    if (iconView is CustomButton) {
                        iconView.touch(event)
                    }
                    super.touchEvent(iconView, event)
                }
            },
            object : CustomRefreshIconInterface() {
                override fun createIconView(context: Context, p1: SizeF?): View {
                    val customButton = CustomButton(context);
                    customButton.setImage(R.drawable.ic_refresh);
                    return customButton;
                }

                override fun touchEvent(iconView: View, event: MotionEvent?) {
                    if (iconView is CustomButton) {
                        iconView.touch(event)
                    }
                    super.touchEvent(iconView, event)
                }
            }
        )
        return AppearanceManager()
            .csHasFavorite(true)
            .csHasShare(true)
            .csHasLike(true)
            .csListItemInterface(object : IStoriesListItem {
                override fun getView(): View {
                    return LayoutInflater.from(this@CustomCellSample)
                        .inflate(R.layout.custom_story_list_item, null, false)
                }

                override fun getVideoView(): View? {
                    return LayoutInflater.from(this@CustomCellSample)
                        .inflate(R.layout.custom_story_list_item, null, false)
                }

                override fun setId(view: View, i: Int) {

                }

                override fun setTitle(itemView: View, title: String, color: Int?) {
                    (itemView.findViewById<View>(R.id.title) as AppCompatTextView).run {
                        text = title
                        color?.let { color ->
                            setTextColor(
                                color
                            )
                        }
                    }
                }

                override fun setImage(itemView: View, imagePath: String?, backgroundColor: Int) {
                    showImage(imagePath, backgroundColor, itemView.findViewById(R.id.image))
                }

                override fun setHasAudio(itemView: View, hasAudio: Boolean) {
                    (itemView.findViewById<View>(R.id.soundIcon) as AppCompatImageView).run {
                        visibility = if (hasAudio)
                            View.VISIBLE
                        else
                            View.GONE
                    }
                }

                override fun setVideo(itemView: View, videoPath: String?) {
                    (itemView.findViewById<View>(R.id.videoCover) as VideoPlayer).run {
                        visibility = View.VISIBLE
                        loadVideo(videoPath)
                    }
                }

                override fun setOpened(itemView: View, isOpened: Boolean) {
                    itemView.findViewById<View>(R.id.border).visibility =
                        if (isOpened) View.INVISIBLE else View.VISIBLE
                }
            })
    }

    private fun showStories() {
        val storiesList = findViewById<StoriesList>(R.id.stories_list)
        storiesList.setAppearanceManager(generateAppearanceManager())
        storiesList.loadStories()
    }

    fun showImage(imagePath: String?, backgroundColor: Int, imageView: ImageView) {
        if (!imagePath.isNullOrEmpty()) {
            val bmp = ImageLoader.decodeFile(File(imagePath), imageView.context)
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