package com.jd.lib.xnestedrecyclerview;

import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.PagerAdapter;

public abstract class NestViewPagerAdapter extends PagerAdapter {

    public abstract boolean isChildOnTop(int position);

    public abstract void setChildOnTop(int position);

    public abstract RecyclerView getCurrent(int position);

}
