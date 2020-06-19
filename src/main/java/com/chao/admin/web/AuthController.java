package com.chao.admin.web;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class AuthController {

//    @RequestMapping(value = "/login",method = RequestMethod.GET)
//    public String login(Model model) {
//        return "login";
//    }

//    @RequestMapping(value = "/login",method = RequestMethod.POST)
//    public String checkLogin(Model model, String name, String password) {
//        Subject subject = SecurityUtils.getSubject();
//        UsernamePasswordToken token = new UsernamePasswordToken(name, password);
//        try {
//            subject.login(token);
//            Session session = subject.getSession();
//            session.setAttribute("subject", subject);
//            return "redirect:index";
//
//        } catch (AuthenticationException e) {
//            model.addAttribute("error", "验证失败");
//            return "login";
//        }
//    }
//
//    @RequestMapping(value = "/logout")
//    public String logout(Model model) {
//        return "index";
//    }

}
