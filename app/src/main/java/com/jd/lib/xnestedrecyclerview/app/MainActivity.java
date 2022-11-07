package com.jd.lib.xnestedrecyclerview.app;

import android.os.Bundle;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.jd.lib.xnestedrecyclerview.manager.NestLinearLayoutManager;
import com.jd.lib.xnestedrecyclerview.ParentRecyclerView;
import com.jd.lib.xnestedrecyclerview.app.entity.FeedsGroup;
import com.jd.lib.xnestedrecyclerview.app.entity.FeedsItemEntity;
import com.jd.lib.xnestedrecyclerview.app.entity.ItemEntity;
import com.jd.lib.xnestedrecyclerview.app.view.ListAdapter;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    ImageView topBtn;

    ListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
    }

    private void initView() {
        recyclerView = findViewById(R.id.recycler_view);
        adapter = new ListAdapter(this, getList());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new NestLinearLayoutManager(this, RecyclerView.VERTICAL, false));
        ((ParentRecyclerView) recyclerView).setAllowNestScroll(true);

        topBtn = findViewById(R.id.top_btn);
        topBtn.setOnClickListener(v -> {
            if(null != recyclerView && null != adapter) {
                recyclerView.scrollToPosition(0);
                adapter.scrollToTop();
            }
        });
    }

    private List<ItemEntity> getList() {
        List<ItemEntity> list = new ArrayList<>();
        int count = 7;
        for (int i = 0; i < count; i++) {
            ItemEntity entity = new ItemEntity();
            entity.type = 0;
            list.add(entity);
        }

        list.add(getFeedsEntity());

        return list;
    }

    private ItemEntity getFeedsEntity() {
        ItemEntity entity = new ItemEntity();
        entity.type = 1;

        List<FeedsGroup> list = new ArrayList<>();

        FeedsGroup group1 = new FeedsGroup();
        group1.name = "111";
        group1.list = getFeedsItemList();
        list.add(group1);

        FeedsGroup group2 = new FeedsGroup();
        group2.name = "222";
        group2.list = getFeedsItemList();
        list.add(group2);

        FeedsGroup group3 = new FeedsGroup();
        group3.name = "333";
        group3.list = getFeedsItemList();
        list.add(group3);

        entity.list = list;

        return entity;
    }

    private List<FeedsItemEntity> getFeedsItemList() {
        List<FeedsItemEntity> list = new ArrayList<>();

        FeedsItemEntity item1 = new FeedsItemEntity();
        item1.name = "吉列（Gillette） 剃须刀刮胡刀手动 非吉利 锋速3经典京东豪华装（1刀架+9刀头）";

        FeedsItemEntity item2 = new FeedsItemEntity();
        item2.name = "DMK进口牛奶 欧德堡（Oldenburger）全脂纯牛奶1L*12盒 新年年货礼盒 早餐奶 高钙奶 整箱家庭装";

        FeedsItemEntity item3 = new FeedsItemEntity();
        item3.name = "国联 翡翠生虾仁 1kg/袋 净重 156-198只（BAP认证）国产白虾仁 海鲜水产";

        FeedsItemEntity item4 = new FeedsItemEntity();
        item4.name = "西麦 水果燕麦片 营养代餐 早餐食品 干吃零食 酸奶好搭档 冷冲烘焙干脆水果燕麦片500g（25g*20小袋）";

        FeedsItemEntity item5 = new FeedsItemEntity();
        item5.name = "十月稻田 稻花香大米 东北大米 东北香米 5kg 当季新米";

        int size = 3;
        for (int i = 0; i < size; i++) {
            list.add(item1);
            list.add(item2);
            list.add(item3);
            list.add(item4);
            list.add(item5);
        }

        return list;
    }
}