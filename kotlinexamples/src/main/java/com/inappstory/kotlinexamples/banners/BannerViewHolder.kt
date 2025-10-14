package com.inappstory.kotlinexamples.banners

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.inappstory.kotlinexamples.R
import com.inappstory.sdk.banners.BannerPlaceLoadCallback
import com.inappstory.sdk.banners.BannerPlaceNavigationCallback
import com.inappstory.sdk.banners.ui.place.BannerPlace
import com.inappstory.sdk.stories.outercallbacks.common.reader.BannerData

class BannerViewHolder(itemView: View) : ViewHolder(itemView) {
    val bannerPlace: BannerPlace = itemView.findViewById(R.id.banner_place)
    val bannerIndex = itemView.findViewById<TextView>(R.id.banner_index)
    val nextBanner = itemView.findViewById<TextView>(R.id.nextBanner)
    val prevBanner = itemView.findViewById<TextView>(R.id.prevBanner)

    fun bind(placeId: String) {
        //bannerPlace.setPlaceId("")
        bannerPlace.setPlaceId(placeId)

      //  bannerPlace.setPlaceId("reference")
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