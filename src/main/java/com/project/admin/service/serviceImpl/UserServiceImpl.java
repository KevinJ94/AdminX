package com.project.admin.service.serviceImpl;

import com.project.admin.dao.UserDAO;
import com.project.admin.model.PageData;
import com.project.admin.entity.UserEntity;
import com.project.admin.entity.UserRoleEntity;
import com.project.admin.service.UserRoleService;
import com.project.admin.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDAO userDAO;
    @Autowired
    private UserRoleService userRoleService;

    @Override
    public UserEntity findByName(String name) {
        return userDAO.findByName(name);
    }

    @Transactional
    @Override
    public void addUser(UserEntity user) {

        UserEntity u = userDAO.saveAndFlush(user);
        UserRoleEntity userRoleEntity = new UserRoleEntity();
        userRoleEntity.setRid(2);
        userRoleEntity.setUid(u.getId());
        userRoleService.addUserRole(userRoleEntity);

    }

    @Override
    public void updateUser(UserEntity user) {

        userDAO.save(user);
    }

    @Override
    public void deleteUserById(int id) {
        userDAO.deleteById(id);
    }

    @Override
    public PageData findAll(int page, int size) {
        PageRequest pr = PageRequest.of(page,size);
        Page<UserEntity> pages = userDAO.findAll(pr);

        List<UserEntity> userEntityList = new ArrayList<>();
        for (UserEntity userEntity : pages){
            userEntityList.add(userEntity);
        }

        return new PageData(size,(int) pages.getTotalElements(),page,userEntityList);
    }

    @Override
    public UserEntity findUserById(int id) {
        return userDAO.findById(id).get();
    }

    @Override
    public List<UserEntity> findUserEntitiesByName(String name) {
        return userDAO.findUserEntitiesByName(name);
    }
}
