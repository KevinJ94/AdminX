package com.chao.admin.web;

import com.chao.admin.entity.UserEntity;
import com.chao.admin.service.UserService;
import com.chao.admin.utils.ResultBean;
import com.chao.admin.utils.ResultBeanFactory;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController extends BaseController {



    @RequestMapping(value = "/user",method = RequestMethod.GET)
    public List<UserEntity> listUser(@RequestParam(value = "start", defaultValue = "0") int start, @RequestParam(value = "size", defaultValue = "5") int size) throws Exception {

        return userService.findAll();
    }

    @RequestMapping(value = "/user/{id}",method = RequestMethod.GET)
    public ResultBean getUser(@PathVariable("id") int id) throws Exception {

        return ResultBeanFactory.getResultBean(response.getStatus(),"success",userService.findUserById(id),true);
    }

    @RequestMapping(value = "/user",method = RequestMethod.POST)
    public void addUser(@RequestBody UserEntity userEntity) throws Exception {
        String salt = new SecureRandomNumberGenerator().nextBytes().toString();
        int times = 2;
        String algorithmName = "md5";
        String encodedPassword = new SimpleHash(algorithmName, userEntity.getPassword(), salt, times).toString();
        UserEntity u = new UserEntity();
        u.setName(userEntity.getName());
        u.setPassword(encodedPassword);
        u.setSalt(salt);
        userService.addUser(u);
    }

    @RequestMapping(value = "/user",method = RequestMethod.PUT)
    public void editUser(@RequestBody UserEntity userEntity) throws Exception {
        System.out.println(userEntity.getId());
        System.out.println(userEntity.getName());
        System.out.println(userEntity.getPassword());
        System.out.println(userEntity.getSalt());
    }




}
