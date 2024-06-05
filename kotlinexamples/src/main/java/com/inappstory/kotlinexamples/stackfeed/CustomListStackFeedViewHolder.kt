package com.inappstory.kotlinexamples.stackfeed

import android.graphics.Color
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.widget.AppCompatImageView
import com.inappstory.kotlinexamples.ImageLoader
import com.inappstory.kotlinexamples.R
import com.inappstory.sdk.stories.cache.Downloader
import com.inappstory.sdk.stories.stackfeed.IStackStoryData
import com.inappstory.sdk.stories.utils.RunnableCallback
import java.io.File

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
            Downloader.downloadFileAndSendToInterface(url, object : RunnableCallback {
                override fun run(path: String?) {
                    if (path == null) return
                    val bmp =  ImageLoader.decodeFile(File(path))
                    if (bmp != null) {
                        imageView.background = null;
                        imageView.setImageBitmap(bmp);
                        imageView.invalidate()
                    }
                }
                override fun error() {
                }
            })

        } else {

        }

        imageView.setImageBitmap(null);
        imageView.setBackgroundColor(backgroundColor);
        imageView.invalidate();
    }
}