package com.inappstory.kotlinexamples.stackfeed

import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.RecyclerView
import com.inappstory.kotlinexamples.R
import com.inappstory.sdk.stories.stackfeed.IStackFeedActions
import com.inappstory.sdk.stories.stackfeed.IStackFeedResult
import com.inappstory.sdk.stories.stackfeed.IStackStoryData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.lang.ref.WeakReference

class StackFeedResultImpl(host: StackFeedIntegrationSample) : IStackFeedResult {
    private val hostReference = WeakReference(host)

    override fun success(stackData: IStackStoryData, actions: IStackFeedActions) {
        val host = hostReference.get()
        host?.apply {
            lifecycleScope.launch(Dispatchers.Main) {
                val customList = findViewById<RecyclerView>(R.id.customList)
                customList.adapter = CustomListAdapter(actions, stackData)
            }
        }
    }

    override fun update(stackData: IStackStoryData) {
        val host = hostReference.get()
        host?.apply {
            lifecycleScope.launch(Dispatchers.Main) {
                val customList = findViewById<RecyclerView>(R.id.customList)
                (customList.adapter as CustomListAdapter).apply {
                    stackFeedData = stackData
                    notifyItemChanged(0)
                }
            }
        }

    }

    override fun error() {
        val host = hostReference.get()
        host?.apply {
            lifecycleScope.launch(Dispatchers.Main) {
                val customList = findViewById<RecyclerView>(R.id.customList)
                customList.adapter = CustomListAdapter(null, null)
            }
        }
    }
}