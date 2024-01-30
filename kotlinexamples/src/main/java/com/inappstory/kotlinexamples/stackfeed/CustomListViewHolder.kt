package com.inappstory.kotlinexamples.stackfeed

import android.view.View
import androidx.recyclerview.widget.RecyclerView.ViewHolder

abstract class CustomListViewHolder<T>(itemView: View) : ViewHolder(itemView) {
    abstract fun bind(bindData: T)
}