package com.project.admin.web;

import com.project.admin.entity.PermissionEntity;
import com.project.admin.utils.ResultBean;
import com.project.admin.utils.ResultBeanFactory;
import com.project.admin.vmodel.PermissionModel;
import io.swagger.annotations.ApiOperation;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PermissionController extends BaseController {

    @ApiOperation("获取权限列表")
    @RequestMapping(value = "/permission",method = RequestMethod.GET)
    public List<PermissionEntity> listPermission() {

        return permissionService.findAll();
    }

    @ApiOperation("通过id获取权限")
    @RequestMapping(value = "/permission/{id}",method = RequestMethod.GET)
    public ResultBean getPermission(@PathVariable("id") int id) {
        try {
            return ResultBeanFactory.getResultBean(response.getStatus(),"success",permissionService.findPermissionById(id),true);
        }catch (Exception e){
            return ResultBeanFactory.getResultBean(response.getStatus(),e.getMessage(),null,false);
        }

    }

    @ApiOperation("添加权限")
    @RequestMapping(value = "/permission",method = RequestMethod.POST)
    public ResultBean addPermission(@RequestBody PermissionModel permissionModel) {
        try {
            PermissionEntity permissionEntity = new PermissionEntity();
            permissionEntity.setDesc(permissionModel.getDesc());
            permissionEntity.setMethod(permissionModel.getMethod());
            permissionEntity.setName(permissionModel.getName());
            permissionEntity.setUrl(permissionModel.getUrl());

            permissionService.addPermission(permissionEntity);
            return ResultBeanFactory.getResultBean(response.getStatus(),"success",null,true);
        }catch (Exception e){
            return ResultBeanFactory.getResultBean(response.getStatus(),e.getMessage(),null,false);
        }
    }
    @ApiOperation("修改权限")
    @RequestMapping(value = "/permission",method = RequestMethod.PUT)
    public ResultBean editPermission(@RequestBody PermissionModel permissionModel) {

        try {

            PermissionEntity permissionEntity = new PermissionEntity();
            permissionEntity.setDesc(permissionModel.getDesc());
            permissionEntity.setMethod(permissionModel.getMethod());
            permissionEntity.setName(permissionModel.getName());
            permissionEntity.setUrl(permissionModel.getUrl());

            permissionService.addPermission(permissionEntity);
            return ResultBeanFactory.getResultBean(response.getStatus(),"success",null,true);
        }catch (Exception e){
            return ResultBeanFactory.getResultBean(response.getStatus(),e.getMessage(),null,false);
        }
    }

    @ApiOperation("删除权限")
    @RequestMapping(value = "/permission/{id}",method = RequestMethod.DELETE)
    public ResultBean deletePermission(@PathVariable("id") int id) {
        try {
            permissionService.deletePermissionById(id);
            return ResultBeanFactory.getResultBean(response.getStatus(),"success",null,true);
        }catch (Exception e){
            return ResultBeanFactory.getResultBean(response.getStatus(),e.getMessage(),null,false);
        }

    }
}
