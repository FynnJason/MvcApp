package com.github.mvpapp.widget;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.ImageView;

import androidx.annotation.Nullable;

import com.github.mvpapp.R;


@SuppressLint("AppCompatCustomView")
public class FJImageView extends ImageView {

    private Drawable mUnSelectedDrawable;
    private Drawable mSelectedDrawable;
    private boolean mIsSelected;

    public FJImageView(Context context) {
        super(context);
    }

    public FJImageView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs, 0);
        init(context, attrs);
    }

    public FJImageView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.FJImageView);
        mUnSelectedDrawable = typedArray.getDrawable(R.styleable.FJImageView_fj_un_selected);
        mSelectedDrawable = typedArray.getDrawable(R.styleable.FJImageView_fj_selected);
        mIsSelected = typedArray.getBoolean(R.styleable.FJImageView_fj_isSelected, false);
        typedArray.recycle();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        this.setImageDrawable(mIsSelected ? mSelectedDrawable : mUnSelectedDrawable);
    }

    @Override
    public void setSelected(boolean selected) {
        super.setSelected(selected);
        mIsSelected = selected;
    }
}
