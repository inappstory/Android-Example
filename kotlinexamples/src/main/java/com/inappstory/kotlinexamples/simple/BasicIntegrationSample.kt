package com.inappstory.kotlinexamples.simple

import android.annotation.SuppressLint
import android.graphics.Point
import android.graphics.Rect
import android.os.Bundle
import android.util.DisplayMetrics
import android.util.Log
import android.view.View
import android.view.View.OnLayoutChangeListener
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.OnApplyWindowInsetsListener
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.inappstory.kotlinexamples.R
import com.inappstory.sdk.AppearanceManager
import com.inappstory.sdk.stories.ui.list.StoriesList

class BasicIntegrationSample : AppCompatActivity(), OnApplyWindowInsetsListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_base_list)
        showStories()

    }

    @SuppressLint("NewApi")
    private fun showStories() {
        val storiesList = findViewById<StoriesList>(R.id.stories_list)


        storiesList.setAppearanceManager(AppearanceManager())
        ViewCompat.setOnApplyWindowInsetsListener(window.decorView, this)
        storiesList.loadStories()
    }

    @SuppressLint("NewApi")
    override fun onApplyWindowInsets(v: View, insets: WindowInsetsCompat): WindowInsetsCompat {
        insets.toWindowInsets()?.let {
            val c1 = it.systemWindowInsetBottom
            val c2 = it.systemWindowInsetTop
            val c3 = it.stableInsetBottom
            val c4 = it.stableInsetTop
            val outMetrics1 = DisplayMetrics()
            val outMetrics2 = DisplayMetrics()
            // val c8 = it.stableInsetBottom
            val display =
                (v.context.getSystemService(WINDOW_SERVICE) as WindowManager).defaultDisplay
            val size = Point()
            display.getSize(size)
            val rect = Rect()
            v.getWindowVisibleDisplayFrame(rect)
            display.getMetrics(outMetrics1)
            this.display.getRealMetrics(outMetrics2)
            Log.e("insetFrame", c1.toString())
        }
        return insets
    }


}