package com.chao.admin.web;

import com.auth0.jwt.JWT;
import com.chao.admin.entity.UserEntity;
import com.chao.admin.service.UserService;
import com.chao.admin.shiro.JWTUtil;
import com.chao.admin.utils.ResultBean;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.UnauthorizedException;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class JWTController {
    @Autowired
    UserService userService;


    @GetMapping("/jwt")
    public ResultBean login() {
        System.out.println("jwt login");
        String encodedPassword = new SimpleHash("md5", "abcde", "jPz19y7arvYIGhuUjsb6sQ==", 2).toString();
        //UsernamePasswordToken token = new UsernamePasswordToken("zhang3", "12345");

        try {
            //subject.login(token);
            System.out.println("success");
            return new ResultBean(200, "Login success",JWTUtil.sign("li4", encodedPassword),true);

        } catch (AuthenticationException e) {
            System.out.println("failed");
            throw new UnauthorizedException();
        }

    }
}
