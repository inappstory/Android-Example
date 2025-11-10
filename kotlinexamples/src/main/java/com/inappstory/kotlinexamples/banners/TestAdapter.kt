package com.inappstory.kotlinexamples.banners

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.inappstory.kotlinexamples.R

class TestAdapter : RecyclerView.Adapter<ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return if (viewType < 3) {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.banners_layout, parent, false)
            BannerViewHolder(view)
        } else {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.empty_item_layout, parent, false)
            EmptyViewHolder(view)
        }
    }

    override fun getItemViewType(position: Int): Int {
        return position
    }

    override fun getItemCount(): Int {
        return 20
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        if (holder is BannerViewHolder) {
            when (position) {
                0 ->
                    holder.bind("main_screen")
                1 ->
                    holder.bind("main_screen_2")
                2 ->
                    holder.bind("main_screen_3")
            }
        }
    }
}