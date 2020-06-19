package com.project.admin.service.serviceImpl;

import com.project.admin.dao.RolePermissionDAO;
import com.project.admin.entity.RolePermissionEntity;
import com.project.admin.service.RolePermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RolePermissionServiceImpl implements RolePermissionService {

    @Autowired
    RolePermissionDAO rolePermissionDAO;

    @Override
    public void addRolePermission(RolePermissionEntity rolePermissionEntity) {
        rolePermissionDAO.save(rolePermissionEntity);
    }

    @Override
    public void updateRolePermission(RolePermissionEntity rolePermissionEntity) {
        rolePermissionDAO.save(rolePermissionEntity);
    }

    @Override
    public void deleteRolePermissionById(int id) {
        rolePermissionDAO.deleteById(id);
    }

    @Override
    public List<RolePermissionEntity> findAll() {
        return rolePermissionDAO.findAll();
    }

    @Override
    public RolePermissionEntity findRolePermissionById(int id) {
        return rolePermissionDAO.findById(id).get();
    }

    @Override
    public List<RolePermissionEntity> findRolePermissionByRid(Integer uid) {
        return rolePermissionDAO.findRolePermissionEntitiesByRid(uid);
    }

    @Override
    public List<RolePermissionEntity> findRoleEntitiesByPid(Integer pid) {
        return rolePermissionDAO.findRolePermissionEntitiesByPid(pid);
    }

    @Override
    public void deleteById(int id) {
        rolePermissionDAO.deleteById(id);
    }
}
