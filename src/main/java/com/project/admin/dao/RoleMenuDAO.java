package com.project.admin.dao;

import com.project.admin.entity.RoleMenuEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RoleMenuDAO extends JpaRepository<RoleMenuEntity,Integer> {
    public void deleteById(int id);
    public List<RoleMenuEntity> findRoleMenuEntitiesByRid(Integer rid);
    public List<RoleMenuEntity> findRoleMenuEntitiesByMid(Integer mid);
}
