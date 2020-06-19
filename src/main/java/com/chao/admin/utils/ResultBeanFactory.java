package com.chao.admin.utils;

public class ResultBeanFactory {

    public static ResultBean getResultBean(int status, String msg, Object data, Boolean result){
        return new ResultBean(status,msg,data,result);
    }
}
