package com.project.admin.service.serviceImpl;

import com.project.admin.dao.MenuDAO;
import com.project.admin.dao.RoleMenuDAO;
import com.project.admin.entity.*;
import com.project.admin.model.AllocMenu;
import com.project.admin.service.MenuService;
import com.project.admin.service.RoleMenuService;
import com.project.admin.service.RoleService;
import com.project.admin.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class MenuServiceImpl implements MenuService {
    @Autowired
    MenuDAO menuDAO;

    @Autowired
    RoleMenuDAO roleMenuDAO;

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
    public Set<MenuEntity> findAll() {
        Set<MenuEntity> menuEntitySet = new HashSet<>();
        List<MenuEntity> menuEntityList = menuDAO.findAll();
        for (MenuEntity menu : menuEntityList){
            menuEntitySet.add(menu);
        }
        return menuEntitySet;
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

    @Override
    public Set<MenuEntity> listMenusByRoleId(Integer rid) {
        List<RoleMenuEntity> roleMenuEntityList = roleMenuService.findRoleMenusByRid(rid);
        Set<MenuEntity> menuEntitySet = new HashSet<>();
        for (RoleMenuEntity rm : roleMenuEntityList){
            menuEntitySet.add(findMenuById(rm.getMid()));
        }
        return menuEntitySet;
    }

    @Override
    public List<MenuEntity> ListRouters(String userName) {
        Set<MenuEntity> menuEntitySet = listMenus(userName);
        List<MenuEntity> menuEntityList = new ArrayList<>();
        for (MenuEntity menuEntity : menuEntitySet){
            if (menuEntity.getPath() != null && !menuEntity.getPath().equals("")){
                menuEntityList.add(menuEntity);
            }
        }
        return menuEntityList;
    }

    @Transactional
    @Override
    public void allocMenus(AllocMenu allocMenu) {
        List<RoleMenuEntity> mids = new ArrayList<>();
        for (Integer i : allocMenu.getMids()){
            RoleMenuEntity t = new RoleMenuEntity();
            t.setRid(allocMenu.getRid());
            t.setMid(i);
            mids.add(t);
        }


        roleMenuDAO.deleteAllByRid(allocMenu.getRid());
        roleMenuDAO.saveAll(mids);
    }
}
