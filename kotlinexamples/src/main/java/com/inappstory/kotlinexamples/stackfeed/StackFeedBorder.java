package com.inappstory.kotlinexamples.stackfeed;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.ColorInt;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.inappstory.sdk.stories.utils.Sizes;

public class StackFeedBorder extends View {
    float gapAngle = 4f;
    float strokeWidthDp = 4f;
    float strokeWidth = 3f;
    float dashAngle = 0f;
    float shiftAngle = 0f;

    Paint readPaint = new Paint();
    Paint nonReadPaint = new Paint();

    RectF rectF = new RectF(0, 0, 0, 0);
    boolean[] statuses = new boolean[0];

    public StackFeedBorder(Context context) {
        super(context);
        init(context);
    }

    public StackFeedBorder(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public StackFeedBorder(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        strokeWidth = Sizes.dpFloatToPxExt(strokeWidthDp, context);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        rectF = new RectF(
                0 + strokeWidth / 2,
                0 + strokeWidth / 2,
                w - strokeWidth / 2,
                h - strokeWidth / 2
        );
        super.onSizeChanged(w, h, oldw, oldh);
    }


    public void setStatuses(@NonNull boolean[] statuses) {
        this.statuses = statuses;
        if (statuses.length == 0) return;
        dashAngle = (360f - gapAngle * statuses.length) / statuses.length;
        shiftAngle = 360f / statuses.length;
    }

    public void setColors(@ColorInt int read, @ColorInt int nonRead) {
        readPaint.setColor(read);
        readPaint.setStyle(Paint.Style.STROKE);
        readPaint.setStrokeWidth(strokeWidth);
        nonReadPaint.setColor(nonRead);
        nonReadPaint.setStyle(Paint.Style.STROKE);
        nonReadPaint.setStrokeWidth(strokeWidth);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        for (int i = 0; i < statuses.length; i++) {
            canvas.drawArc(
                    rectF,
                    shiftAngle * i - 90,
                    dashAngle,
                    false,
                    statuses[i] ? readPaint : nonReadPaint
            );
        }
    }
}
