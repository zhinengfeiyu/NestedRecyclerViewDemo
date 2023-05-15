package com.zhinengfeiyu.nestedrecyclerviewdemo;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

public class InnerRecyclerView extends RecyclerView {

    private float mLastEventY;

    public InnerRecyclerView(@NonNull Context context) {
        super(context);
    }

    public InnerRecyclerView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public InnerRecyclerView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        boolean result;
        if (ev.getAction() == MotionEvent.ACTION_DOWN) {
            result = true;
            getParent().requestDisallowInterceptTouchEvent(true);
            super.dispatchTouchEvent(ev);
        } else if (ev.getAction() == MotionEvent.ACTION_MOVE) {
            if (ev.getY() < mLastEventY) {
                result = getTop() <= 0 && canScrollVertically(1);
            } else {
                result = canScrollVertically(-1);
            }
            if (!result) {
                getParent().requestDisallowInterceptTouchEvent(false);
            }
            super.dispatchTouchEvent(ev);
        } else {
            result = super.dispatchTouchEvent(ev);
        }
        mLastEventY = ev.getY();
        return result;
    }
}
