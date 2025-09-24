package com.inappstory.kotlinexamples.custom;

import android.content.Context;
import android.view.MotionEvent;
import android.widget.FrameLayout;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageView;

import com.inappstory.kotlinexamples.R;

public class CustomButton extends FrameLayout {
    AppCompatImageView imageView;

    public CustomButton(@NonNull Context context) {
        super(context);
        init(context);
    }

    public void click() {

    }

    public void touch(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                animate().scaleX(0.7f).scaleY(0.7f).start();
                break;
            case MotionEvent.ACTION_CANCEL:
            case MotionEvent.ACTION_UP:
                animate().scaleX(1f).scaleY(1f).start();
                break;
        }
    }

    public void setScaleType(ImageView.ScaleType scaleType) {
        imageView.setScaleType(scaleType);
    }

    public void setImage(int id) {
        imageView.setImageResource(id);
    }

    public void setEnabled(boolean enabled) {
        imageView.setEnabled(enabled);
    }

    public void setActive(boolean active) {
        imageView.setActivated(active);
    }

    private void init(Context context) {
        inflate(context, R.layout.custom_button, this);
        imageView = findViewById(R.id.image);
    }
}
