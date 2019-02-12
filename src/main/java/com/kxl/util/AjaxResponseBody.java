package com.kxl.util;

import java.io.Serializable;

/**
 * Created by Administrator on 2019/1/29.
 */
public class AjaxResponseBody implements Serializable {
    private Integer status;
    private String msg;
    private Object result;
    private String jwtToken;

    public AjaxResponseBody() {
    }

    public AjaxResponseBody(Integer status, String msg, Object result, String jwtToken) {
        this.status = status;
        this.msg = msg;
        this.result = result;
        this.jwtToken = jwtToken;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getResult() {
        return result;
    }

    public void setResult(Object result) {
        this.result = result;
    }

    public String getJwtToken() {
        return jwtToken;
    }

    public void setJwtToken(String jwtToken) {
        this.jwtToken = jwtToken;
    }
}
