package com.project.admin.model;

import java.util.ArrayList;
import java.util.List;

public class AllocRole {
    private Integer uid;
    private List<Integer> rids = new ArrayList<>();

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public List<Integer> getRids() {
        return rids;
    }

    public void setRids(List<Integer> rids) {
        this.rids = rids;
    }
}
