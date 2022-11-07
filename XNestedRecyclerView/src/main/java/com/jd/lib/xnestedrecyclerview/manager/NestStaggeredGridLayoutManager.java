package com.jd.lib.xnestedrecyclerview.manager;

import android.content.Context;
import android.util.AttributeSet;

import androidx.recyclerview.widget.StaggeredGridLayoutManager;

public class NestStaggeredGridLayoutManager extends StaggeredGridLayoutManager implements INestLayoutManager {
    private boolean isInBottom;

    public NestStaggeredGridLayoutManager(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    public NestStaggeredGridLayoutManager(int spanCount, int orientation) {
        super(spanCount, orientation);
    }

    @Override
    public void setInBottom(boolean inBottom) {
        isInBottom = inBottom;
    }

    @Override
    public boolean canScrollVertically() {
        return !isInBottom;
    }
}
