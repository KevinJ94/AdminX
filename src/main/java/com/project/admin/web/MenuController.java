package com.project.admin.web;

import com.project.admin.entity.MenuEntity;
import com.project.admin.utils.Algorithm;
import com.project.admin.utils.ResultBean;
import com.project.admin.utils.ResultBeanFactory;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

@RestController
public class MenuController extends BaseController {

    @ApiOperation("获取菜单列表")
    @RequestMapping(value = "/menu",method = RequestMethod.GET)
    public ResultBean listMenu() {

        return ResultBeanFactory.getResultBean(response.getStatus(),"success",Algorithm.constructMenuTree(menuService.findAll()),true);
    }

    @ApiOperation("通过用户名获取菜单列表")
    @RequestMapping(value = "/getmenu/{name}",method = RequestMethod.GET)
    public ResultBean listMenuByName(@PathVariable("name") String name) {
        return ResultBeanFactory.getResultBean(response.getStatus(),"success",Algorithm.constructMenuTree(menuService.listMenus(name)),true);
    }

    @ApiOperation("通过用户名获取路由列表")
    @RequestMapping(value = "/getrouter/{name}",method = RequestMethod.GET)
    public ResultBean listRouterByName(@PathVariable("name") String name) {

        return ResultBeanFactory.getResultBean(response.getStatus(),"success", menuService.ListRouters(name),true);
    }

    @ApiOperation("通过id获取菜单")
    @RequestMapping(value = "/menu/{id}",method = RequestMethod.GET)
    public ResultBean getMenu(@PathVariable("id") int id) {
        try {
            return ResultBeanFactory.getResultBean(response.getStatus(),"success",menuService.findMenuById(id),true);
        }catch (Exception e){
            return ResultBeanFactory.getResultBean(response.getStatus(),e.getMessage(),null,false);
        }

    }

    @ApiOperation("添加菜单")
    @RequestMapping(value = "/menu",method = RequestMethod.POST)
    public ResultBean addMenu(MenuEntity menuEntity) {
        try {

            menuService.addMenu(menuEntity);
            return ResultBeanFactory.getResultBean(response.getStatus(),"success",null,true);
        }catch (Exception e){
            return ResultBeanFactory.getResultBean(response.getStatus(),e.getMessage(),null,false);
        }
    }
    @ApiOperation("修改菜单")
    @RequestMapping(value = "/menu",method = RequestMethod.PUT)
    public ResultBean editMenu(MenuEntity menuEntity) {

        try {

            MenuEntity m = menuService.findMenuById(menuEntity.getId());
            m.setDesc(menuEntity.getDesc());
            m.setIcon(menuEntity.getIcon());
            m.setIndex(menuEntity.getIndex());
            m.setPath(menuEntity.getPath());
            m.setTitle(menuEntity.getTitle());
            menuService.updateMenu(m);
            return ResultBeanFactory.getResultBean(response.getStatus(),"success",null,true);
        }catch (Exception e){
            return ResultBeanFactory.getResultBean(response.getStatus(),e.getMessage(),null,false);
        }
    }

    @ApiOperation("删除菜单")
    @RequestMapping(value = "/menu/{id}",method = RequestMethod.DELETE)
    public ResultBean deleteMenu(@PathVariable("id") int id) {
        try {
            menuService.deleteMenuById(id);
            return ResultBeanFactory.getResultBean(response.getStatus(),"success",null,true);
        }catch (Exception e){
            return ResultBeanFactory.getResultBean(response.getStatus(),e.getMessage(),null,false);
        }

    }

}
