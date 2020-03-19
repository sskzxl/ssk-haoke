package com.ssk.haoke.cloud.server.common;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.NoArgsConstructor;

import java.io.Serializable;
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
//保证序列化Json时,如果是null对象,key也会消失
@NoArgsConstructor
public class ServiceResponse<T> implements Serializable{
    private int resultCode;
    private String resultMsg;
    private T data;
    private ServiceResponse(int resultCode){
        this.resultCode = resultCode;
    }
    private ServiceResponse(int resultCode, String resultMsg){
        this.resultCode = resultCode;
        this.resultMsg = resultMsg;
    }
    private ServiceResponse(int resultCode, String resultMsg, T data){
        this.resultCode = resultCode;
        this.resultMsg = resultMsg;
        this.data = data;
    }
    private ServiceResponse(int resultCode,T data){
        this.data = data;
        this.resultCode = resultCode;
    }
    @JsonIgnore
    //使之不在Json序列化结果当中
    public boolean isSuccess(){
        return this.resultCode == ResponseCode.SUCCESS.getCode();
    }
    public int getResultCode(){
        return resultCode;
    }
    public String getResultMsg(){
        return resultMsg;
    }
    public T getData(){
        return data;
    }
    public static <T> ServiceResponse<T> createBySuccess(){
        return new ServiceResponse<T>(ResponseCode.SUCCESS.getCode());

    }
    public static <T> ServiceResponse<T> createBySuccessResultMsg(String resultMsg){
        return new ServiceResponse<T>(ResponseCode.SUCCESS.getCode(),resultMsg);
    }
    //当data数据是String类型时，因为参数是T类型所以会调用private ServiceResponse(int resultCode,T data)方法，
    //而不会调用private ServiceResponse(int resultCode, String resultMsg)
    public static <T> ServiceResponse<T> createBySuccess(T data){
        return new ServiceResponse<T>(ResponseCode.SUCCESS.getCode(),data);
    }
    public static <T> ServiceResponse<T> createBySuccess(String message,T data){
        return new ServiceResponse<T>(ResponseCode.SUCCESS.getCode(),message,data);
    }
    //错误响应
    public static <T> ServiceResponse<T> createByError(){
        //返回错误代码和普通错误响应消息"error"
        return new ServiceResponse<T>(ResponseCode.ERROR.getCode(),ResponseCode.ERROR.getDesc());
    }
    //自定义响应消息
    public static <T> ServiceResponse<T> createByErrorMessage(String errorMessage){
        return new ServiceResponse<T>(ResponseCode.ERROR.getCode(),errorMessage);
    }
    public static <T> ServiceResponse<T> createByErrorCodeMessage(int errorCode, String errorMessage){
        return new ServiceResponse<T>(errorCode,errorMessage);
    }
}
