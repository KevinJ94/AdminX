package com.project.admin.router;


import java.util.List;

public class Router {
    public String path;
    public String component;
    public String redirect;
    public List<Router> children;
}
