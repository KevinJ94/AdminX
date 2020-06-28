package com.project.admin.service;


import com.project.admin.entity.MenuEntity;

import java.util.List;
import java.util.Set;

public interface MenuService {
    public void addMenu(MenuEntity menuEntity);

    public void  updateMenu(MenuEntity menuEntity);

    public void deleteMenuById(int id);

    public Set<MenuEntity> findAll();

    public MenuEntity findMenuById(int id);

    public Set<MenuEntity> listMenus(String userName);

}
