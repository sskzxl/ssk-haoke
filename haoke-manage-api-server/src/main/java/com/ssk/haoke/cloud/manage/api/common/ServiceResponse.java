package com.ssk.haoke.cloud.manage.api.common;

//封装高复用响应对象
public class ServiceResponse  <T> {
    private String msg;
    private int status;
    private T data;

    private ServiceResponse(String msg, int status, T data) {
        this.msg = msg;
        this.status = status;
        this.data = data;
    }
    private ServiceResponse(String msg, int status) {
        this.msg = msg;
        this.status = status;
    }
    public static ServiceResponse ok(String msg , int status){
        return new ServiceResponse(msg,status);
    }
    public static <T>ServiceResponse ok(String msg , int status,T data){
        return new ServiceResponse(msg,status,data);
    }

}
