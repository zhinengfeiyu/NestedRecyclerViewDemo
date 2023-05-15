package com.zhinengfeiyu.nestedrecyclerviewdemo;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

public class OuterRecyclerView extends RecyclerView {

    private static final int PRE_CHILD_MAX_EXPOSE_HEIGHT = 200;
    private static final int NEXT_CHILD_MAX_EXPOSE_HEIGHT = 200;

    private float mLastEventY = 0f;

    private boolean mCanAutoScrollUp = false;
    private boolean mCanAutoScrollDown = false;

    private View mCurrentChild = null;

    public OuterRecyclerView(@NonNull Context context) {
        super(context);
    }

    public OuterRecyclerView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public OuterRecyclerView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (ev.getAction() == MotionEvent.ACTION_DOWN) {
            mLastEventY = 0;
            mCanAutoScrollUp = getChildCount() == 1;
            mCanAutoScrollDown = getChildCount() == 1;
            if (getChildCount() == 1) {
                mCurrentChild = getChildAt(0);
            }
        }
        boolean result = super.dispatchTouchEvent(ev);
        mLastEventY = ev.getY();
        return result;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_MOVE) {
            if (event.getY() <= mLastEventY) {
                if (mCanAutoScrollUp) {
                    if (getChildCount() == 2 && getChildAt(1) != mCurrentChild &&
                        getChildAt(1).getTop() <= getMeasuredHeight() - NEXT_CHILD_MAX_EXPOSE_HEIGHT) {
                        return false;
                    }
                }
            } else {
                if (mCanAutoScrollDown) {
                    if (getChildCount() == 2 && getChildAt(0) != mCurrentChild &&
                            getChildAt(1).getTop() >= PRE_CHILD_MAX_EXPOSE_HEIGHT) {
                        return false;
                    }
                }
            }
        } else if (event.getAction() == MotionEvent.ACTION_UP) {
            if (getChildCount() == 2 && getChildAt(1) != mCurrentChild) {
                if (getChildAt(1).getTop() <= getMeasuredHeight() - NEXT_CHILD_MAX_EXPOSE_HEIGHT) {
                    smoothScrollBy(0, getChildAt(1).getTop());
                } else {
                    smoothScrollBy(0, -(getMeasuredHeight() - getChildAt(1).getTop()));
                }
                return false;
            }
            if (getChildCount() == 2 && getChildAt(0) != mCurrentChild) {
                if (getChildAt(1).getTop() >= PRE_CHILD_MAX_EXPOSE_HEIGHT) {
                    smoothScrollBy(0, -(getMeasuredHeight() - getChildAt(1).getTop()));
                } else {
                    smoothScrollBy(0, getChildAt(1).getTop());
                }
                return false;
            }
        }
        return super.onTouchEvent(event);
    }

}
