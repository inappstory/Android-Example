package com.inappstory.demo.goodswidget;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatTextView;
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
import com.inappstory.sdk.stories.utils.Sizes;

import java.util.ArrayList;

public class CustomWidgetSample extends AppCompatActivity {
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
            RelativeLayout container;

            @Override
            public View getWidgetView() {
                container =
                        (RelativeLayout) View.inflate(CustomWidgetSample.this,
                                R.layout.custom_goods_widget, null);

                return container;
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

            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void getSkus(ArrayList<String> skus, GetGoodsDataCallback getGoodsDataCallback) {
                if (container != null && skus != null) {
                    getGoodsDataCallback.onSuccess(new ArrayList<>());
                    for (String sku : skus) {
                        AppCompatTextView textView = new AppCompatTextView(
                                CustomWidgetSample.this);
                        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                                ViewGroup.LayoutParams.WRAP_CONTENT);
                        lp.setMargins(Sizes.dpToPxExt(16), Sizes.dpToPxExt(16),
                                Sizes.dpToPxExt(16), 0);
                        textView.setLayoutParams(lp);

                        textView.setPadding(Sizes.dpToPxExt(8), Sizes.dpToPxExt(8),
                                Sizes.dpToPxExt(8), Sizes.dpToPxExt(8));
                        textView.setBackground(getDrawable(R.drawable.widget_background_solid));
                        textView.setTextSize(18);
                        textView.setTextColor(getResources().getColor(R.color.white));
                        textView.setText(sku);
                        textView.setOnClickListener(v1 -> {
                            //    InAppStoryManager.closeStoryReader();
                            Toast.makeText(CustomWidgetSample.this, textView.getText(), Toast.LENGTH_LONG).show();
                        });
                        ((LinearLayout) container.findViewById(R.id.container)).addView(textView);

                    }
                    container.findViewById(R.id.close).setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            getGoodsDataCallback.onClose();
                        }
                    });
                }
            }

            @Override
            public void onItemClick(GoodsItemData goodsItemData) {

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
