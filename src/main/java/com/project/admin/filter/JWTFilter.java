package com.project.admin.filter;

import com.alibaba.fastjson.JSON;
import com.project.admin.service.PermissionService;
import com.project.admin.shiro.JWTToken;
import com.project.admin.shiro.JWTUtil;
import com.project.admin.utils.ResultBeanFactory;
import com.project.admin.utils.SpringContextUtils;
import com.sun.xml.internal.fastinfoset.util.CharArray;
import org.apache.shiro.web.filter.authc.BasicHttpAuthenticationFilter;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Set;

public class JWTFilter extends BasicHttpAuthenticationFilter {
    @Autowired
    PermissionService permissionService;

    private String username = null;
    //代码的执行流程 preHandle -> isAccessAllowed -> isLoginAttempt -> executeLogin



    /**
     * 判断用户是否想要登入。
     * 检测header里面是否包含Authorization字段即可
     */
    @Override
    protected boolean isLoginAttempt(ServletRequest request, ServletResponse response) {
        System.out.println("尝试登陆");
        HttpServletRequest req = (HttpServletRequest) request;
        String authorization = req.getHeader("Authorization");
        return authorization != null;
    }

    /**
     *
     */
    @Override
    protected boolean executeLogin(ServletRequest request, ServletResponse response) throws Exception {
        System.out.println("执行登陆");
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        String authorization = httpServletRequest.getHeader("Authorization");

        JWTToken token = new JWTToken(authorization);
        // 提交给realm进行登入，如果错误他会抛出异常并被捕获
        getSubject(request, response).login(token);
        username = JWTUtil.getUsername(authorization);
        // 如果没有抛出异常则代表登入成功，返回true
        return true;
    }

    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) {
        System.out.println("访问检查");

        return true;


    }

    /**
     * 对跨域提供支持
     */
    @Override
    protected boolean preHandle(ServletRequest request, ServletResponse response) throws Exception {
        System.out.println("预处理");
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;

        if (isLoginAttempt(request, response)) {
            try {
                executeLogin(request, response);
            } catch (Exception e) {
                error(response);
                return false;
            }
        } else {
            error(response);
            return false;
        }
        if (null == permissionService)
            permissionService = SpringContextUtils.getContext().getBean(PermissionService.class);

        String requestURI = getPathWithinApplication(request);

        System.out.println("requestURI:" + requestURI + " Method:" + httpServletRequest.getMethod());


        if (username != null) {
//            boolean needInterceptor = permissionService.needInterceptor(requestURI);
//            if (!needInterceptor) {
//                return true;
//            } else {

            boolean hasPermission = false;

            Set<String> permissionUrls = permissionService.listPermissionURLs(username);
            Set<String> permissionMethods = permissionService.listPermissionMethods(username, getURL(requestURI));

            for (String url : permissionUrls) {
                // 这就表示当前用户有这个权限

                if (matchURL(url, requestURI)) {

                    for (String method : permissionMethods) {
                        if (((HttpServletRequest) request).getMethod().equals(method)) {
                            hasPermission = true;
                            break;
                        }
                    }
                }
            }

            if (hasPermission)
                return super.preHandle(request, response);
            else {
                error(response);
                return false;
            }

            //}
        }

        return super.preHandle(request, response);

    }

    private String getURL(String URI){
        char[] charArray = URI.toCharArray();
        int index = -1;

        for (int i = charArray.length - 1; i >= 0; i--) {
            if (charArray[i] == '/') {
                if (i == 0) {
                    return "/";
                } else {
                    index = i;
                    break;
                }

            }
        }
        return URI.substring(0, index);
    }

    private boolean matchURL(String URL, String URI) {

        if (URL.equals(URI)) {
            return true;
        }

        return URL.equals(getURL(URI));
    }


    private void error(ServletResponse response) throws IOException {
        HttpServletResponse resp = (HttpServletResponse) response;
        resp.setContentType("application/json; charset=utf-8");
        PrintWriter out = resp.getWriter();
        out.println(JSON.toJSONString(ResultBeanFactory.getResultBean(401, "UNAUTHORIZED", null, false)));
        out.flush();
        out.close();
    }
}