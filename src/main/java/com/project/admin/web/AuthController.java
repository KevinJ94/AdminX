package com.project.admin.web;

import com.project.admin.shiro.JWTUtil;
import com.project.admin.utils.ResultBean;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController extends BaseController {

    @GetMapping("/login")
    public ResultBean login() {
        System.out.println("jwt login");
        String encodedPassword = new SimpleHash("md5", "abcde", "jPz19y7arvYIGhuUjsb6sQ==", 2).toString();

        try {
            return new ResultBean(response.getStatus(), "Login success", JWTUtil.sign("li4", encodedPassword),true);

        } catch (AuthenticationException e) {
            return new ResultBean(response.getStatus(), "Login failed", e,false);
        }
    }

}
