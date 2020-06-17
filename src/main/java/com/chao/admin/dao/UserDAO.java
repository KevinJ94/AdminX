package com.chao.admin.dao;

import com.chao.admin.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserDAO extends JpaRepository<UserEntity,Integer> {

    public UserEntity findByName(String name);
    public List<UserEntity> findUserEntitiesByName(String name);
    public void deleteById(int id);

}
