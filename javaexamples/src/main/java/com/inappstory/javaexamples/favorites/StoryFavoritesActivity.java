package com.inappstory.javaexamples.favorites;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.inappstory.javaexamples.ImageLoader;
import com.inappstory.javaexamples.R;
import com.inappstory.sdk.AppearanceManager;
import com.inappstory.sdk.InAppStoryManager;
import com.inappstory.sdk.exceptions.DataException;
import com.inappstory.sdk.stories.ui.list.StoriesList;
import com.inappstory.sdk.stories.ui.views.IStoriesListItem;

import java.io.File;

public class StoryFavoritesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fav);
        showStories();

        findViewById(R.id.removeAll).setOnClickListener(v -> {
            if (InAppStoryManager.isNull()) return;
            InAppStoryManager.getInstance().removeAllFavorites();
        });
    }


    private AppearanceManager generateAppearanceManager() {
        AppearanceManager appearanceManager =
                new AppearanceManager()
                        .csHasFavorite(true)
                        .csIsDraggable(true)
                        .csListItemInterface(new IStoriesListItem() {
                            @Override
                            public View getView() {
                                return LayoutInflater.from(StoryFavoritesActivity.this)
                                        .inflate(R.layout.custom_story_list_item_removable, null, false);
                            }

                            @Override
                            public View getVideoView() {
                                return null;
                            }

                            @Override
                            public void setId(View itemView, int id) {
                                itemView.findViewById(R.id.remove).setOnClickListener(v -> {
                                    if (InAppStoryManager.isNull()) return;
                                    InAppStoryManager.getInstance().removeFromFavorite(id);
                                });
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
                        });
        return appearanceManager;
    }

    private void showStories() {
        StoriesList storiesList = findViewById(R.id.favList);
        storiesList.setAppearanceManager(generateAppearanceManager());
        storiesList.setLayoutManager(new GridLayoutManager(StoryFavoritesActivity.this, 2,
                RecyclerView.VERTICAL, false));
        try {
            storiesList.loadStories();
        } catch (DataException e) {
            e.printStackTrace();
        }
    }

    private void showImage(String path, int backgroundColor, ImageView imageView) {
        if (path != null && !path.isEmpty()) {
            Bitmap bmp = ImageLoader.decodeFile(new File(path));
            if (bmp == null) {
                imageView.setBackgroundColor(backgroundColor);
            } else {
                imageView.setImageBitmap(bmp);
            }
        } else {
            imageView.setBackgroundColor(backgroundColor);
        }
    }
}
