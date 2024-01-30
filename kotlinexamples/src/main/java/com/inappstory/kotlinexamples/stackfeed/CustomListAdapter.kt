package com.inappstory.kotlinexamples.stackfeed

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.inappstory.kotlinexamples.R
import com.inappstory.sdk.stories.stackfeed.IStackFeedActions
import com.inappstory.sdk.stories.stackfeed.IStackStoryData

class CustomListAdapter(
    private val stackFeedActions: IStackFeedActions?,
    stackStoryData: IStackStoryData?
) : RecyclerView.Adapter<ViewHolder>() {

    var stackFeedData = stackStoryData

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view: View
        if (stackFeedData != null && viewType == 0) {
            view = inflater.inflate(
                R.layout.custom_list_stack_feed_item,
                parent,
                false
            )
            view.setOnClickListener {
                stackFeedActions?.openReader(parent.context)
            }
            return CustomListStackFeedViewHolder(view)
        } else {
            return CustomListCommonViewHolder(
                inflater.inflate(
                    R.layout.custom_list_common_item,
                    parent,
                    false
                )
            )
        }
    }


    override fun getItemViewType(position: Int): Int {
        return position
    }

    override fun getItemCount(): Int {
        return 9 + (if (stackFeedData != null) 1 else 0)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        stackFeedData?.let {
            if (holder is CustomListStackFeedViewHolder)
                holder.bind(it)
        }
    }
}