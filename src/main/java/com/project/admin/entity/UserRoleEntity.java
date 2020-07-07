package com.project.admin.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "user_role", schema = "adminx", catalog = "")
@JsonIgnoreProperties({ "handler","hibernateLazyInitializer" })
public class UserRoleEntity {
    private int id;
    private Integer uid;
    private Integer rid;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "uid")
    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    @Basic
    @Column(name = "rid")
    public Integer getRid() {
        return rid;
    }

    public void setRid(Integer rid) {
        this.rid = rid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserRoleEntity that = (UserRoleEntity) o;
        return id == that.id &&
                Objects.equals(uid, that.uid) &&
                Objects.equals(rid, that.rid);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, uid, rid);
    }
}
