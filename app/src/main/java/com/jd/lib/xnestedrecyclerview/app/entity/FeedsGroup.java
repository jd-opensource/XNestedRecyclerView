package com.jd.lib.xnestedrecyclerview.app.entity;

import java.util.List;

public class FeedsGroup {
    public String groupId;
    public String name;
    public List<FeedsItemEntity> list;

    public int getTotalCount() {
        return null != list ? list.size() : 0;
    }

    public FeedsItemEntity getItemEntity(int i) {
        return null != list && i < list.size() ? list.get(i) : null;
    }
}
