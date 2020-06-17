package com.chao.admin.service.serviceImpl;

import com.chao.admin.dao.PermissionDAO;
import com.chao.admin.dao.RolePermissionDAO;
import com.chao.admin.entity.PermissionEntity;
import com.chao.admin.entity.RoleEntity;
import com.chao.admin.entity.RolePermissionEntity;
import com.chao.admin.entity.UserEntity;
import com.chao.admin.service.PermissionService;
import com.chao.admin.service.RoleService;
import com.chao.admin.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class PermissionServiceImpl implements PermissionService {
    @Autowired
    PermissionDAO permissionDAO;

    @Autowired
    RoleService roleService;

    @Autowired
    UserService userService;

    @Autowired
    RolePermissionDAO rolePermissionDAO;

    @Override
    public void addPermission(PermissionEntity permissionEntity) {
        permissionDAO.save(permissionEntity);
    }

    @Override
    public void updatePermission(PermissionEntity permissionEntity) {
        permissionDAO.save(permissionEntity);
    }

    @Override
    public void deletePermissionById(int id) {
        permissionDAO.deleteById(id);
    }

    @Override
    public List<PermissionEntity> findAll() {
        return permissionDAO.findAll();
    }

    @Override
    public PermissionEntity findPermissionById(int id) {
        return permissionDAO.findById(id).get();
    }

    @Override
    public Set<String> listPermissions(String userName) {
        HashSet<String> res = new HashSet<>();
        UserEntity user = userService.findByName(userName);

        if (user== null){
            return res;
        }

        List<RoleEntity> roleEntityList = roleService.findRolesByUid(user.getId());
        List<RolePermissionEntity> rolePermissionEntityList = new ArrayList<>();

        for (RoleEntity roleEntity : roleEntityList){
            rolePermissionEntityList.addAll(rolePermissionDAO.findRolePermissionEntitiesByRid(roleEntity.getId()));
        }

        for (RolePermissionEntity rp : rolePermissionEntityList){
            res.add(findPermissionById(rp.getPid()).getName());
        }

        return res;
    }

    @Override
    public Set<String> listPermissionURLs(String userName) {
        HashSet<String> res = new HashSet<>();
        UserEntity user = userService.findByName(userName);

        if (user== null){
            return res;
        }

        List<RoleEntity> roleEntityList = roleService.findRolesByUid(user.getId());
        List<RolePermissionEntity> rolePermissionEntityList = new ArrayList<>();

        for (RoleEntity roleEntity : roleEntityList){
            rolePermissionEntityList.addAll(rolePermissionDAO.findRolePermissionEntitiesByRid(roleEntity.getId()));
        }

        for (RolePermissionEntity rp : rolePermissionEntityList){
            res.add(findPermissionById(rp.getPid()).getUrl());
        }

        return res;
    }

    @Override
    public PermissionEntity findByName(String name) {

        return permissionDAO.findByName(name);
    }

    @Override
    public boolean needInterceptor(String requestURI) {

        List<PermissionEntity> ps = findAll();
        for (PermissionEntity p : ps) {
            if (p.getUrl().equals(requestURI))
                return true;
        }
        return false;
    }
}
