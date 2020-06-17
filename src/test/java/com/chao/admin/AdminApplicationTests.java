package com.chao.admin;

import com.chao.admin.dao.RoleDAO;
import com.chao.admin.service.PermissionService;
import com.chao.admin.service.RoleService;
import com.chao.admin.service.UserRoleService;
import com.chao.admin.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashSet;
import java.util.Set;

@SpringBootTest
class AdminApplicationTests {

    @Autowired
    PermissionService permissionService;




    @Test
    void contextLoads() {
    }

    @Test
    void testUserCRUD() {
//        UserEntity ue = new UserEntity();
//        ue.setName("chaojiang");
//        ue.setPassword("123456");
//        ue.setSalt("abcde");
//        userService.addUser(ue);
//        System.out.println("added a new user");
//        ue = userService.findByName("chaojiang");
//        System.out.println("find by name tested");
//        ue = userService.findUserById(ue.getId());
//        System.out.println("find by id tested");
//        ue.setName("jiang");
//        userService.updateUser(ue)
//        userService.findUserByName("jiang");
        Set<String> names =  permissionService.listPermissions("zhang3");
        for (String name : names){
            System.out.println(name);
        }


    }


}
