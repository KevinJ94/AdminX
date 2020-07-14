package com.project.admin.web;

import com.project.admin.model.PageData;
import com.project.admin.entity.PermissionEntity;
import com.project.admin.utils.ResultBean;
import com.project.admin.utils.ResultBeanFactory;
import com.project.admin.model.PermissionModel;
import io.swagger.annotations.ApiOperation;

import org.springframework.web.bind.annotation.*;

@RestController
public class PermissionController extends BaseController {

    @ApiOperation("获取权限列表")
    @RequestMapping(value = "/permission",method = RequestMethod.GET)
    public ResultBean listPermission(PageData pageData) {
        try {

            PageData pd = permissionService.findAll(pageData.pageNum,pageData.size);
            return ResultBeanFactory.getResultBean(response.getStatus(),"success",pd,true);
        }catch (Exception e){
            return ResultBeanFactory.getResultBean(response.getStatus(),e.getMessage(),null,false);
        }
    }

    @ApiOperation("获取权限列表")
    @RequestMapping(value = "/allpermission",method = RequestMethod.GET)
    public ResultBean listAllPermission() {
        try {

            return ResultBeanFactory.getResultBean(response.getStatus(),"success",permissionService.findAll(),true);
        }catch (Exception e){
            return ResultBeanFactory.getResultBean(response.getStatus(),e.getMessage(),null,false);
        }
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
    public ResultBean addPermission(PermissionModel permissionModel) {
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
    public ResultBean editPermission(PermissionEntity permissionEntity) {

        try {

            PermissionEntity p = permissionService.findPermissionById(permissionEntity.getId());
            p.setDesc(permissionEntity.getDesc());
            p.setMethod(permissionEntity.getMethod());
            p.setName(permissionEntity.getName());
            p.setUrl(permissionEntity.getUrl());

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
