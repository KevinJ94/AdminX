package com.chao.admin.dao;

import com.chao.admin.entity.RolePermissionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RolePermissionDAO extends JpaRepository<RolePermissionEntity,Integer> {
    public List<RolePermissionEntity> findRolePermissionEntitiesByRid(Integer rid);
    public List<RolePermissionEntity> findRolePermissionEntitiesByPid(Integer pid);
    public void deleteById(int id);
}
