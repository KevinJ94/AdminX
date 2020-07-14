package com.project.admin.dao;

import com.project.admin.entity.RolePermissionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface RolePermissionDAO extends JpaRepository<RolePermissionEntity,Integer> {
    public List<RolePermissionEntity> findRolePermissionEntitiesByRid(Integer rid);
    public List<RolePermissionEntity> findRolePermissionEntitiesByPid(Integer pid);
    public void deleteById(int id);
    @Transactional
    public void deleteAllByRid(Integer rid);
}
