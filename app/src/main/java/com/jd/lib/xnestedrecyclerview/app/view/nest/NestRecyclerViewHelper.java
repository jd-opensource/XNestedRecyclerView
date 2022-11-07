package com.jd.lib.xnestedrecyclerview.app.view.nest;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.jd.lib.xnestedrecyclerview.app.entity.FeedsGroup;

public class NestRecyclerViewHelper {
    private FeedsAdapter adapter;

    public NestRecyclerViewHelper(Context context, FeedsGroup group) {
        //todo 初始化对象
        adapter = new FeedsAdapter(group);
    }

    public void bindView(@NonNull RecyclerView view) {
        //todo 设置adapter等
        view.setAdapter(adapter);
    }

    public void getData() {
        //todo 如果是异步获取数据的，加载数据
    }
}
