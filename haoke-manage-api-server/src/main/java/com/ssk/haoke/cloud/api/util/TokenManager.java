package com.ssk.haoke.cloud.api.util;

public interface TokenManager {

    /**
     * 创建token
     * @param user
     * @return
     */
    String getToken(String  username);

    /**
     * 刷新用户
     * @param token
     */
    void refreshUserToken(String token);

    /**
     * 用户退出登陆
     * @param token
     */
    void loginOff(String token);

    /**
     * 获取用户信息
     * @param token
     * @return
     */
    String getUserInfoByToken(String token);

}