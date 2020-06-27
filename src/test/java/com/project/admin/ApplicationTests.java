package com.project.admin;

import com.project.admin.entity.MenuEntity;
import com.project.admin.entity.TreeData;
import com.project.admin.service.MenuService;
import com.project.admin.service.PermissionService;
import com.project.admin.utils.Algorithm;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Set;

@SpringBootTest
class ApplicationTests {

    @Autowired
    PermissionService permissionService;

    @Autowired
    MenuService menuService;


    @Test
    void contextLoads() {
    }

    @Test
    void testUserCRUD() {

        Set<MenuEntity> menuEntitySet = menuService.listMenus("zhang3");
        List<TreeData> treeDataList = Algorithm.constructMenuTree(menuEntitySet);
        for (TreeData node : treeDataList){
            Algorithm.printTreeData(node,1);
        }
    }


}
