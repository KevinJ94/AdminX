package com.project.admin.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "menu", schema = "adminx", catalog = "")
public class MenuEntity {
    private int id;
    private Integer pid;
    private String title;
    private String index;
    private String icon;
    private String path;
    private String desc;
    private String component;

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "pid")
    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    @Basic
    @Column(name = "title")
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Basic
    @Column(name = "index_")
    public String getIndex() {
        return index;
    }

    public void setIndex(String index) {
        this.index = index;
    }

    @Basic
    @Column(name = "icon")
    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    @Basic
    @Column(name = "path")
    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    @Basic
    @Column(name = "desc_")
    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    @Basic
    @Column(name = "component")
    public String getComponent() {
        return component;
    }

    public void setComponent(String component) {
        this.component = component;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MenuEntity that = (MenuEntity) o;
        return id == that.id &&
                Objects.equals(pid, that.pid) &&
                Objects.equals(title, that.title) &&
                Objects.equals(index, that.index) &&
                Objects.equals(icon, that.icon) &&
                Objects.equals(path, that.path) &&
                Objects.equals(desc, that.desc) &&
                Objects.equals(component, that.component);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, pid, title, index, icon, path, desc, component);
    }
}
