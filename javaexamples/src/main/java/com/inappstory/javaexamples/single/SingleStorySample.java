package com.inappstory.javaexamples.single;

import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatEditText;

import com.inappstory.javaexamples.R;
import com.inappstory.sdk.AppearanceManager;
import com.inappstory.sdk.InAppStoryManager;
import com.inappstory.sdk.stories.ui.list.StoriesList;

public class SingleStorySample extends AppCompatActivity {

    AppearanceManager appearanceManager = new AppearanceManager();
    AppCompatEditText storyIdText;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single);
        WebView webView = findViewById(R.id.webView);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setUseWideViewPort(true);
        webView.setWebViewClient(new WebViewClient());
        webView.loadUrl("http://web.test.kiozk.ru/subscribe-mobile?auth_token=ZbzlefEgIEz_07dG411EB84x1Q0WwUfEnIrkzE0Uo2k");
        showStories();
        storyIdText = findViewById(R.id.story_id);
        findViewById(R.id.show_story).setOnClickListener(v -> showSingle(storyIdText.getText().toString()));
    }

    private void showSingle(String storyId) {
        if (storyId == null || storyId.isEmpty()) return;
        InAppStoryManager.getInstance().showStory(storyId, SingleStorySample.this, appearanceManager);
    }

    private void showStories() {
        StoriesList storiesList = findViewById(R.id.stories_list);
        storiesList.setAppearanceManager(appearanceManager);

        storiesList.loadStories();
    }
}
