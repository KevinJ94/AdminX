package com.chao.admin.service;

import com.chao.admin.entity.UserEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {

    public UserEntity findByName(String name);

    public void addUser(UserEntity userEntity);

    public void  updateUser(UserEntity userEntity);

    public void deleteUserById(int id);

    public List<UserEntity> findAll();

    public UserEntity findUserById(int id);

    public List<UserEntity> findUserEntitiesByName(String name);

}
