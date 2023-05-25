package com.inappstory.kotlinexamples.share

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.inappstory.kotlinexamples.DemoApplication

class ShareBroadcastReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        (context?.applicationContext as DemoApplication?)?.shareListener?.apply {
            shareCallback.hideAnimation(true)
        }
    }
}