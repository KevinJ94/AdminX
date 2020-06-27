package com.project.admin.dao;

import com.project.admin.entity.MenuEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MenuDAO extends JpaRepository<MenuEntity,Integer> {
    public void deleteById(int id);
}
