package com.jd.lib.xnestedrecyclerview.app.view;

import android.content.Context;
import android.graphics.Color;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.jd.lib.xnestedrecyclerview.app.R;
import com.jd.lib.xnestedrecyclerview.app.entity.ItemEntity;

public class TextLayout extends LinearLayout {
    private ImageView imgView;
    private TextView textView;

    public TextLayout(@NonNull Context context) {
        super(context);
        LayoutInflater.from(getContext()).inflate(R.layout.text_layout, this);
    }

    public void update(@NonNull ItemEntity itemEntity) {
        setBackgroundColor(parseColor(itemEntity.backgroundColor, Color.WHITE));
    }

    public static int parseColor(String colorString, int defaultColor) {
        if (TextUtils.isEmpty(colorString)) {
            return defaultColor;
        }
        try {
            return Color.parseColor(colorString.trim());
        } catch (Exception e) {
            e.printStackTrace();
            return defaultColor;
        }
    }
}
