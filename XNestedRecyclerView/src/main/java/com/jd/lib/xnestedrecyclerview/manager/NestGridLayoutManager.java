package com.jd.lib.xnestedrecyclerview.manager;

import android.content.Context;
import android.util.AttributeSet;

import androidx.recyclerview.widget.GridLayoutManager;

public class NestGridLayoutManager extends GridLayoutManager implements INestLayoutManager {
    private boolean isInBottom;

    public NestGridLayoutManager(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    public NestGridLayoutManager(Context context, int spanCount) {
        super(context, spanCount);
    }

    public NestGridLayoutManager(Context context, int spanCount, int orientation, boolean reverseLayout) {
        super(context, spanCount, orientation, reverseLayout);
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
