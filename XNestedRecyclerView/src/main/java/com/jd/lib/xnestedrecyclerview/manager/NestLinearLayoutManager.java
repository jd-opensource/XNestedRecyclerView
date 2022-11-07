package com.jd.lib.xnestedrecyclerview.manager;

import android.content.Context;
import android.util.AttributeSet;

import androidx.recyclerview.widget.LinearLayoutManager;

public class NestLinearLayoutManager extends LinearLayoutManager implements INestLayoutManager {
    private boolean isInBottom;

    public NestLinearLayoutManager(Context context) {
        super(context);
    }

    public NestLinearLayoutManager(Context context, int orientation, boolean reverseLayout) {
        super(context, orientation, reverseLayout);
    }

    public NestLinearLayoutManager(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
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
