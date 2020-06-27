package com.project.admin.web;

import com.project.admin.entity.UserEntity;
import com.project.admin.utils.Algorithm;
import com.project.admin.utils.ResultBean;
import com.project.admin.utils.ResultBeanFactory;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController extends BaseController {


    @ApiOperation("获取用户列表")
    @RequestMapping(value = "/user",method = RequestMethod.GET)
    public List<UserEntity> listUser() {

        return userService.findAll();
    }

    @ApiOperation("通过id获取用户")
    @RequestMapping(value = "/user/{id}",method = RequestMethod.GET)
    public ResultBean getUser(@PathVariable("id") int id) {

        return ResultBeanFactory.getResultBean(response.getStatus(),"success",userService.findUserById(id),true);
    }

    @ApiOperation("添加用户")
    @RequestMapping(value = "/user",method = RequestMethod.POST)
    public ResultBean addUser(@RequestBody UserEntity userEntity) {
        try {
            String salt = Algorithm.generateSalt();
            String encodedPassword = Algorithm.encodePassword(userEntity.getPassword(),salt);
            UserEntity u = new UserEntity();
            u.setName(userEntity.getName());
            u.setPassword(encodedPassword);
            u.setSalt(salt);
            u.setEnable(1);
            userService.addUser(u);
            return ResultBeanFactory.getResultBean(response.getStatus(),"success",null,true);
        }catch (Exception e){
            return ResultBeanFactory.getResultBean(response.getStatus(),e.getMessage(),null,false);
        }
    }
    @ApiOperation("修改用户")
    @RequestMapping(value = "/user",method = RequestMethod.PUT)
    public ResultBean editUser(@RequestBody UserEntity userEntity) {


        try {
            String salt = new SecureRandomNumberGenerator().nextBytes().toString();
            int times = 2;
            String algorithmName = "md5";
            String encodedPassword = new SimpleHash(algorithmName, userEntity.getPassword(), salt, times).toString();
            UserEntity u = new UserEntity();
            u.setName(userEntity.getName());
            u.setPassword(encodedPassword);
            u.setSalt(salt);
            u.setEnable(userEntity.getEnable());
            userService.updateUser(u);
            return ResultBeanFactory.getResultBean(response.getStatus(),"success",null,true);
        }catch (Exception e){
            return ResultBeanFactory.getResultBean(response.getStatus(),e.getMessage(),null,false);
        }
    }
    @ApiOperation("禁用用户")
    @RequestMapping(value = "/user/{id}",method = RequestMethod.DELETE)
    public ResultBean deleteUser(@PathVariable("id") int id) {

        try {
            UserEntity u = userService.findUserById(id);
            u.setEnable(0);
            userService.updateUser(u);
            return ResultBeanFactory.getResultBean(response.getStatus(),"success",null,true);
        }catch (Exception e){
            return ResultBeanFactory.getResultBean(response.getStatus(),e.getMessage(),null,false);
        }
    }


}
