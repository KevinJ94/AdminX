package com.project.admin.web;

import com.project.admin.entity.MenuEntity;
import com.project.admin.entity.RoleEntity;
import com.project.admin.entity.TreeData;
import com.project.admin.utils.Algorithm;
import com.project.admin.utils.ResultBean;
import com.project.admin.utils.ResultBeanFactory;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;


@RestController
public class RoleController extends BaseController {

    @ApiOperation("获取角色列表")
    @RequestMapping(value = "/role",method = RequestMethod.GET)
    public ResultBean listRole() {

        return ResultBeanFactory.getResultBean(response.getStatus(),"success",Algorithm.constructRoleTree(roleService.findAll()),true);
    }

    @ApiOperation("通过用户名获取角色列表")
    @RequestMapping(value = "/getrole/{name}",method = RequestMethod.GET)
    public ResultBean listRoleByName(@PathVariable("name") String name) {
        return ResultBeanFactory.getResultBean(response.getStatus(),"success",roleService.listRoleByUser(name),true);
    }


    @ApiOperation("通过id获取角色")
    @RequestMapping(value = "/role/{id}",method = RequestMethod.GET)
    public ResultBean getRole(@PathVariable("id") int id) {
        try {
            return ResultBeanFactory.getResultBean(response.getStatus(),"success",roleService.findRoleById(id),true);
        }catch (Exception e){
            return ResultBeanFactory.getResultBean(response.getStatus(),e.getMessage(),null,false);
        }

    }

    @ApiOperation("添加角色")
    @RequestMapping(value = "/role",method = RequestMethod.POST)
    public ResultBean addRole(RoleEntity roleEntity) {
        try {

            roleService.addRole(roleEntity);
            return ResultBeanFactory.getResultBean(response.getStatus(),"success",null,true);
        }catch (Exception e){
            return ResultBeanFactory.getResultBean(response.getStatus(),e.getMessage(),null,false);
        }
    }
    @ApiOperation("修改角色")
    @RequestMapping(value = "/role",method = RequestMethod.PUT)
    public ResultBean editRole(RoleEntity roleEntity) {

        try {

            roleService.addRole(roleEntity);
            return ResultBeanFactory.getResultBean(response.getStatus(),"success",null,true);
        }catch (Exception e){
            return ResultBeanFactory.getResultBean(response.getStatus(),e.getMessage(),null,false);
        }
    }

    @ApiOperation("删除角色")
    @RequestMapping(value = "/role/{id}",method = RequestMethod.DELETE)
    public ResultBean deleteRole(@PathVariable("id") int id) {
        try {
            menuService.deleteMenuById(id);
            return ResultBeanFactory.getResultBean(response.getStatus(),"success",null,true);
        }catch (Exception e){
            return ResultBeanFactory.getResultBean(response.getStatus(),e.getMessage(),null,false);
        }

    }

}
