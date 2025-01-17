package com.inappstory.kotlinexamples

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatCheckBox
import com.inappstory.kotlinexamples.advanced.AdvancedCellSample
import com.inappstory.kotlinexamples.custom.CustomCellSample
import com.inappstory.kotlinexamples.customreader.ReaderCustomizationSample
import com.inappstory.kotlinexamples.favorites.FavoritesSample
import com.inappstory.kotlinexamples.feeds.FeedSample
import com.inappstory.kotlinexamples.goodswidget.CustomCellWidgetSample
import com.inappstory.kotlinexamples.goodswidget.CustomWidgetSample
import com.inappstory.kotlinexamples.goodswidget.DefaultWidgetSample
import com.inappstory.kotlinexamples.iam.InAppMessagingSample
import com.inappstory.kotlinexamples.notification.NotificationSubscribeSample
import com.inappstory.kotlinexamples.onboarding.OnboardingSample
import com.inappstory.kotlinexamples.share.CustomShareSample
import com.inappstory.kotlinexamples.simple.BasicIntegrationSample
import com.inappstory.kotlinexamples.single.SingleStorySample
import com.inappstory.kotlinexamples.stackfeed.StackFeedIntegrationSample
import com.inappstory.kotlinexamples.tagsplaceholders.TagsPlaceholdersSample
import com.inappstory.kotlinexamples.userchange.UserChangeSample

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val isHQcb = findViewById<AppCompatCheckBox>(R.id.isHQ)
        findViewById<View>(R.id.simple).setOnClickListener {
            showActivity(
                BasicIntegrationSample::class.java
            )
        }
        findViewById<View>(R.id.in_app_messages).setOnClickListener {
            showActivity(
                InAppMessagingSample::class.java
            )
        }
        findViewById<View>(R.id.stack).setOnClickListener {
            showActivity(
                StackFeedIntegrationSample::class.java
            )
        }
        findViewById<View>(R.id.advanced).setOnClickListener {
            showActivity(
                AdvancedCellSample::class.java, isHQcb.isChecked
            )
        }
        findViewById<View>(R.id.custom).setOnClickListener {
            showActivity(
                CustomCellSample::class.java
            )
        }
        findViewById<View>(R.id.feeds).setOnClickListener {
            showActivity(
                FeedSample::class.java
            )
        }
        findViewById<View>(R.id.favorites).setOnClickListener {
            showActivity(
                FavoritesSample::class.java
            )
        }
        findViewById<View>(R.id.custom_reader).setOnClickListener {
            showActivity(
                ReaderCustomizationSample::class.java
            )
        }
        findViewById<View>(R.id.onboarding).setOnClickListener {
            showActivity(
                OnboardingSample::class.java
            )
        }
        findViewById<View>(R.id.single).setOnClickListener {
            showActivity(
                SingleStorySample::class.java
            )
        }
        findViewById<View>(R.id.change_user).setOnClickListener {
            showActivity(
                UserChangeSample::class.java
            )
        }
        findViewById<View>(R.id.tags).setOnClickListener {
            showActivity(
                TagsPlaceholdersSample::class.java
            )
        }
        findViewById<View>(R.id.notification).setOnClickListener {
            showActivity(
                NotificationSubscribeSample::class.java
            )
        }
        findViewById<View>(R.id.base_carousel).setOnClickListener {
            showActivity(
                DefaultWidgetSample::class.java
            )
        }
        findViewById<View>(R.id.custom_carousel).setOnClickListener {
            showActivity(
                CustomCellWidgetSample::class.java
            )
        }
        findViewById<View>(R.id.custom_goods_widget).setOnClickListener {
            showActivity(
                CustomWidgetSample::class.java
            )
        }
        findViewById<View>(R.id.custom_share).setOnClickListener {
            showActivity(
                CustomShareSample::class.java
            )
        }
    }

    private fun showActivity(activityClass: Class<*>) {
        val intent = Intent(this@MainActivity, activityClass)
        startActivity(intent)
    }

    private fun showActivity(activityClass: Class<*>, isHQ: Boolean) {
        val intent = Intent(this@MainActivity, activityClass)
        intent.putExtra("isHQ", isHQ)
        startActivity(intent)
    }
}