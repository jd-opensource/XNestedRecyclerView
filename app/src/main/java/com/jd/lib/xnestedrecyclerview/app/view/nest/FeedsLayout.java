package com.jd.lib.xnestedrecyclerview.app.view.nest;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;

import com.jd.lib.xnestedrecyclerview.NestViewPagerView;
import com.jd.lib.xnestedrecyclerview.app.entity.ItemEntity;

public class FeedsLayout extends NestViewPagerView {

    protected Context mContext;
    protected int mParentHeight;

    protected ViewGroup tab;
    protected int tabHeight = 200;

    //注意点：用于监听页面高度变化，如隐藏虚拟菜单栏
    private View.OnLayoutChangeListener layoutChangeListener = new View.OnLayoutChangeListener() {
        @Override
        public void onLayoutChange(View v, int left, int top, int right, int bottom, int oldLeft, int oldTop, int oldRight, int oldBottom) {
            int parentHeight = v.getHeight();
            if (parentHeight > 0 && parentHeight != mParentHeight) {
                setHeight(parentHeight);
            }
        }
    };

    public FeedsLayout(Context context) {
        super(context);
        mContext = context;
    }

    public void update(@NonNull ItemEntity itemEntity) {
        adapter = new FeedsViewPagerAdapter(mContext, itemEntity);
        viewPager.setAdapter(adapter);

        initTab(itemEntity);
        setHeight(mParentHeight);
    }

    protected void initTab(ItemEntity model) {
        if (null == tab) {
            tab = new TextTabGroup(getContext(), tabHeight, 40);
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, tabHeight);
            tab.setLayoutParams(layoutParams);
            addView(tab, 0);
        }
        ((TextTabGroup) tab).addTab(model.list, Color.BLACK, Color.RED);
        ((TextTabGroup) tab).setViewPager(viewPager);
    }

    public void setHeight(int parentHeight) {
        this.mParentHeight = parentHeight;

        if (null != tab && null != tab.getLayoutParams()) {
            //折叠屏适配，tabHeight需要重新获取
            tabHeight = tab.getLayoutParams().height;
        } else {
            tabHeight = 0;
        }

        int pagerHeight = getNestListHeight(parentHeight);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, pagerHeight);
        viewPager.setLayoutParams(params);
    }

    private int getNestListHeight(int height) {
        //注意点：如果view高度和ParentRecyclerView一致，建议view高度+1，避免下拉刷新判断的冲突
        return height + 1 - tabHeight;
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (null != getParent()) {
            int parentHeight = ((ViewGroup) getParent()).getHeight();
            if (parentHeight > 0 && parentHeight != mParentHeight) {
                setHeight(parentHeight);
            }
            ((View) getParent()).addOnLayoutChangeListener(layoutChangeListener);
        }
    }

    @Override
    protected void onDetachedFromWindow() {
        if (null != getParent()) {
            ((View) getParent()).removeOnLayoutChangeListener(layoutChangeListener);
        }
        super.onDetachedFromWindow();
    }

}
