package com.project.admin.utils;

public class ResultBean {

    private int status; // 状态码：200是正常调用 500是程序出错

    private String msg; // 返回消息：成功还是失败

    private Object data; // 返回的对象。有时候我们要返回一些数据就放在这个里，如果不需要返回数据则是null

    private Boolean result; // 接口调用的结果 true是成功。false是失败。

    public int getStatus() {

        return status;

    }

    public void setStatus(int status) {

        this.status = status;

    }

    public String getMsg() {

        return msg;

    }

    public void setMsg(String msg) {

        this.msg = msg;

    }

    public Boolean getResult() {

        return result;

    }

    public void setResult(Boolean result) {

        this.result = result;

    }

    public Object getData() {

        return data;

    }

    public void setData(Object data) {

        this.data = data;

    }

    public ResultBean() {

    }

    public ResultBean(int status, String msg, Object data, Boolean result) {

        super();

        this.status = status;

        this.msg = msg;

        this.data = data;

        this.result = result;

    }

    /**
     * 对返回值的封装
     *
     * @param status
     * @param msg
     * @param object
     * @param flag
     * @return
     */
    public static ResultBean result(int status, String msg, Object object, boolean flag) {
        return new ResultBean(status, msg, object, flag);
    }

}
