package com.project.admin.dao;

import com.project.admin.entity.PermissionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PermissionDAO extends JpaRepository<PermissionEntity,Integer> {
        PermissionEntity findByName(String name);
        public List<PermissionEntity> findPermissionEntitiesByName(String name);
        public void deleteById(int id);
}
