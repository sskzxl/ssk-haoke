package com.ssk.haoke.cloud.portal.api.util.impl;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ssk.haoke.cloud.portal.api.config.LoginRedisConfig;
import com.ssk.haoke.cloud.portal.api.util.TokenManager;
import com.ssk.haoke.cloud.server.user.api.dto.response.UserRespDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
@Component
public class RedisTokenManager implements TokenManager ,Serializable {
    @Autowired
    RedisTemplate redisTemplate;
    @Autowired
    LoginRedisConfig config;
    @Autowired
    ObjectMapper objectMapper;
    public final static Logger logger = LoggerFactory.getLogger(RedisTokenManager.class);
    public String getToken(UserRespDto userRespDto) {
        //使用uuid作为源token
        String token = UUID.randomUUID().toString().replace("-", "");
        String user = JSON.toJSONString(userRespDto);

        this.redisTemplate.opsForValue().set(token,user,config.getExpires(),TimeUnit.SECONDS);
        return token;
    }

    @Override
    public void refreshUserToken(String token) {
        if(redisTemplate.hasKey(token)){
            redisTemplate.expire(token,config.getExpires(), TimeUnit.SECONDS);
        }
    }

    @Override
    public Boolean loginOff(String token) {
        return redisTemplate.delete(token);
    }

    @Override
    public String getUserInfoByToken(String token) {
        if(null != token && redisTemplate.hasKey(token)){
            String user = (String) redisTemplate.opsForValue().get(token);
            return user;
        }
        return null;
    }
}
