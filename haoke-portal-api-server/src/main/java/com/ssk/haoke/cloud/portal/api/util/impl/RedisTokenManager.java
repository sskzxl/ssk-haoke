package com.ssk.haoke.cloud.portal.api.util.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ssk.haoke.cloud.portal.api.config.LoginRedisConfig;
import com.ssk.haoke.cloud.portal.api.util.TokenManager;
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

    public String getToken(String username) {
        //使用uuid作为源token
        String token = UUID.randomUUID().toString().replace("-", "");

        this.redisTemplate.opsForValue().set(token,username,config.getExpires(),TimeUnit.SECONDS);
        return token;
    }

    @Override
    public void refreshUserToken(String token) {
        if(redisTemplate.hasKey(token)){
            redisTemplate.expire(token,config.getExpires(), TimeUnit.SECONDS);
        }
    }

    @Override
    public void loginOff(String token) {
        redisTemplate.delete(token);
    }

    @Override
    public String getUserInfoByToken(String token) {
        if(redisTemplate.hasKey(token)){
            String user = (String) redisTemplate.opsForValue().get(token);
            return user;
        }
        return null;
    }
}
