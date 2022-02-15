package com.inappstory.kotlinexamples.goodswidget

import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.RelativeLayout
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.content.res.AppCompatResources
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.RecyclerView
import com.inappstory.kotlinexamples.R
import com.inappstory.sdk.AppearanceManager
import com.inappstory.sdk.exceptions.DataException
import com.inappstory.sdk.stories.ui.list.StoriesList
import com.inappstory.sdk.stories.ui.views.goodswidget.*
import com.inappstory.sdk.stories.utils.Sizes
import java.util.ArrayList

class CustomWidgetSample : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_base_list)
        showStories()
    }

    private fun showStories() {
        val storiesList = findViewById<StoriesList>(R.id.stories_list)
        storiesList.setAppearanceManager(AppearanceManager())
        AppearanceManager.getCommonInstance().csCustomGoodsWidget(object : ICustomGoodsWidget {
            var container: RelativeLayout? = null

            override fun getWidgetView(): View {
                container = View.inflate(
                    this@CustomWidgetSample,
                    R.layout.custom_goods_widget, null
                ) as RelativeLayout
                return (container)!!
            }

            override fun getItem(): ICustomGoodsItem? {
                return null
            }

            override fun getWidgetAppearance(): IGoodsWidgetAppearance? {
                return null
            }

            override fun getDecoration(): RecyclerView.ItemDecoration? {
                return null
            }

            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            override fun getSkus(
                skus: ArrayList<String>?,
                getGoodsDataCallback: GetGoodsDataCallback
            ) {
                container?.let { container ->
                    if (skus != null) {
                        getGoodsDataCallback.onSuccess(ArrayList())
                        for (sku: String in skus) {
                            val textView = AppCompatTextView(
                                this@CustomWidgetSample
                            )
                            val lp = LinearLayout.LayoutParams(
                                ViewGroup.LayoutParams.MATCH_PARENT,
                                ViewGroup.LayoutParams.WRAP_CONTENT
                            )
                            lp.setMargins(
                                Sizes.dpToPxExt(16), Sizes.dpToPxExt(16),
                                Sizes.dpToPxExt(16), 0
                            )
                            textView.layoutParams = lp
                            textView.setPadding(
                                Sizes.dpToPxExt(8), Sizes.dpToPxExt(8),
                                Sizes.dpToPxExt(8), Sizes.dpToPxExt(8)
                            )
                            textView.background = AppCompatResources.getDrawable(
                                this@CustomWidgetSample,
                                R.drawable.widget_background_solid
                            )
                            textView.textSize = 18f
                            textView.setTextColor(resources.getColor(R.color.white))
                            textView.text = sku
                            textView.setOnClickListener {
                                getGoodsDataCallback.itemClick(sku)
                                Toast.makeText(
                                    this@CustomWidgetSample,
                                    textView.text,
                                    Toast.LENGTH_LONG
                                ).show()
                            }
                            (container.findViewById<View>(R.id.container) as LinearLayout).addView(
                                textView
                            )
                        }
                        container.findViewById<View>(R.id.close).setOnClickListener {
                            getGoodsDataCallback.onClose()
                        }
                    }
                }

            }

            override fun onItemClick(goodsItemData: GoodsItemData) {}
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