package com.chao.admin.dao;

import com.chao.admin.entity.UserRoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRoleDAO extends JpaRepository<UserRoleEntity,Integer> {
    public List<UserRoleEntity> findUserRoleEntitiesByUid(Integer uid);
    public List<UserRoleEntity> findUserRoleEntitiesByRid(Integer rid);
    public void deleteById(int id);
}
