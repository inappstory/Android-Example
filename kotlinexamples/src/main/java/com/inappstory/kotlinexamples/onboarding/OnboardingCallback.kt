package com.inappstory.kotlinexamples.onboarding

import android.content.Context
import android.widget.Toast
import com.inappstory.sdk.stories.outercallbacks.common.onboarding.OnboardingLoadCallback
import java.lang.ref.WeakReference

class OnboardingCallback(context: Context) : OnboardingLoadCallback {
    private val contextRef = WeakReference(context)


    override fun onboardingLoadSuccess(size: Int, feed: String?) {
        contextRef.get()?.let {
            Toast.makeText(
                it,
                "onboarding load $size $feed",
                Toast.LENGTH_LONG
            ).show()
        }
    }

    override fun onboardingLoadError(feed: String?, reason: String?) {
        contextRef.get()?.let {
            Toast.makeText(
                it,
                "onboarding load error $feed: $reason",
                Toast.LENGTH_LONG
            ).show()
        }
    }
}