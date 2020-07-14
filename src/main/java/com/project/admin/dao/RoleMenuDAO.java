package com.project.admin.dao;

import com.project.admin.entity.RoleMenuEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface RoleMenuDAO extends JpaRepository<RoleMenuEntity,Integer> {
    public void deleteById(int id);
    public List<RoleMenuEntity> findRoleMenuEntitiesByRid(Integer rid);
    public List<RoleMenuEntity> findRoleMenuEntitiesByMid(Integer mid);
    @Transactional
    public void deleteAllByRid(Integer rid);
}
