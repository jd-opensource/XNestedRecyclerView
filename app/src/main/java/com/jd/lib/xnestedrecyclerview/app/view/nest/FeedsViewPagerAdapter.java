package com.jd.lib.xnestedrecyclerview.app.view.nest;

import android.content.Context;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;

import androidx.core.view.ViewCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.jd.lib.xnestedrecyclerview.NestRecyclerView;
import com.jd.lib.xnestedrecyclerview.NestViewPagerAdapter;
import com.jd.lib.xnestedrecyclerview.app.entity.FeedsGroup;
import com.jd.lib.xnestedrecyclerview.app.entity.ItemEntity;

import java.util.ArrayList;

//（1）adapter需继承NestViewPagerAdapter
public class FeedsViewPagerAdapter extends NestViewPagerAdapter {

    private SparseArray<RecyclerView> viewList;
    private Context context;
    private ItemEntity model;

    private ArrayList<FeedsGroup> mTabList;
    private SparseArray<NestRecyclerViewHelper> engineArray = new SparseArray<>();//数据

    public FeedsViewPagerAdapter(Context c, ItemEntity model) {
        context = c;
        this.model = model;
        viewList = new SparseArray<>();
        mTabList = new ArrayList<>();
        if (null != model.list) {
            mTabList.addAll(model.list);
        }
    }


    @Override
    public CharSequence getPageTitle(int position) {
        return position < mTabList.size() ? mTabList.get(position).name : "";
    }

    @Override
    public int getCount() {
        return mTabList.size();
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        RecyclerView recyclerView;
        if (viewList.indexOfKey(position) >= 0) {
            recyclerView = viewList.get(position);
        } else {
            //（2）ViewPager里面的RecyclerView需要使用NestRecyclerView
            recyclerView = new NestRecyclerView(context);
            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(context);

            recyclerView.setLayoutManager(layoutManager);
            recyclerView.setNestedScrollingEnabled(true);
            recyclerView.setOverScrollMode(RecyclerView.OVER_SCROLL_NEVER);
            recyclerView.setFocusable(false);

            NestRecyclerViewHelper nestRecyclerViewHelper;
            if (engineArray.indexOfKey(position) >= 0) {
                nestRecyclerViewHelper = engineArray.get(position);
            } else {
                nestRecyclerViewHelper = new NestRecyclerViewHelper(context,
                        mTabList.get(position));

                engineArray.put(position, nestRecyclerViewHelper);
            }
            nestRecyclerViewHelper.bindView(recyclerView);
            nestRecyclerViewHelper.getData();

            viewList.put(position, recyclerView);
        }
        container.addView(recyclerView);
        return recyclerView;
    }


    @Override
    public boolean isChildOnTop(int position) {
        if (null != viewList.get(position)) {
            return !ViewCompat.canScrollVertically(viewList.get(position), -1);
        }
        return true;
    }

    @Override
    public void setChildOnTop(int position) {
        if (null != viewList.get(position)) {
            viewList.get(position).scrollToPosition(0);
        }
    }

    @Override
    public RecyclerView getCurrent(int position) {
        return viewList.get(position);
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }


    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

}

