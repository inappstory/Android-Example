package com.inappstory.kotlinexamples.share

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.widget.RelativeLayout
import com.inappstory.kotlinexamples.R
import com.inappstory.sdk.share.IASShareData
import com.inappstory.sdk.share.IASShareManager
import com.inappstory.sdk.stories.callbacks.OverlappingContainerActions
import com.inappstory.sdk.stories.callbacks.ShareCallback
import com.inappstory.sdk.stories.utils.Sizes
import java.lang.ref.WeakReference
import java.util.HashMap

class CustomShareCallback : ShareCallback {

    private var currentViewRef: WeakReference<View>? = null

    private var actions: OverlappingContainerActions? = null

    override fun getView(
        context: Context,
        data: HashMap<String, Any>,
        actions: OverlappingContainerActions
    ): View {
        this.actions = actions;
        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val currentView = inflater.inflate(R.layout.share_layout, null).apply {
            findViewById<View>(R.id.background)?.also {
                it.setOnClickListener {
                    hideAnimation(false)
                }
                it.alpha = 0f
            }
            findViewById<View>(R.id.bottom_panel)?.translationY = Sizes.dpToPxExt(100).toFloat()
            val shareData = data["shareData"] as IASShareData?
            shareData?.let {
                findViewById<View>(R.id.tg_share).setOnClickListener {
                    share(context, shareData, actions, "org.telegram.messenger")
                }
                findViewById<View>(R.id.gmail_share).setOnClickListener {
                    share(context, shareData, actions, "com.google.android.gm")
                }
                findViewById<View>(R.id.default_share).setOnClickListener {
                    share(context, shareData, actions, null)
                }
            }
        }
        currentViewRef = WeakReference(currentView)
        return currentView
    }

    private fun showAnimation() {
        val currentView = currentViewRef?.get()
        currentView?.findViewById<View>(R.id.background)
            ?.animate()?.alpha(1f)?.duration = 500;
        currentView?.findViewById<View>(R.id.bottom_panel)
            ?.animate()?.translationY(0f)?.duration = 500;
    }

    fun hideAnimation(shared: Boolean) {
        val currentView = currentViewRef?.get()
        currentView?.findViewById<View>(R.id.background)
            ?.animate()?.alpha(0f)?.duration = 500;
        currentView?.findViewById<View>(R.id.bottom_panel)
            ?.animate()?.translationY(Sizes.dpToPxExt(100).toFloat())?.setDuration(500)
            ?.setListener(object :
                AnimatorListenerAdapter() {
                override fun onAnimationEnd(animation: Animator) {
                    super.onAnimationEnd(animation);
                    shareWithResult(shared)
                }
            });
    }

    private fun shareWithResult(result: Boolean) {
        val data = hashMapOf<String, Any>()
        data["shared"] = result;
        actions?.closeView(data);
    }

    private fun share(
        context: Context,
        data: IASShareData,
        actions: OverlappingContainerActions,
        packageName: String?
    ) {
        val shareManager = IASShareManager()
        packageName?.let {
            shareManager.shareToSpecificApp(ShareBroadcastReceiver::class.java, context, data, it)
            return
        }
        shareManager.shareDefault(ShareBroadcastReceiver::class.java, context, data)
    }

    override fun viewIsVisible(view: View?) {
        showAnimation()
    }

    override fun onBackPress(actions: OverlappingContainerActions): Boolean {
        hideAnimation(false)
        return true
    }
}