package com.jd.lib.xnestedrecyclerview.app.view;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.jd.lib.xnestedrecyclerview.NestViewPagerView;
import com.jd.lib.xnestedrecyclerview.app.entity.ItemEntity;
import com.jd.lib.xnestedrecyclerview.app.view.nest.FeedsLayout;

import java.util.ArrayList;
import java.util.List;

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.MainHolder> {
    private List<ItemEntity> mList;

    private NestViewPagerView nestViewPagerView;

    public ListAdapter(Context context, List<ItemEntity> list) {
        mList = new ArrayList<>();
        mList.addAll(list);
    }

    @NonNull
    @Override
    public MainHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view;
        if (viewType == 1) {
            view = new FeedsLayout(viewGroup.getContext());
            nestViewPagerView = (NestViewPagerView) view;
        } else {
            view = new TextLayout(viewGroup.getContext());
        }
        return new MainHolder(view);
    }

    @Override
    public int getItemViewType(int position) {
        ItemEntity itemEntity = mList.get(position);
        return null != itemEntity ? itemEntity.type : 0;
    }

    @Override
    public void onBindViewHolder(@NonNull MainHolder mainHolder, int i) {
        if (mainHolder.itemView instanceof FeedsLayout) {
            ((FeedsLayout) mainHolder.itemView).update(mList.get(i));
        }
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public void scrollToTop() {
        if (null != nestViewPagerView) {
            nestViewPagerView.scrollToTop();
        }
    }


    class MainHolder extends RecyclerView.ViewHolder {

        public MainHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
