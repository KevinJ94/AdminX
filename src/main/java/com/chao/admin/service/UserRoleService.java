package com.chao.admin.service;

import com.chao.admin.entity.UserRoleEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserRoleService {
    public void addUserRole(UserRoleEntity userRoleEntity);

    public void  updateUserRole(UserRoleEntity userRoleEntity);

    public void deleteUserRoleById(int id);

    public List<UserRoleEntity> findAll();

    public UserRoleEntity findUserRoleById(int id);

    public List<UserRoleEntity> findUserRoleEntitiesByUid(Integer uid);

    public List<UserRoleEntity> findUserRoleEntitiesByRid(Integer rid);

    public void deleteById(int id);
}
