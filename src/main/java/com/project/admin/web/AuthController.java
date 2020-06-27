package com.project.admin.web;

import com.project.admin.entity.UserEntity;
import com.project.admin.shiro.JWTUtil;
import com.project.admin.utils.Algorithm;
import com.project.admin.utils.ResultBean;
import com.project.admin.vmodel.UserLogin;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController extends BaseController {

    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public ResultBean login(UserLogin userLogin) {
        System.out.println(userLogin.getName()+userLogin.getPassword());

        UserEntity userEntity = userService.findByName(userLogin.getName());
        if (userEntity == null){
            return new ResultBean(response.getStatus(), "Username error", null,false);
        }


        String encodedPassword = Algorithm.encodePassword(userLogin.getPassword(),userEntity.getSalt());
        if (!encodedPassword.equals(userEntity.getPassword())){
            return new ResultBean(response.getStatus(), "Username or password error", null,false);
        }

        try {
            return new ResultBean(response.getStatus(), "Login success", JWTUtil.sign(userEntity.getName(), encodedPassword),true);

        } catch (AuthenticationException e) {
            return new ResultBean(response.getStatus(), "Login failed", e,false);
        }
    }

}
