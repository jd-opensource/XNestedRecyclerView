package com.jd.lib.xnestedrecyclerview;

public interface OnNestFlingListener {
    void onNestFling(int velocityX, int velocityY);

    boolean canParentScroll();
}
