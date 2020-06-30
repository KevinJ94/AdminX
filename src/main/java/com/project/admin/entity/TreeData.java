package com.project.admin.entity;

import java.util.ArrayList;
import java.util.List;

public class TreeData {

    public TreeData(Integer id, Integer pid, String icon, String title, String index) {
        this.id = id;
        this.pid = pid;
        this.icon = icon;
        this.title = title;
        this.index = index;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getIndex() {
        return index;
    }

    public void setIndex(String index) {
        this.index = index;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public List<TreeData> getChildren() {
        return children;
    }

    public void setChildren(List<TreeData> children) {
        this.children = children;
    }

    public Integer id;
    public Integer pid;
    public String icon;
    public String title;
    public String index;
    public List<TreeData> children = new ArrayList<>();
}
