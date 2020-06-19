package com.project.admin.exception;

import com.project.admin.utils.ResultBean;
import com.project.admin.utils.ResultBeanFactory;
import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.NativeWebRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@ControllerAdvice
public class DefaultExceptionHandler {
	@Autowired(required = false)
	public HttpServletResponse response;

	@Autowired
	public HttpServletRequest request;

	@ExceptionHandler({ UnauthorizedException.class })
	@ResponseStatus(HttpStatus.UNAUTHORIZED)
	public ResultBean processUnauthenticatedException(NativeWebRequest request, UnauthorizedException e) {
		return ResultBeanFactory.getResultBean(response.getStatus(),"UNAUTHORIZED",null,false);
	}

	@ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public String illegalArgumentHandler(IllegalArgumentException e){
        return e.toString();
    }


}
