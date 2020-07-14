package com.project.admin.web;

import com.project.admin.entity.MenuEntity;
import com.project.admin.entity.PermissionEntity;
import com.project.admin.entity.RoleEntity;
import com.project.admin.entity.UserEntity;
import com.project.admin.model.AllocMenu;
import com.project.admin.model.AllocPermission;
import com.project.admin.model.AllocRole;
import com.project.admin.shiro.JWTUtil;
import com.project.admin.utils.Algorithm;
import com.project.admin.utils.ResultBean;
import com.project.admin.utils.ResultBeanFactory;
import com.project.admin.model.UserLogin;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authc.AuthenticationException;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@RestController
public class AuthController extends BaseController {

    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public ResultBean login(UserLogin userLogin) {
        //System.out.println(userLogin.getName()+userLogin.getPassword());

        UserEntity userEntity = userService.findByName(userLogin.getName());
        if (userEntity == null){
            return new ResultBean(response.getStatus(), "Username error", null,false);
        }


        String encodedPassword = Algorithm.encodePassword(userLogin.getPassword(),userEntity.getSalt());
        if (!encodedPassword.equals(userEntity.getPassword())){
            return new ResultBean(response.getStatus(), "Username or password error", null,false);
        }

        if(userEntity.getEnable() == 0){
            return new ResultBean(response.getStatus(), "Login failed, user is not enable!", null,false);
        }

        try {
            return new ResultBean(response.getStatus(), "Login success", JWTUtil.sign(userEntity.getName(), encodedPassword),true);

        } catch (AuthenticationException e) {
            return new ResultBean(response.getStatus(), "Login failed", e,false);
        }
    }

    @ApiOperation("通过用户id读取分配角色")
    @RequestMapping(value = "/allocrole/{id}",method = RequestMethod.GET)
    public ResultBean getRoles(@PathVariable("id") int id) {
        try {
            Set<RoleEntity> roleEntitySet = roleService.findRolesByUid(id);
            AllocRole allocRole = new AllocRole();
            allocRole.setUid(id);
            for (RoleEntity roleEntity : roleEntitySet){
                allocRole.getRids().add(roleEntity.getId());
            }

            return ResultBeanFactory.getResultBean(response.getStatus(),"success",allocRole,true);
        }catch (Exception e){
            return ResultBeanFactory.getResultBean(response.getStatus(),e.getMessage(),null,false);
        }
    }

    @ApiOperation("写入分配角色")
    @RequestMapping(value = "/allocrole",method = RequestMethod.POST)
    public ResultBean setRoles(AllocRole allocRole) {
        try {

            roleService.allocRoles(allocRole);
            return ResultBeanFactory.getResultBean(response.getStatus(),"success",null,true);
        }catch (Exception e){
            return ResultBeanFactory.getResultBean(response.getStatus(),e.getMessage(),null,false);
        }
    }

    @ApiOperation("通过角色id读取分配菜单")
    @RequestMapping(value = "/allocmenu/{id}",method = RequestMethod.GET)
    public ResultBean getMenus(@PathVariable("id") int id) {
        try {
            Set<MenuEntity> menuEntitySet = menuService.listMenusByRoleId(id);
            AllocMenu allocMenu = new AllocMenu();
            allocMenu.setRid(id);
            for (MenuEntity menuEntity : menuEntitySet){
                allocMenu.getMids().add(menuEntity.getId());
            }

            return ResultBeanFactory.getResultBean(response.getStatus(),"success",allocMenu,true);
        }catch (Exception e){
            return ResultBeanFactory.getResultBean(response.getStatus(),e.getMessage(),null,false);
        }
    }

    @ApiOperation("写入分配菜单")
    @RequestMapping(value = "/allocmenu",method = RequestMethod.POST)
    public ResultBean setMenus(AllocMenu allocMenu) {
        try {
            menuService.allocMenus(allocMenu);
            return ResultBeanFactory.getResultBean(response.getStatus(),"success",null,true);
        }catch (Exception e){
            return ResultBeanFactory.getResultBean(response.getStatus(),e.getMessage(),null,false);
        }
    }

    @ApiOperation("通过角色id读取分配权限")
    @RequestMapping(value = "/allocpermission/{id}",method = RequestMethod.GET)
    public ResultBean getPermissions(@PathVariable("id") int id) {
        try {
            Set<PermissionEntity> permissionEntitySet = permissionService.findPermissionsByRid(id);
            AllocPermission allocPermission = new AllocPermission();
            allocPermission.setRid(id);
            for (PermissionEntity permissionEntity : permissionEntitySet){
                allocPermission.getPids().add(permissionEntity.getId());
            }

            return ResultBeanFactory.getResultBean(response.getStatus(),"success",allocPermission,true);
        }catch (Exception e){
            return ResultBeanFactory.getResultBean(response.getStatus(),e.getMessage(),null,false);
        }
    }

    @ApiOperation("写入分配权限")
    @RequestMapping(value = "/allocpermission",method = RequestMethod.POST)
    public ResultBean setPermissions(AllocPermission allocPermission) {
        try {

            permissionService.allocPermissions(allocPermission);
            return ResultBeanFactory.getResultBean(response.getStatus(),"success",null,true);
        }catch (Exception e){
            return ResultBeanFactory.getResultBean(response.getStatus(),e.getMessage(),null,false);
        }
    }


}
