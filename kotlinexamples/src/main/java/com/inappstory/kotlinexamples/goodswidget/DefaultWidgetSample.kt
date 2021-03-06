package com.inappstory.kotlinexamples.goodswidget

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.inappstory.kotlinexamples.R
import com.inappstory.sdk.AppearanceManager
import com.inappstory.sdk.InAppStoryManager
import com.inappstory.sdk.exceptions.DataException
import com.inappstory.sdk.stories.ui.list.StoriesList
import com.inappstory.sdk.stories.ui.views.goodswidget.*
import java.util.ArrayList

class DefaultWidgetSample : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_base_list)
        showStories()
    }

    private fun showStories() {
        val storiesList = findViewById<StoriesList>(R.id.stories_list)
        storiesList.setAppearanceManager(AppearanceManager())
        AppearanceManager.getCommonInstance().csCustomGoodsWidget(object : ICustomGoodsWidget {
            override fun getWidgetView(): View? {
                return null;
            }

            override fun getItem(): ICustomGoodsItem? {
                return null;
            }

            override fun getWidgetAppearance(): IGoodsWidgetAppearance? {
                return null;
            }

            override fun getDecoration(): RecyclerView.ItemDecoration? {
                return null;
            }

            public override fun getSkus(skus: ArrayList<String>, callback: GetGoodsDataCallback) {
                val goodsItemData: ArrayList<GoodsItemData> = ArrayList<GoodsItemData>()
                for (sku: String in skus) {
                    val data = GoodsItemData(
                        sku,
                        "title_$sku",
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

            override fun onItemClick(goodsItemData: GoodsItemData) {
                InAppStoryManager.closeStoryReader()
                Toast.makeText(
                    this@DefaultWidgetSample,
                    goodsItemData.toString(), Toast.LENGTH_LONG
                ).show()
            }
        })
        try {
            storiesList.loadStories()
        } catch (e: DataException) {
            e.printStackTrace()
        }
    }

    override fun onDestroy() {
        AppearanceManager.getCommonInstance().csCustomGoodsWidget(null)
        super.onDestroy()
    }
}