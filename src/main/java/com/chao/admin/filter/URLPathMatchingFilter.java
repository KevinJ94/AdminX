package com.chao.admin.filter;

import com.alibaba.fastjson.JSONObject;
import com.chao.admin.service.PermissionService;
import com.chao.admin.utils.ResultBean;
import com.chao.admin.utils.ResultBeanFactory;
import com.chao.admin.utils.SpringContextUtils;
import com.sun.deploy.net.HttpRequest;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.UnauthorizedException;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.PathMatchingFilter;
import org.apache.shiro.web.util.WebUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.lang.reflect.Array;
import java.util.Enumeration;
import java.util.Set;

public class URLPathMatchingFilter extends PathMatchingFilter {
    @Autowired
    PermissionService permissionService;

    @Override
    protected boolean onPreHandle(ServletRequest request, ServletResponse response, Object mappedValue)
            throws Exception {
        if(null==permissionService)
            permissionService = SpringContextUtils.getContext().getBean(PermissionService.class);

        String requestURI = getPathWithinApplication(request);

        HttpServletRequest httpServletRequest = (HttpServletRequest)request;

        System.out.println("requestURI:" + requestURI+" Method:"+ httpServletRequest.getMethod());

        Subject subject = SecurityUtils.getSubject();
        // 如果没有登录，就跳转到登录页面
        if (!subject.isAuthenticated()) {
            WebUtils.issueRedirect(request, response, "/login");
            return true;
        }

        // 看看这个路径权限里有没有维护，如果没有维护，一律放行(也可以改为一律不放行)
        //System.out.println("permissionService:"+permissionService);
        boolean needInterceptor = permissionService.needInterceptor(requestURI);
        if (!needInterceptor) {
            return true;
        } else {
            boolean hasPermission = false;
            String userName = subject.getPrincipal().toString();
            Set<String> permissionUrls = permissionService.listPermissionURLs(userName);
            for (String url : permissionUrls) {
                // 这就表示当前用户有这个权限
                if (url.equals(requestURI)) {
                    hasPermission = true;
                    break;
                }
            }

            if (hasPermission)
                return true;
            else {
                UnauthorizedException ex = new UnauthorizedException("当前用户没有访问路径 " + requestURI + " 的权限");

                subject.getSession().setAttribute("ex", ex);

                HttpServletResponse resp = (HttpServletResponse) response;
                resp.setContentType("application/json; charset=utf-8");
                PrintWriter out = resp.getWriter();
                JSONObject json = new JSONObject();
                json.put("data",ResultBeanFactory.getResultBean(401,"UNAUTHORIZED",null,false));
                out.println(json); // 返回自己的json
                out.flush();
                out.close();
                return false;
            }

        }

    }
}