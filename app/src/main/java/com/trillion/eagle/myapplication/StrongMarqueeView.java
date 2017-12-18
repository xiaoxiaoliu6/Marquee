package com.trillion.eagle.myapplication;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Build;
import android.os.Handler;
import android.support.annotation.ColorInt;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * 跑马灯
 */
class StrongMarqueeView extends TextView {
    private Rect mRect;
    private boolean mIsEnd;
    private boolean mIsEnableScroll;
    private String mText;
    private float offX;
    private Paint mPaint;
    private int mStep = 3;

    public StrongMarqueeView(Context context) {
        super(context);
        init();
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public StrongMarqueeView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    public StrongMarqueeView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public StrongMarqueeView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        this.setMaxLines(1);
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    }

    @Override
    public boolean isFocused() {
        return true;
    }

    public void setText(String text) {
        mText = text;
        mPaint.setTextSize(sp2px(16));
        mRect = new Rect();
        mPaint.getTextBounds(mText, 0, mText.length(), mRect);
        mIsEnd = false;
        offX = 0f;
        this.mIsEnableScroll = false;
        invalidate();
    }

    public void setTextColor(@ColorInt int color) {
        mPaint.setColor(color);
        invalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        float x, y;
        if (!mIsEnd) {
            x = -offX;
        } else {
            x = getMeasuredWidth() - offX;
        }
        y = getMeasuredHeight() / 2 + (-mPaint.ascent() - mPaint.descent()) / 2;
        canvas.drawText(mText, x, y, mPaint);

        offX += mStep;
        if ((offX >= mRect.width() && !mIsEnd) || (offX >= getMeasuredWidth() + mRect.width() && mIsEnd)) {
            offX = 0;
            mIsEnd = true;
        }
        if (!mIsEnableScroll) {
            return;
        }
        invalidate();
    }

    public void startScroll(long delayTime) {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                mIsEnableScroll = true;
                invalidate();
            }
        }, delayTime);
    }

    public void stopScroll() {
        mIsEnableScroll = false;
        invalidate();
    }

    private int sp2px(int spValue) {
        final float fontScale = this.getContext().getResources().getDisplayMetrics().scaledDensity;
        return (int) (spValue * fontScale + 0.5f);
    }
}