package com.project.admin.dao;

import com.project.admin.entity.UserRoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface UserRoleDAO extends JpaRepository<UserRoleEntity,Integer> {
    public List<UserRoleEntity> findUserRoleEntitiesByUid(Integer uid);
    public List<UserRoleEntity> findUserRoleEntitiesByRid(Integer rid);
    public void deleteById(int id);
    @Transactional
    public void deleteAllByUid(Integer uid);
}
