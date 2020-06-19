package com.chao.admin.web;

import com.chao.admin.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class BaseController {
    @Autowired
    public UserService userService;

    @Autowired(required = false)
    public HttpServletResponse response;

    @Autowired
    public HttpServletRequest request;




}
