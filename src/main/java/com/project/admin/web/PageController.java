package com.project.admin.web;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PageController extends BaseController {
    @RequestMapping(value = "/index",method = RequestMethod.GET)
    public String index(Model model) {
        return "index";
    }

    @RequestMapping(value = "/test",method = RequestMethod.GET)
    public String test(Model model) {
        return "test";
    }

    @RequestMapping(value = "/users",method = RequestMethod.GET)
    public String userMng(Model model) throws Exception{

        return "users";
    }

}
