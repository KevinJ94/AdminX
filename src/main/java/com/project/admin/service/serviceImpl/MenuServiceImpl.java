package com.project.admin.service.serviceImpl;

import com.project.admin.dao.MenuDAO;
import com.project.admin.entity.MenuEntity;
import com.project.admin.entity.RoleEntity;
import com.project.admin.entity.RoleMenuEntity;
import com.project.admin.entity.UserEntity;
import com.project.admin.service.MenuService;
import com.project.admin.service.RoleMenuService;
import com.project.admin.service.RoleService;
import com.project.admin.service.UserService;
import com.sun.glass.ui.Menu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class MenuServiceImpl implements MenuService {
    @Autowired
    MenuDAO menuDAO;

    @Autowired
    RoleService roleService;

    @Autowired
    UserService userService;

    @Autowired
    RoleMenuService roleMenuService;



    @Override
    public void addMenu(MenuEntity menuEntity) {
        menuDAO.save(menuEntity);
    }

    @Override
    public void updateMenu(MenuEntity menuEntity) {
        menuDAO.save(menuEntity);
    }

    @Override
    public void deleteMenuById(int id) {
        menuDAO.deleteById(id);
    }

    @Override
    public List<MenuEntity> findAll() {
        return menuDAO.findAll();
    }

    @Override
    public MenuEntity findMenuById(int id) {
        return menuDAO.findById(id).get();
    }

    @Override
    public Set<MenuEntity> listMenus(String userName) {
        UserEntity userEntity = userService.findByName(userName);
        Set<RoleEntity> roleEntitySet = roleService.findRolesByUid(userEntity.getId());
        List<RoleMenuEntity> roleMenuEntityList = new ArrayList<>();
        for (RoleEntity r : roleEntitySet){
            roleMenuEntityList.addAll(roleMenuService.findRoleMenusByRid(r.getId()));
        }
        Set<MenuEntity> menuEntitySet = new HashSet<>();
        for (RoleMenuEntity rm : roleMenuEntityList){
            menuEntitySet.add(findMenuById(rm.getMid()));
        }

        return menuEntitySet;
    }
}
