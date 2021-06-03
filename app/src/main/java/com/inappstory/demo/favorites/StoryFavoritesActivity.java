package com.inappstory.demo.favorites;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.inappstory.demo.R;
import com.inappstory.sdk.AppearanceManager;
import com.inappstory.sdk.exceptions.DataException;
import com.inappstory.sdk.stories.ui.list.StoriesList;

public class StoryFavoritesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fav);
        showStories();
    }

    private void showStories() {
        StoriesList storiesList = findViewById(R.id.favList);
        storiesList.setAppearanceManager(new AppearanceManager());
        storiesList.setLayoutManager(new GridLayoutManager(StoryFavoritesActivity.this, 2,
                RecyclerView.VERTICAL, false));
        try {
            storiesList.loadStories();
        } catch (DataException e) {
            e.printStackTrace();
        }
    }


}
