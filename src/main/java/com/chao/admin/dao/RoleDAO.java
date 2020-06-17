package com.chao.admin.dao;

import com.chao.admin.entity.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RoleDAO extends JpaRepository<RoleEntity,Integer> {

    public RoleEntity findByName(String name);
    public List<RoleEntity> findRoleEntitiesByName(String name);
    public void deleteById(int id);

}
