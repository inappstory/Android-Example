package com.inappstory.kotlinexamples.stackfeed

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.inappstory.kotlinexamples.R
import com.inappstory.sdk.AppearanceManager
import com.inappstory.sdk.InAppStoryManager
import com.inappstory.sdk.stories.stackfeed.IStackFeedActions
import com.inappstory.sdk.stories.stackfeed.IStackFeedResult
import com.inappstory.sdk.stories.stackfeed.IStackStoryData
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
        customList.layoutManager = LinearLayoutManager(this)
        InAppStoryManager.getInstance().getStackFeed(
            null,
            null,
            emptyList(),
            AppearanceManager(),
            object : IStackFeedResult {
                override fun success(stackData: IStackStoryData, actions: IStackFeedActions) {
                    lifecycleScope.launch(Dispatchers.Main) {
                        customList.adapter = CustomListAdapter(actions, stackData)
                    }
                }

                override fun update(stackData: IStackStoryData) {
                    lifecycleScope.launch(Dispatchers.Main) {
                        (customList.adapter as CustomListAdapter).apply {
                            stackFeedData = stackData
                            notifyItemChanged(0)
                        }
                    }
                }

                override fun error() {
                    lifecycleScope.launch(Dispatchers.Main) {
                        customList.adapter = CustomListAdapter(null, null)
                    }
                }
            }
        )
    }

}