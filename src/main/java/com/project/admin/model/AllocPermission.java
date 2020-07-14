package com.project.admin.model;

import java.util.ArrayList;
import java.util.List;

public class AllocPermission {
    private Integer rid;

    public Integer getRid() {
        return rid;
    }

    public void setRid(Integer rid) {
        this.rid = rid;
    }

    public List<Integer> getPids() {
        return pids;
    }

    public void setPids(List<Integer> pids) {
        this.pids = pids;
    }

    private List<Integer> pids = new ArrayList<>();
}
