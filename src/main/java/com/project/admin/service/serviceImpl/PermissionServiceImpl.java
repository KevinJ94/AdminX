package com.project.admin.service.serviceImpl;

import com.project.admin.dao.PermissionDAO;
import com.project.admin.dao.RolePermissionDAO;
import com.project.admin.entity.PermissionEntity;
import com.project.admin.entity.RoleEntity;
import com.project.admin.entity.RolePermissionEntity;
import com.project.admin.entity.UserEntity;
import com.project.admin.service.PermissionService;
import com.project.admin.service.RolePermissionService;
import com.project.admin.service.RoleService;
import com.project.admin.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


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
    RolePermissionService rolePermissionService;

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

        List<RolePermissionEntity> rolePermissionEntityList = rolePermissionService.findRoleEntitiesByUsername(userName);
        for (RolePermissionEntity rp : rolePermissionEntityList){
            res.add(findPermissionById(rp.getPid()).getName());
        }

        return res;
    }

    @Override
    public Set<String> listPermissionURLs(String userName) {
        HashSet<String> res = new HashSet<>();

        List<RolePermissionEntity> rolePermissionEntityList = rolePermissionService.findRoleEntitiesByUsername(userName);
        for (RolePermissionEntity rp : rolePermissionEntityList){
            res.add(findPermissionById(rp.getPid()).getUrl());
        }
        for (String s : res){
            System.out.println(s);
        }

        return res;
    }

    @Override
    public Set<String> listPermissionMethods(String userName,String URL) {
        HashSet<String> res = new HashSet<>();

        List<RolePermissionEntity> rolePermissionEntityList = rolePermissionService.findRoleEntitiesByUsername(userName);

        for (RolePermissionEntity rp : rolePermissionEntityList){
            PermissionEntity p = findPermissionById(rp.getPid());
            if (URL.equals(p.getUrl())){
                res.add(p.getMethod());
            }

        }
        for (String s : res){
            System.out.println(s);
        }

        return res;
    }

    @Override
    public PermissionEntity findByName(String name) {

        return permissionDAO.findByName(name);
    }

    @Deprecated
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
