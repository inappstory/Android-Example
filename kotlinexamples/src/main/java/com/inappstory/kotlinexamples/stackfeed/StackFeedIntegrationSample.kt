package com.inappstory.kotlinexamples.stackfeed

import android.graphics.Rect
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ItemDecoration
import com.inappstory.kotlinexamples.R
import com.inappstory.sdk.AppearanceManager
import com.inappstory.sdk.InAppStoryManager
import com.inappstory.sdk.stories.stackfeed.IStackFeedActions
import com.inappstory.sdk.stories.stackfeed.IStackFeedResult
import com.inappstory.sdk.stories.stackfeed.IStackStoryData
import com.inappstory.sdk.stories.utils.Sizes
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class StackFeedIntegrationSample : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_with_stack_feed)
        showStories()
    }

    private fun showStories() {
        val customList = findViewById<RecyclerView>(R.id.customList)
        customList?.let { list->
            list.layoutManager = LinearLayoutManager(this, RecyclerView.HORIZONTAL, false)
            list.addItemDecoration(object : ItemDecoration() {
                override fun getItemOffsets(
                    outRect: Rect,
                    view: View,
                    parent: RecyclerView,
                    state: RecyclerView.State
                ) {
                    outRect.set(
                        Sizes.dpFloatToPxExt(4f, this@StackFeedIntegrationSample),
                        0,
                        Sizes.dpFloatToPxExt(4f, this@StackFeedIntegrationSample),
                        0
                    )
                }
            })
            InAppStoryManager.getInstance().getStackFeed(
                null,
                null,
                emptyList(),
                AppearanceManager(),
                StackFeedResultImpl(this)
            )
        }

    }

}