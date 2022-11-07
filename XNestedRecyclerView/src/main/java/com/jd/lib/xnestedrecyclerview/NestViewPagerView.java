package com.jd.lib.xnestedrecyclerview;

import android.content.Context;
import android.widget.LinearLayout;

import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

public class NestViewPagerView extends LinearLayout {
    protected ViewPager viewPager;
    protected NestViewPagerAdapter adapter;

    public NestViewPagerView(Context context) {
        super(context);
        setOrientation(VERTICAL);
        viewPager = new ViewPager(context);
        addView(viewPager);
    }

    public void scrollToTop() {
        if (null != adapter) {
            adapter.setChildOnTop(viewPager.getCurrentItem());
        }
    }

    public boolean isChildOnTop() {
        if (null != adapter) {
            return adapter.isChildOnTop(viewPager.getCurrentItem());
        }
        return true;
    }


    public RecyclerView getCurrent() {
        if (null != adapter) {
            return adapter.getCurrent(viewPager.getCurrentItem());
        }
        return null;
    }
}
