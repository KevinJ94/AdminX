package com.project.admin.filter;

import com.alibaba.fastjson.JSON;
import com.project.admin.service.PermissionService;
import com.project.admin.shiro.JWTToken;
import com.project.admin.shiro.JWTUtil;
import com.project.admin.utils.ResultBeanFactory;
import com.project.admin.utils.SpringContextUtils;
import org.apache.shiro.web.filter.authc.BasicHttpAuthenticationFilter;
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

    /**
     * 这里我们详细说明下为什么最终返回的都是true，即允许访问
     * 例如我们提供一个地址 GET /article
     * 登入用户和游客看到的内容是不同的
     * 如果在这里返回了false，请求会被直接拦截，用户看不到任何东西
     * 所以我们在这里返回true，Controller中可以通过 subject.isAuthenticated() 来判断用户是否登入
     * 如果有些资源只有登入用户才能访问，我们只需要在方法上面加上 @RequiresAuthentication 注解即可
     * 但是这样做有一个缺点，就是不能够对GET,POST等请求进行分别过滤鉴权(因为我们重写了官方的方法)，但实际上对应用影响不大
     */
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
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;
        httpServletResponse.setHeader("Access-control-Allow-Origin", httpServletRequest.getHeader("Origin"));
        httpServletResponse.setHeader("Access-Control-Allow-Methods", "GET,POST,OPTIONS,PUT,DELETE");
        httpServletResponse.setHeader("Access-Control-Allow-Headers", httpServletRequest.getHeader("Access-Control-Request-Headers"));
        // 跨域时会首先发送一个option请求，这里我们给option请求直接返回正常状态
        if (httpServletRequest.getMethod().equals(RequestMethod.OPTIONS.name())) {
            httpServletResponse.setStatus(HttpStatus.OK.value());
            return false;
        }


        if (isLoginAttempt(request, response)) {
            try {
                executeLogin(request, response);
            } catch (Exception e) {
                HttpServletResponse resp = (HttpServletResponse) response;
                resp.setContentType("application/json; charset=utf-8");
                PrintWriter out = resp.getWriter();
                out.println(JSON.toJSONString(ResultBeanFactory.getResultBean(401, "UNAUTHORIZED", null, false))); // 返回自己的json
                out.flush();
                out.close();
                return false;
            }
        }
        else {
            HttpServletResponse resp = (HttpServletResponse) response;
            resp.setContentType("application/json; charset=utf-8");
            PrintWriter out = resp.getWriter();
            out.println(JSON.toJSONString(ResultBeanFactory.getResultBean(401, "UNAUTHORIZED", null, false))); // 返回自己的json
            out.flush();
            out.close();
            return false;
        }
        if (null == permissionService)
            permissionService = SpringContextUtils.getContext().getBean(PermissionService.class);

        String requestURI = getPathWithinApplication(request);

        //HttpServletRequest httpServletRequest = (HttpServletRequest) request;

        System.out.println("requestURI:" + requestURI + " Method:" + httpServletRequest.getMethod());
        if (username != null) {
            boolean needInterceptor = permissionService.needInterceptor(requestURI);
            if (!needInterceptor) {
                return true;
            } else {

                boolean hasPermission = false;

                Set<String> permissionUrls = permissionService.listPermissionURLs(username);
                for (String url : permissionUrls) {
                    // 这就表示当前用户有这个权限
                    if (url.equals(requestURI)) {
                        hasPermission = true;
                        break;
                    }
                }

                if (hasPermission)
                    return super.preHandle(request, response);
                else {
                    HttpServletResponse resp = (HttpServletResponse) response;
                    resp.setContentType("application/json; charset=utf-8");
                    PrintWriter out = resp.getWriter();
                    out.println(JSON.toJSONString(ResultBeanFactory.getResultBean(401, "UNAUTHORIZED", null, false))); // 返回自己的json
                    out.flush();
                    out.close();
                    return false;
                }

            }
        }

        return super.preHandle(request, response);


    }

}