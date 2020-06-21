package com.project.admin.service;

import com.project.admin.entity.PermissionEntity;

import java.util.List;
import java.util.Set;

public interface PermissionService {
    public void addPermission(PermissionEntity permissionEntity);

    public void  updatePermission(PermissionEntity permissionEntity);

    public void deletePermissionById(int id);

    public List<PermissionEntity> findAll();

    public PermissionEntity findPermissionById(int id);

    public Set<String> listPermissions(String userName);

    public Set<String> listPermissionURLs(String userName);

    public Set<String> listPermissionMethods(String userName,String URL);

    PermissionEntity findByName(String name);

    @Deprecated
    public boolean needInterceptor(String requestURI);
}
