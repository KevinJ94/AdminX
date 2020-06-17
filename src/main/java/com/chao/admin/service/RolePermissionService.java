package com.chao.admin.service;

import com.chao.admin.entity.RolePermissionEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface RolePermissionService {

    public void addRolePermission(RolePermissionEntity rolePermissionEntity);

    public void  updateRolePermission(RolePermissionEntity rolePermissionEntity);

    public void deleteRolePermissionById(int id);

    public List<RolePermissionEntity> findAll();

    public RolePermissionEntity findRolePermissionById(int id);

    public List<RolePermissionEntity> findRolePermissionByRid(Integer uid);

    public List<RolePermissionEntity> findRoleEntitiesByPid(Integer pid);

    public void deleteById(int id);

}
