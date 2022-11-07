package com.jd.lib.xnestedrecyclerview.app.view.nest;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.ClipDrawable;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.StateListDrawable;
import android.graphics.drawable.shapes.RectShape;
import android.util.SparseIntArray;
import android.util.TypedValue;
import android.view.Gravity;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.viewpager.widget.ViewPager;

import com.jd.lib.xnestedrecyclerview.app.entity.FeedsGroup;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class TextTabGroup extends RadioGroup {
    private int tabHeight;
    private int textSize;
    private int mSize;

    protected ViewPager pager;

    protected static final int[][] CHECKED_DEFAULT_STATES = new int[][]{
            {android.R.attr.state_checked, android.R.attr.state_enabled}, {}
    };

    private SparseIntArray ids = new SparseIntArray();
    private SparseIntArray positions = new SparseIntArray();

    private ViewPager.OnPageChangeListener pageListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            check(ids.get(position));
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };

    public TextTabGroup(Context context, int tabHeight, int textSize) {
        super(context);
        setOrientation(HORIZONTAL);
        setBackgroundColor(0x33999999);

        this.tabHeight = tabHeight;
        this.textSize = textSize;

        setOnCheckedChangeListener(new OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                int position = positions.get(checkedId);
                if (null != pager) {
                    pager.setCurrentItem(position);
                }
            }
        });
    }

    public void setViewPager(ViewPager pager) {
        this.pager = pager;
        if (pager.getAdapter() == null) {
            throw new IllegalStateException();
        } else {
            pager.addOnPageChangeListener(this.pageListener);
        }
    }

    public void addTab(List<FeedsGroup> tabList, int unSelectColor, int color) {
        removeAllViews();
        mSize = (null != tabList) ? tabList.size() : 0;
        if (mSize <= 0) {
            return;
        }

        ColorStateList colorStateList = createColorStateList(unSelectColor, color);
        Drawable defaultDrawable = new ColorDrawable(Color.TRANSPARENT),
                checkedDrawable = createBackgroundChecked(color);
        int widthPixels = getContext().getResources().getDisplayMetrics().widthPixels;
        int width = widthPixels / mSize;
        for (int i = 0; i < mSize; i++) {
            RadioButton button = getTab(width, i, tabList.get(i).name);

            button.setTextColor(colorStateList);
            button.setBackground(createBackground(defaultDrawable, checkedDrawable));
            button.setTextSize(TypedValue.COMPLEX_UNIT_PX, textSize);
            button.setText(tabList.get(i).name);

            addView(button);
        }
        check(ids.get(0));
    }

    private RadioButton getTab(int width, int position, String text) {
        RadioButton button = new RadioButton(getContext());
        button.setIncludeFontPadding(false);
        button.setGravity(Gravity.CENTER);
        button.setSingleLine();
        button.setButtonDrawable(android.R.color.transparent);
        int id = generateViewId();
        ids.put(position, id);
        positions.put(id, position);
        button.setId(id);
        button.setTag(position);

        button.setPadding(0, 0, 0, 0);

        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(width, tabHeight);
        button.setLayoutParams(params);

        return button;
    }

    private ColorStateList createColorStateList(int defaultTextColor, int checkedTextColor) {
        return new ColorStateList(CHECKED_DEFAULT_STATES,
                new int[]{checkedTextColor, defaultTextColor});
    }


    private static final AtomicInteger sNextGeneratedId = new AtomicInteger(1);

    public static int generateViewId() {
        for (; ; ) {
            final int result = sNextGeneratedId.get();
            // aapt-generated IDs have the high byte nonzero; clamp to the range under that.
            int newValue = result + 1;
            if (newValue > 0x00FFFFFF) newValue = 1; // Roll over to 1, not 0.
            if (sNextGeneratedId.compareAndSet(result, newValue)) {
                return result;
            }
        }
    }

    static Drawable createBackground(Drawable defaultDrawable, Drawable checkedDrawable) {
        return createBackground(defaultDrawable, checkedDrawable, 500);
    }

    static Drawable createBackground(Drawable defaultDrawable, Drawable checkedDrawable, int level) {
        StateListDrawable drawable = new StateListDrawable();
        drawable.addState(CHECKED_DEFAULT_STATES[0], checkedDrawable);
        drawable.addState(CHECKED_DEFAULT_STATES[1], defaultDrawable);
        drawable.setLevel(level);
        return drawable;
    }

    static Drawable createBackgroundChecked(int highlightColor) {
        return createBackgroundChecked(highlightColor, 500);
    }

    /**
     * 创建选中态的背景图片
     *
     * @param highlightColor 选中态底部线的颜色
     * @return
     */
    static Drawable createBackgroundChecked(int highlightColor, int level) {
        ShapeDrawable selLineDrawable = new ShapeDrawable(new RectShape());
        selLineDrawable.getPaint().setStyle(Paint.Style.FILL);
        selLineDrawable.getPaint().setColor(highlightColor);
        ClipDrawable clipDrawable = new ClipDrawable(selLineDrawable, Gravity.BOTTOM, ClipDrawable.VERTICAL);
        clipDrawable.setLevel(level);
        return clipDrawable;
    }
}
