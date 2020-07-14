package com.project.admin.service;

import com.project.admin.model.PageData;
import com.project.admin.entity.UserEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {

    public UserEntity findByName(String name);

    public void addUser(UserEntity userEntity);

    public void  updateUser(UserEntity userEntity);

    public void deleteUserById(int id);

    public PageData findAll(int page, int size);

    public UserEntity findUserById(int id);

    public List<UserEntity> findUserEntitiesByName(String name);

}
