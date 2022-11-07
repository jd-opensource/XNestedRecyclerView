package com.jd.lib.xnestedrecyclerview.app.view.nest;

import android.content.Context;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.jd.lib.xnestedrecyclerview.app.R;
import com.jd.lib.xnestedrecyclerview.app.entity.FeedsItemEntity;

public class FeedsItemView extends RelativeLayout {
    private ImageView img;
    private TextView name;

    public FeedsItemView(Context context) {
        super(context);
        LayoutInflater.from(getContext()).inflate(R.layout.feeds_item, this);
        img = findViewById(R.id.product_img);
        name = findViewById(R.id.product_name);
    }

    public void update(@NonNull FeedsItemEntity itemEntity) {
        name.setText(itemEntity.name);
    }
}
