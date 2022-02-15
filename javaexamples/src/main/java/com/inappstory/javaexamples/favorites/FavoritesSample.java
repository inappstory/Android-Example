package com.inappstory.javaexamples.favorites;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;

import com.inappstory.javaexamples.R;
import com.inappstory.sdk.AppearanceManager;
import com.inappstory.sdk.exceptions.DataException;
import com.inappstory.sdk.imageloader.ImageLoader;
import com.inappstory.sdk.stories.ui.list.StoriesList;
import com.inappstory.sdk.stories.ui.views.IGetFavoriteListItem;
import com.inappstory.sdk.stories.ui.views.IStoriesListItem;
import com.inappstory.sdk.stories.utils.Sizes;

import java.util.List;

public class FavoritesSample extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base_list);
        showStories();
    }

    private AppearanceManager generateSimpleAppearanceManager() {

        AppearanceManager appearanceManager =
                new AppearanceManager().csHasFavorite(true);
        return appearanceManager;
    }

    private AppearanceManager generateCustomAppearanceManager() {
        AppearanceManager appearanceManager =
                new AppearanceManager()
                        .csHasFavorite(true)

                        .csListItemInterface(new IStoriesListItem() {
                            @Override
                            public View getView() {
                                return LayoutInflater.from(FavoritesSample.this)
                                        .inflate(R.layout.custom_story_list_item, null, false);
                            }

                            @Override
                            public View getVideoView() {
                                return null;
                            }

                            @Override
                            public void setId(View view, int i) {

                            }

                            @Override
                            public void setTitle(View itemView, String title, Integer color) {
                                ((AppCompatTextView) itemView.findViewById(R.id.title)).setText(title);
                                if (color != null) {
                                    ((AppCompatTextView) itemView.findViewById(R.id.title)).setTextColor(color);
                                }
                            }


                            @Override
                            public void setImage(View itemView, String url, int backgroundColor) {
                                showImage(url, backgroundColor, itemView.findViewById(R.id.image));
                            }

                            @Override
                            public void setHasAudio(View itemView, boolean hasAudio) {

                            }

                            @Override
                            public void setVideo(View view, String s) {

                            }


                            @Override
                            public void setOpened(View itemView, boolean isOpened) {
                                itemView.findViewById(R.id.border).setVisibility(isOpened ?
                                        View.INVISIBLE : View.VISIBLE);
                            }
                        })
                        .csFavoriteListItemInterface(new IGetFavoriteListItem() {
                            @Override
                            public View getFavoriteItem() {
                                View v = LayoutInflater.from(FavoritesSample.this)
                                        .inflate(R.layout.custom_story_list_item_favorite, null, false);
                                return v;
                            }

                            @Override
                            public void bindFavoriteItem(View favCell, List<Integer> backColors, int count) {
                                RelativeLayout imageViewLayout = favCell.findViewById(R.id.container);
                                imageViewLayout.removeAllViews();
                                AppCompatTextView title = favCell.findViewById(R.id.title);
                                title.setText("Favorites");
                                bindFavoriteCellImages(imageViewLayout, null, backColors, count);
                            }

                            @Override
                            public void setImages(View favCell, List<String> list, List<Integer> backColors, int count) {
                                RelativeLayout imageViewLayout = favCell.findViewById(R.id.container);
                                imageViewLayout.removeAllViews();
                                bindFavoriteCellImages(imageViewLayout, list, backColors, count);
                            }


                        });

        return appearanceManager;
    }

    void bindFavoriteCellImages(RelativeLayout imageViewLayout,
                                List<String> list, List<Integer> backColors, int count) {
        Context context = FavoritesSample.this;
        if (count > 0) {
            AppCompatImageView image1 = new AppCompatImageView(context);
            AppCompatImageView image2 = new AppCompatImageView(context);
            RelativeLayout.LayoutParams piece2;
            switch (count) {
                case 1:
                    image1.setLayoutParams(new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT,
                            RelativeLayout.LayoutParams.MATCH_PARENT));
                    image1.setScaleType(ImageView.ScaleType.CENTER_CROP);
                    showImage(list != null ? list.get(0) : null, backColors.get(0), image1);
                    imageViewLayout.addView(image1);
                    break;
                default:
                    piece2 = new RelativeLayout.LayoutParams(Sizes.dpToPxExt(42),
                            RelativeLayout.LayoutParams.MATCH_PARENT);
                    image1.setLayoutParams(new RelativeLayout.LayoutParams(Sizes.dpToPxExt(42),
                            RelativeLayout.LayoutParams.MATCH_PARENT));
                    piece2.addRule(RelativeLayout.ALIGN_PARENT_RIGHT, RelativeLayout.TRUE);
                    image2.setLayoutParams(piece2);

                    image1.setScaleType(ImageView.ScaleType.CENTER_CROP);
                    image2.setScaleType(ImageView.ScaleType.CENTER_CROP);
                    showImage(list != null ? list.get(0) : null, backColors.get(0), image1);
                    showImage(list != null ? list.get(1) : null, backColors.get(1), image2);
                    imageViewLayout.addView(image1);
                    imageViewLayout.addView(image2);
                    break;
            }
        }
    }

    private void showStories() {
        StoriesList storiesList = findViewById(R.id.stories_list);
        storiesList.setAppearanceManager(generateSimpleAppearanceManager());
        storiesList.setOnFavoriteItemClick(() -> {
            Intent intent = new Intent(FavoritesSample.this, StoryFavoritesActivity.class);
            startActivity(intent);
        });
        try {
            storiesList.loadStories();
        } catch (DataException e) {
            e.printStackTrace();
        }
    }

    void showImage(String url, int backgroundColor, ImageView imageView) {
        if (url != null && !url.isEmpty()) {
            ImageLoader.getInstance().displayImage(url, -1, imageView);
        } else {
            imageView.setBackgroundColor(backgroundColor);
        }
    }
}