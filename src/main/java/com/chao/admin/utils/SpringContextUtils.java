package com.chao.admin.utils;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

@Component
public class SpringContextUtils implements ApplicationContextAware {
    private static ApplicationContext context;
  
    public void setApplicationContext(ApplicationContext context)
            throws BeansException {
        com.chao.admin.utils.SpringContextUtils.context = context;
    }  
  
    public static ApplicationContext getContext(){
        return context;  
    }  
}  