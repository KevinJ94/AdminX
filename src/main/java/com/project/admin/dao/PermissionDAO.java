package com.project.admin.dao;

import com.project.admin.entity.PermissionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface PermissionDAO extends PagingAndSortingRepository<PermissionEntity,Integer> {
        PermissionEntity findByName(String name);
        public List<PermissionEntity> findPermissionEntitiesByName(String name);
        @Transactional
        public void deleteById(int id);
}
