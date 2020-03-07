package com.ssk.haoke.cloud.portal.api.vo.user;

import lombok.Data;

@Data
public class UserResponse {
    private UserData data;
    private UserMeta meta;

    private  UserResponse(UserData data, UserMeta meta) {
        this.data = data;
        this.meta = meta;
    }

    private  UserResponse(UserMeta meta) {
        this.meta = meta;
    }
    public static UserResponse getSuccess(String uid,String token){
        UserMeta meta = new UserMeta();
        meta.setMsg("成功");
        meta.setStatus(200);
        UserData data = new UserData();
        data.setToken(token);
        data.setUid(uid);
        return  new UserResponse(data, meta);
    }
    public static UserResponse getFail(Integer status){
        UserMeta meta = new UserMeta();
        if(status == 1){
            meta.setStatus(status);
            meta.setMsg("密码错误");
        }
        if(status == 2){
            meta.setStatus(status);
            meta.setMsg("用户名不存在");
        }
        return new UserResponse(meta);
    }
    public  boolean isSuccess(){
        if(meta.getStatus() == 200){
            return true;
        }else{
            return false;
        }
    }


}
