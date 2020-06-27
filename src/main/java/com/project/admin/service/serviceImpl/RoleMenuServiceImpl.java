package com.project.admin.service.serviceImpl;

import com.project.admin.dao.RoleMenuDAO;
import com.project.admin.entity.RoleMenuEntity;
import com.project.admin.service.RoleMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleMenuServiceImpl implements RoleMenuService {
    @Autowired
    RoleMenuDAO roleMenuDAO;

    @Override
    public void addRoleMenu(RoleMenuEntity roleMenuEntity) {
        roleMenuDAO.save(roleMenuEntity);
    }

    @Override
    public void updateRoleMenu(RoleMenuEntity roleMenuEntity) {
        roleMenuDAO.save(roleMenuEntity);
    }

    @Override
    public void deleteRoleMenuById(int id) {
        roleMenuDAO.deleteById(id);
    }

    @Override
    public List<RoleMenuEntity> findAll() {
        return roleMenuDAO.findAll();
    }

    @Override
    public RoleMenuEntity findRoleMenuById(int id) {
        return roleMenuDAO.findById(id).get();
    }

    @Override
    public List<RoleMenuEntity> findRoleMenusByRid(Integer rid) {
        return roleMenuDAO.findRoleMenuEntitiesByRid(rid);
    }

    @Override
    public List<RoleMenuEntity> findRoleMenusByMid(Integer mid) {
        return roleMenuDAO.findRoleMenuEntitiesByMid(mid);
    }


    @Override
    public void deleteById(int id) {
        roleMenuDAO.deleteById(id);
    }
}
