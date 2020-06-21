package com.project.admin.service.serviceImpl;

import com.project.admin.dao.RolePermissionDAO;
import com.project.admin.entity.RoleEntity;
import com.project.admin.entity.RolePermissionEntity;
import com.project.admin.entity.UserEntity;
import com.project.admin.service.RolePermissionService;
import com.project.admin.service.RoleService;
import com.project.admin.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class RolePermissionServiceImpl implements RolePermissionService {

    @Autowired
    RolePermissionDAO rolePermissionDAO;

    @Autowired
    UserService userService;

    @Autowired
    RoleService roleService;


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
    public List<RolePermissionEntity> findRoleEntitiesByUsername(String userName) {

        UserEntity user = userService.findByName(userName);

        if (user== null){
            return null;
        }

        Set<RoleEntity> roleEntitySet = roleService.findRolesByUid(user.getId());
        List<RolePermissionEntity> rolePermissionEntityList = new ArrayList<>();

        for (RoleEntity roleEntity : roleEntitySet){
            rolePermissionEntityList.addAll(rolePermissionDAO.findRolePermissionEntitiesByRid(roleEntity.getId()));
        }
        return rolePermissionEntityList;
    }

    @Override
    public void deleteById(int id) {
        rolePermissionDAO.deleteById(id);
    }
}
