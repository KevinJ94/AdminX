package com.project.admin.service.serviceImpl;

import com.project.admin.dao.PermissionDAO;
import com.project.admin.dao.RolePermissionDAO;
import com.project.admin.entity.RoleEntity;
import com.project.admin.entity.UserRoleEntity;
import com.project.admin.model.AllocPermission;
import com.project.admin.model.PageData;
import com.project.admin.entity.PermissionEntity;
import com.project.admin.entity.RolePermissionEntity;
import com.project.admin.service.PermissionService;
import com.project.admin.service.RolePermissionService;
import com.project.admin.service.RoleService;
import com.project.admin.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


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
    RolePermissionService rolePermissionService;

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
    public PageData findAll(int page, int size) {
        PageRequest pr = PageRequest.of(page,size);

        Page<PermissionEntity> pages = permissionDAO.findAll(pr);

        List<PermissionEntity> permissionEntityList = new ArrayList<>();
        for (PermissionEntity permissionEntity : pages){
            permissionEntityList.add(permissionEntity);
        }

        return new PageData(size,(int) pages.getTotalElements(),page,permissionEntityList);
    }

    @Override
    public List<PermissionEntity> findAll() {
        return (List<PermissionEntity>) permissionDAO.findAll();
    }

    @Override
    public PermissionEntity findPermissionById(int id) {
        return permissionDAO.findById(id).get();
    }

    @Override
    public Set<String> listPermissions(String userName) {
        HashSet<String> res = new HashSet<>();

        List<RolePermissionEntity> rolePermissionEntityList = rolePermissionService.findRoleEntitiesByUsername(userName);
        for (RolePermissionEntity rp : rolePermissionEntityList) {
            res.add(findPermissionById(rp.getPid()).getName());
        }

        return res;
    }

    @Override
    public Set<String> listPermissionURLs(String userName) {
        HashSet<String> res = new HashSet<>();

        List<RolePermissionEntity> rolePermissionEntityList = rolePermissionService.findRoleEntitiesByUsername(userName);
        for (RolePermissionEntity rp : rolePermissionEntityList) {
            res.add(findPermissionById(rp.getPid()).getUrl());
        }
//        for (String s : res){
//            System.out.println(s);
//        }

        return res;
    }

    @Override
    public Set<String> listPermissionMethods(String userName, String URL) {

        HashSet<String> res = new HashSet<>();

        List<RolePermissionEntity> rolePermissionEntityList = rolePermissionService.findRoleEntitiesByUsername(userName);

        for (RolePermissionEntity rp : rolePermissionEntityList) {
            PermissionEntity p = findPermissionById(rp.getPid());
            if (URL.equals(p.getUrl())) {
                res.add(p.getMethod());
            }

        }
//        for (String s : res){
//            System.out.println(s);
//        }

        return res;
    }

    @Override
    public Set<PermissionEntity> findPermissionsByRid(Integer roleId) {

        List<RolePermissionEntity> rolePermissionEntityList = rolePermissionService.findRolePermissionByRid(roleId);
        Set<PermissionEntity> permissionEntitySet = new HashSet<>();
        for(RolePermissionEntity rolePermissionEntity : rolePermissionEntityList){
            permissionEntitySet.add(findPermissionById(rolePermissionEntity.getPid()));
        }

        return permissionEntitySet;
    }

    @Override
    public PermissionEntity findByName(String name) {

        return permissionDAO.findByName(name);
    }


    @Transactional
    @Override
    public void allocPermissions(AllocPermission allocPermission) {
        List<RolePermissionEntity> pids = new ArrayList<>();

        for (Integer i : allocPermission.getPids()){
            RolePermissionEntity t = new RolePermissionEntity();
            t.setRid(allocPermission.getRid());
            t.setPid(i);
            pids.add(t);
        }

        rolePermissionDAO.deleteAllByRid(allocPermission.getRid());
        rolePermissionDAO.saveAll(pids);
    }


}
