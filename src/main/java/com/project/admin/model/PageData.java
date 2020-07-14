package com.project.admin.model;

import java.util.List;

public class PageData {
    public int size;
    public int total;
    public int pageNum;

    public int getSize() {
        return size;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }



    public Object data;

    public PageData(int size, int total, int pageNum, Object data) {
        this.size = size;
        this.total = total;
        this.pageNum = pageNum;
        this.data = data;
    }
    public PageData() {
    }
}
