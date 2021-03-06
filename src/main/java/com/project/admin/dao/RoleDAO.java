package com.project.admin.dao;

import com.project.admin.entity.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RoleDAO extends JpaRepository<RoleEntity,Integer> {

    public RoleEntity findByName(String name);
    public List<RoleEntity> findRoleEntitiesByName(String name);
    public List<RoleEntity> findRoleEntitiesByPid(Integer pid);
    public void deleteById(int id);

}
