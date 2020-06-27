package com.project.admin.service;

import com.project.admin.entity.RoleMenuEntity;

import java.util.List;

public interface RoleMenuService {
    public void addRoleMenu(RoleMenuEntity roleMenuEntity);

    public void  updateRoleMenu(RoleMenuEntity roleMenuEntity);

    public void deleteRoleMenuById(int id);

    public List<RoleMenuEntity> findAll();

    public RoleMenuEntity findRoleMenuById(int id);

    public List<RoleMenuEntity> findRoleMenusByRid(Integer rid);

    public List<RoleMenuEntity> findRoleMenusByMid(Integer mid);

    public void deleteById(int id);
}
