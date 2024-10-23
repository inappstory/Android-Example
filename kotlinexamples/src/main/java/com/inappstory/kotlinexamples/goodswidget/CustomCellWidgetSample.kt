package com.inappstory.kotlinexamples.goodswidget

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.RecyclerView
import com.inappstory.kotlinexamples.R
import com.inappstory.sdk.AppearanceManager
import com.inappstory.sdk.InAppStoryManager
import com.inappstory.sdk.InAppStoryService
import com.inappstory.sdk.imageloader.ImageLoader
import com.inappstory.sdk.stories.ui.list.StoriesList
import com.inappstory.sdk.stories.ui.views.goodswidget.*
import java.util.ArrayList

class CustomCellWidgetSample : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_base_list)
        showStories()
    }

    private fun showStories() {
        val storiesList = findViewById<StoriesList>(R.id.stories_list)
        storiesList.appearanceManager = AppearanceManager()
        AppearanceManager.getCommonInstance().csCustomGoodsWidget(object : ICustomGoodsWidget {


            override fun getWidgetView(context: Context?): View? {
                return null;
            }

            override fun getItem(): ICustomGoodsItem? {
                return object : ICustomGoodsItem {
                    override fun getView(context: Context?): View {
                        return LayoutInflater.from(context ?: this@CustomCellWidgetSample)
                            .inflate(
                                R.layout.custom_goods_item,
                                null, false
                            )
                    }

                    override fun bindView(view: View, goodsItemData: GoodsItemData) {
                        ImageLoader.getInstance().displayImage(
                            goodsItemData.image,
                            -1,
                            view.findViewById<View>(R.id.image) as AppCompatImageView,
                            InAppStoryService.getInstance().fastCache
                        )
                        (view.findViewById<View>(R.id.title) as AppCompatTextView).text =
                            goodsItemData.title
                    }
                }
            }

            override fun getWidgetAppearance(): IGoodsWidgetAppearance? {
                return null
            }

            override fun getDecoration(): RecyclerView.ItemDecoration? {
                return null
            }

            override fun getSkus(
                widgetView: View?,
                skus: ArrayList<String>,
                callback: GetGoodsDataCallback
            ) {
                val goodsItemData: ArrayList<GoodsItemData> = ArrayList<GoodsItemData>()
                for (sku in skus) {
                    val data = GoodsItemData(
                        sku,
                        sku,
                        "desc_$sku",
                        "https://media.istockphoto.com/photos/big-and-small-picture-id172759822",
                        "10",
                        "20",
                        sku
                    )
                    goodsItemData.add(data)
                }
                callback.onSuccess(goodsItemData)
            }

            override fun onItemClick(
                widgetView: View?,
                view: View?,
                goodsItemData: GoodsItemData?,
                callback: GetGoodsDataCallback?
            ) {
                InAppStoryManager.closeStoryReader()
                Toast.makeText(
                    widgetView?.context ?: this@CustomCellWidgetSample,
                    goodsItemData.toString(), Toast.LENGTH_LONG
                ).show()
            }
        })
        storiesList.loadStories()
    }

    override fun onDestroy() {
        AppearanceManager.getCommonInstance().csCustomGoodsWidget(null)
        super.onDestroy()
    }
}