package com.project.admin;

import com.project.admin.entity.MenuEntity;
import com.project.admin.model.AllocPermission;
import com.project.admin.service.MenuService;
import com.project.admin.service.PermissionService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.security.AllPermission;
import java.util.ArrayList;
import java.util.List;

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
        AllocPermission allocPermission = new AllocPermission();
        List<Integer> pids = new ArrayList<>();
        allocPermission.setRid(3);
        pids.add(1);
        pids.add(2);
        pids.add(3);
        pids.add(4);
        allocPermission.setPids(pids);
        permissionService.allocPermissions(allocPermission);
        //permissionService.findPermissionById(1);
    }


}
