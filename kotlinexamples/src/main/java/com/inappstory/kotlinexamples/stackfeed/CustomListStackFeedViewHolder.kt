package com.inappstory.kotlinexamples.stackfeed

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.widget.AppCompatImageView
import com.inappstory.kotlinexamples.ImageLoader
import com.inappstory.kotlinexamples.R
import com.inappstory.sdk.stories.stackfeed.IStackStoryData
import java.io.File

class CustomListStackFeedViewHolder(itemView: View) :
    CustomListViewHolder<IStackStoryData>(itemView) {

    private val imageView: AppCompatImageView = itemView.findViewById(R.id.image)
    private val title: TextView = itemView.findViewById(R.id.title)
    private val statuses: TextView = itemView.findViewById(R.id.statuses)


    override fun bind(bindData: IStackStoryData) {
        title.setTextColor(bindData.titleColor())
        title.text = bindData.title()
        statuses.text = bindData.stackFeedOpenedStatuses().joinToString(separator = ",")
        bindData.cover().let {
            showImage(it.imageCoverPath(), it.backgroundColor(), imageView)
        }
    }

    private fun showImage(url: String?, backgroundColor: Int, imageView: ImageView) {
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