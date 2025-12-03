package com.inappstory.kotlinexamples.checkout

import android.annotation.SuppressLint
import android.graphics.Point
import android.graphics.Rect
import android.os.Bundle
import android.text.TextUtils
import android.util.DisplayMetrics
import android.util.Log
import android.view.View
import android.view.WindowManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.OnApplyWindowInsetsListener
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.inappstory.kotlinexamples.R
import com.inappstory.sdk.AppearanceManager
import com.inappstory.sdk.InAppStoryManager
import com.inappstory.sdk.UseManagerInstanceCallback
import com.inappstory.sdk.goods.outercallbacks.ProductCart
import com.inappstory.sdk.goods.outercallbacks.ProductCartInteractionCallback
import com.inappstory.sdk.goods.outercallbacks.ProductCartOffer
import com.inappstory.sdk.goods.outercallbacks.ProductCartUpdatedProcessCallback
import com.inappstory.sdk.stories.ui.list.StoriesList

class CheckoutIntegrationSample : AppCompatActivity(), OnApplyWindowInsetsListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_base_list)
        showStories()
        addProductsCallback()
    }

    val products: MutableMap<String, ProductCartOffer> = hashMapOf()

    private fun updateProducts(productCartOffer: ProductCartOffer) {
        val product = products[productCartOffer.offerId]
        if (product == null) {
            products[productCartOffer.offerId] = productCartOffer
        } else {
            product.quantity += productCartOffer.quantity
        }
    }

    private fun invokeCallback(processCallback: ProductCartUpdatedProcessCallback?) {
        var totalPrice = 0f
        var totalOldPrice = 0f
        var currency: String? = ""
        products.values.forEach {
            try {
                totalPrice += it.price.toFloat()
            } catch (e: Exception) {
            }
            try {
                totalOldPrice += it.oldPrice.toFloat()
            } catch (e: Exception) {
            }
            currency = it.currency
        }
        processCallback?.onSuccess(
            ProductCart()
                .offers(
                    products.values.toList()
                )
                .price("$totalPrice")
                .oldPrice("$totalOldPrice")
                .priceCurrency(currency)
        )
    }

    private fun addProductsCallback() {
        InAppStoryManager.useInstance(object : UseManagerInstanceCallback() {
            override fun use(manager: InAppStoryManager) {
                manager.setProductCartInteractionCallback(
                    object : ProductCartInteractionCallback {
                        override fun cartUpdate(
                            offer: ProductCartOffer?,
                            processCallback: ProductCartUpdatedProcessCallback?
                        ) {
                            if (offer == null) return
                            updateProducts(offer)
                            invokeCallback(processCallback)
                            return
                        }

                        override fun cartClicked() {
                            val text = TextUtils.join(
                                "\n",
                                products.values.map {
                                    it.name + " -> " + it.quantity
                                }
                            )
                            Toast.makeText(
                                this@CheckoutIntegrationSample,
                                text,
                                Toast.LENGTH_LONG
                            )
                                .show()
                           /* InAppStoryManager.closeStoryReader(true) {

                            }*/
                        }

                        override fun cartGetState(processCallback: ProductCartUpdatedProcessCallback?) {
                            invokeCallback(processCallback)
                        }
                    })
            }
        })
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
        }
        return insets
    }


}