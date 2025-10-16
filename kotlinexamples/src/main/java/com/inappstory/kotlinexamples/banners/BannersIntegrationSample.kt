package com.inappstory.kotlinexamples.banners

import android.annotation.SuppressLint
import android.graphics.Point
import android.graphics.Rect
import android.os.Bundle
import android.util.DisplayMetrics
import android.util.Log
import android.view.View
import android.view.WindowManager
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.OnApplyWindowInsetsListener
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.inappstory.kotlinexamples.R
import com.inappstory.sdk.InAppStoryManager
import com.inappstory.sdk.banners.BannerPlaceLoadCallback
import com.inappstory.sdk.banners.BannerPlaceLoadSettings
import com.inappstory.sdk.banners.BannerPlaceNavigationCallback
import com.inappstory.sdk.banners.ui.place.BannerPlace
import com.inappstory.sdk.stories.outercallbacks.common.reader.BannerData

class BannersIntegrationSample : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.banners_layout)
        setNavigation()
        val bannerPlace = findViewById<BannerPlace>(R.id.banner_place)
        /*InAppStoryManager.getInstance()
            ?.loadBannerPlace(BannerPlaceLoadSettings().placeId("main_screen"))
        InAppStoryManager.getInstance()
            ?.loadBannerPlace(BannerPlaceLoadSettings().placeId("main_screen_2"))*/

        /*val rv = findViewById<RecyclerView>(R.id.rvList)
        rv.layoutManager = LinearLayoutManager(this)
        rv.adapter = TestAdapter()*/
        bannerPlace.uniqueId("reference")
        bannerPlace.setPlaceId("reference")
        bannerPlace.loadBanners()
    }

    @SuppressLint("NewApi")
    private fun setNavigation() {
        val bannerPlace = findViewById<BannerPlace>(R.id.banner_place)
        val bannerIndex = findViewById<TextView>(R.id.banner_index)
        val nextBanner = findViewById<TextView>(R.id.nextBanner)
        val prevBanner = findViewById<TextView>(R.id.prevBanner)
        nextBanner.setOnClickListener {
            bannerPlace.showNext()
        }
        prevBanner.setOnClickListener {
            bannerPlace.showPrevious()
        }
        bannerPlace.loadCallback(object : BannerPlaceLoadCallback() {
            override fun bannerPlaceLoaded(
                total: Int,
                data: MutableList<BannerData>?,
                height: Int
            ) {
                bannerIndex.text = "1/$total"
            }

            override fun loadError() {
            }

            override fun bannerLoaded(p0: Int, p1: Boolean) {
            }

            override fun bannerLoadError(p0: Int, p1: Boolean) {
            }

        })
        bannerPlace.navigationCallback(object : BannerPlaceNavigationCallback {
            override fun onPageScrolled(
                current: Int,
                total: Int,
                offsetFraction: Float,
                offsetInPx: Int
            ) {

            }

            override fun onPageSelected(
                current: Int,
                total: Int
            ) {
                bannerIndex.text = "${current + 1}/$total"
            }
        })
    }
}