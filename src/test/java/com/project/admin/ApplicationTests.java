package com.project.admin;

import com.project.admin.entity.MenuEntity;
import com.project.admin.entity.RoleEntity;
import com.project.admin.entity.TreeData;
import com.project.admin.service.MenuService;
import com.project.admin.service.PermissionService;
import com.project.admin.service.RoleService;
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
    RoleService roleService;


    @Test
    void contextLoads() {
    }

    @Test
    void testUserCRUD() {
        List<TreeData> treeDataList = Algorithm.constructRoleTree(roleService.findAll());
        for (TreeData node : treeDataList){
            Algorithm.printTreeData(node,1);
        }

        for(RoleEntity roleEntity:roleService.listRoleByUser("zhang3")){
            System.out.println(roleEntity.getName()+"   "+roleEntity.getDesc());
        }
    }


}
