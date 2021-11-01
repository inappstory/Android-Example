package com.inappstory.demo.goodswidget;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.inappstory.demo.R;
import com.inappstory.sdk.AppearanceManager;
import com.inappstory.sdk.InAppStoryManager;
import com.inappstory.sdk.exceptions.DataException;
import com.inappstory.sdk.stories.ui.list.StoriesList;
import com.inappstory.sdk.stories.ui.views.goodswidget.GetGoodsDataCallback;
import com.inappstory.sdk.stories.ui.views.goodswidget.GoodsItemData;
import com.inappstory.sdk.stories.ui.views.goodswidget.ICustomGoodsItem;
import com.inappstory.sdk.stories.ui.views.goodswidget.ICustomGoodsWidget;
import com.inappstory.sdk.stories.ui.views.goodswidget.IGoodsWidgetAppearance;

import java.util.ArrayList;

public class DefaultWidgetSample extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base_list);
        showStories();
    }

    StoriesList storiesList;

    private void showStories() {
        storiesList = findViewById(R.id.stories_list);
        storiesList.setAppearanceManager(new AppearanceManager());

        AppearanceManager.getCommonInstance().csCustomGoodsWidget(new ICustomGoodsWidget() {
            @Override
            public View getWidgetView() {
                return null;
            }

            @Override
            public ICustomGoodsItem getItem() {
                return null;
            }

            @Override
            public IGoodsWidgetAppearance getWidgetAppearance() {
                return null;
            }

            @Override
            public RecyclerView.ItemDecoration getDecoration() {
                return null;
            }

            @Override
            public void getSkus(ArrayList<String> skus, GetGoodsDataCallback callback) {
                ArrayList<GoodsItemData> goodsItemData = new ArrayList<>();
                for (String sku : skus) {
                    GoodsItemData data = new GoodsItemData(sku, "title_" + sku,"desc_" +sku,
                            "https://media.istockphoto.com/photos/big-and-small-picture-id172759822", "10", "20", sku);
                    goodsItemData.add(data);
                }
                callback.onSuccess(goodsItemData);
            }

            @Override
            public void onItemClick(GoodsItemData goodsItemData) {
                InAppStoryManager.closeStoryReader();
                Toast.makeText(DefaultWidgetSample.this,
                        goodsItemData.toString(), Toast.LENGTH_LONG).show();
            }
        });
        try {
            storiesList.loadStories();
        } catch (DataException e) {
            e.printStackTrace();
        }

    }

    @Override
    protected void onDestroy() {
        AppearanceManager.getCommonInstance().csCustomGoodsWidget(null);
        super.onDestroy();
    }
}
