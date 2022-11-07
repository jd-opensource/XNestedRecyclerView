package com.jd.lib.xnestedrecyclerview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

public class NestRecyclerView extends RecyclerView {
    private boolean startFling;
    private int velocityY;
    private int totalDy;
    private FlingHelper helper;

    private OnNestFlingListener onFlingListener;

    public NestRecyclerView(Context context) {
        super(context);
        initView(context);
    }

    public NestRecyclerView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    public NestRecyclerView(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        initView(context);
    }

    private void initView(Context context) {
        helper = new FlingHelper(context);
        addOnScrollListener(new OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                if (startFling) {
                    totalDy = 0;
                }
                totalDy += dy;
                if (startFling) {
                    startFling = false;
                }
                if (velocityY != 0 && isChildOnTop(recyclerView)) {
                    double distance = helper.getSplineFlingDistance(velocityY);
                    if (distance > Math.abs(totalDy)) {
                        int childVelocityY = helper.getVelocityByDistance(distance + totalDy);
                        //dispatchNestedFling(0, -childVelocityY, false);无效，改为回调
                        if (null != onFlingListener) {
                            onFlingListener.onNestFling(0, -childVelocityY);
                        }
                    }
                }
            }

            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                    velocityY = 0;
                }
            }
        });
    }

    public void setOnNestFlingListener(OnNestFlingListener onFlingListener) {
        this.onFlingListener = onFlingListener;
    }

    public boolean isChildOnTop(View view) {
        return !view.canScrollVertically(-1);
    }

    @Override
    public void onNestedScroll(View target, int dxConsumed, int dyConsumed, int dxUnconsumed, int dyUnconsumed) {
        scrollBy(0, dyUnconsumed);
    }

    @Override
    public boolean fling(int velocityX, int velocityY) {
        boolean flag = super.fling(velocityX, velocityY);
        if (flag && velocityY < 0) {
            startFling = true;
            this.velocityY = velocityY;
        } else {
            this.velocityY = 0;
        }
        return flag;
    }
}
