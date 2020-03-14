package com.ssk.haoke.cloud.portal.api.util;

import com.ssk.haoke.cloud.server.user.api.dto.response.UserRespDto;

public interface TokenManager {

    /**
     * 创建token
     * @param userRespDto
     * @return
     */
    String getToken(UserRespDto userRespDto);

    /**
     * 刷新用户
     * @param token
     */
    void refreshUserToken(String token);

    /**
     * 用户退出登陆
     * @param token
     */
    Boolean loginOff(String token);

    /**
     * 获取用户信息
     * @param token
     * @return
     */
    String getUserInfoByToken(String token);

}