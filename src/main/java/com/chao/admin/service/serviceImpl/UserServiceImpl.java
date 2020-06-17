package com.chao.admin.service.serviceImpl;

import com.chao.admin.dao.UserDAO;
import com.chao.admin.entity.UserEntity;
import com.chao.admin.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDAO userDAO;

    @Override
    public UserEntity findByName(String name) {
        return userDAO.findByName(name);
    }

    @Override
    public void addUser(UserEntity user) {
        userDAO.save(user);
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
    public List<UserEntity> findAll() {
        return userDAO.findAll();
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
