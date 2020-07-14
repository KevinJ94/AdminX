package com.project.admin.service;

import com.project.admin.model.AllocPermission;
import com.project.admin.model.AllocRole;
import com.project.admin.model.PageData;
import com.project.admin.entity.PermissionEntity;

import java.util.List;
import java.util.Set;

public interface PermissionService {
    public void addPermission(PermissionEntity permissionEntity);

    public void  updatePermission(PermissionEntity permissionEntity);

    public void deletePermissionById(int id);

    public PageData findAll(int page, int size);

    public List<PermissionEntity> findAll();

    public PermissionEntity findPermissionById(int id);

    public Set<String> listPermissions(String userName);

    public Set<String> listPermissionURLs(String userName);

    public Set<String> listPermissionMethods(String userName,String URL);

    public Set<PermissionEntity> findPermissionsByRid(Integer roleId);

    PermissionEntity findByName(String name);

    public void allocPermissions(AllocPermission allocPermission);

}
