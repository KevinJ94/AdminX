package com.project.admin.web;

import com.project.admin.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
public class BaseController {
    @Autowired
    public UserService userService;

    @Autowired
    public PermissionService permissionService;

    @Autowired
    public RoleService roleService;

    @Autowired
    public UserRoleService userRoleService;

    @Autowired
    public RolePermissionService rolePermissionService;

    @Autowired(required = false)
    public HttpServletResponse response;

    @Autowired
    public HttpServletRequest request;


}
