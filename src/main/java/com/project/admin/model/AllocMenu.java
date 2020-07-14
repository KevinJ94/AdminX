package com.project.admin.model;

import java.util.ArrayList;
import java.util.List;

public class AllocMenu {
    private Integer rid;

    public Integer getRid() {
        return rid;
    }

    public void setRid(Integer rid) {
        this.rid = rid;
    }

    public List<Integer> getMids() {
        return mids;
    }

    public void setMids(List<Integer> mids) {
        this.mids = mids;
    }

    private List<Integer> mids = new ArrayList<>();
}
