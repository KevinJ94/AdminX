package com.project.admin.service.serviceImpl;

import com.project.admin.dao.UserRoleDAO;
import com.project.admin.entity.UserRoleEntity;
import com.project.admin.service.UserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserRoleServiceImpl implements UserRoleService {
    @Autowired
    UserRoleDAO userRoleDAO;

    @Override
    public void addUserRole(UserRoleEntity userRoleEntity) {
        userRoleDAO.save(userRoleEntity);
    }



    @Override
    public void updateUserRole(UserRoleEntity userRoleEntity) {
        userRoleDAO.save(userRoleEntity);
    }

    @Override
    public void deleteUserRoleById(int id) {
        userRoleDAO.deleteById(id);
    }

    @Override
    public List<UserRoleEntity> findAll() {
        return userRoleDAO.findAll();
    }

    @Override
    public UserRoleEntity findUserRoleById(int id) {
        return userRoleDAO.findById(id).get();
    }

    @Override
    public List<UserRoleEntity> findUserRoleEntitiesByUid(Integer uid) {
        return userRoleDAO.findUserRoleEntitiesByUid(uid);
    }

    @Override
    public List<UserRoleEntity> findUserRoleEntitiesByRid(Integer rid) {
        return userRoleDAO.findUserRoleEntitiesByRid(rid);
    }

    @Override
    public void deleteById(int id) {
        userRoleDAO.deleteById(id);
    }
}
