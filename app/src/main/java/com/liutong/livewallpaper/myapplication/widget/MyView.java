package com.liutong.livewallpaper.myapplication.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by win10 on 2017/9/4.
 */

public class MyView extends View {
    public MyView(Context context) {
        super(context);
    }

    public MyView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        int width = getMySize(100,widthMeasureSpec);
        int height = getMySize(100,heightMeasureSpec);
//        if (width < height) {
//            height = width;
//        } else {
//            width = height;
//        }
        setMeasuredDimension(width,height);
    }

    private int getMySize(int defaultSize,int measureSpec) {
        int mySize = defaultSize;
        int mode = MeasureSpec.getMode(measureSpec);
        int size = MeasureSpec.getSize(measureSpec);

        switch (mode) {
            case MeasureSpec.UNSPECIFIED :{
                mySize = defaultSize;
                break;
            }
            case MeasureSpec.AT_MOST :{
                mySize = size;
                break;
            }
            case MeasureSpec.EXACTLY:{
                mySize = size;
                break;
            }
        }
        
        return mySize;
    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
    }
}
