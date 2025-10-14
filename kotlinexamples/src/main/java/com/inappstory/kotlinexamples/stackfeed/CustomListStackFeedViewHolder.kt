package com.inappstory.kotlinexamples.stackfeed

import android.app.Activity
import android.graphics.Color
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.widget.AppCompatImageView
import com.inappstory.kotlinexamples.R
import com.inappstory.sdk.imageloader.CustomFileLoader
import com.inappstory.sdk.stories.stackfeed.IStackStoryData

class CustomListStackFeedViewHolder(itemView: View) :
    CustomListViewHolder<IStackStoryData>(itemView) {

    private val imageView: AppCompatImageView = itemView.findViewById(R.id.image)
    private val title: TextView = itemView.findViewById(R.id.title)
    private val statuses: StackFeedBorder = itemView.findViewById(R.id.statuses)

    init {
        statuses.setColors(Color.GRAY, Color.GREEN);
    }

    override fun bind(bindData: IStackStoryData) {
        title.setTextColor(bindData.titleColor())
        statuses.setStatuses(bindData.stackFeedOpenedStatuses())
        bindData.cover().let {
            showImage(it.imageCoverPath() ?: it.feedCoverPath(), it.backgroundColor(), imageView)
        }
    }

    private fun showImage(url: String?, backgroundColor: Int, imageView: ImageView) {

        if (!url.isNullOrEmpty()) {
            CustomFileLoader().getBitmapFromUrl(url) {
                (imageView.context as Activity).runOnUiThread {
                    imageView.background = null;
                    imageView.setImageBitmap(it);
                    imageView.invalidate()
                }
            }
        }
        imageView.setImageBitmap(null);
        imageView.setBackgroundColor(backgroundColor);
        imageView.invalidate();
    }
}