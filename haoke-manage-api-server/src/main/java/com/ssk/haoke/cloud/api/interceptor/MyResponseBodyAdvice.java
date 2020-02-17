package com.ssk.haoke.cloud.api.interceptor;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ssk.haoke.cloud.api.controller.GraphQLController;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import java.time.Duration;
/*
    在controller执行完返回responseBody之前执行
 */
//@ControllerAdvice
public class MyResponseBodyAdvice implements ResponseBodyAdvice {
    @Autowired
    private RedisTemplate<String, String> redisTemplate;
    private ObjectMapper mapper = new ObjectMapper();

    /**
     * 返回true时需要进行处理加入缓存
     * @param returnType
     * @param converterType
     * @return
     */
    @Override
    public boolean supports(MethodParameter returnType, Class converterType) {
        if (returnType.hasMethodAnnotation(GetMapping.class)) {
            return true;
        }
        if (returnType.hasMethodAnnotation(PostMapping.class) &&
                StringUtils.equals(GraphQLController.class.getName(),
                        returnType.getExecutable().getDeclaringClass().getName())) {
            return true;
        }
        return false;
    }

    /**
     * 在响应responseBody之前把数据加入缓存
     * @param body
     * @param returnType
     * @param selectedContentType
     * @param selectedConverterType
     * @param request
     * @param response
     * @return
     */
    @Override
    public Object beforeBodyWrite(Object body,
                                  MethodParameter returnType,
                                  MediaType selectedContentType,
                                  Class selectedConverterType,
                                  ServerHttpRequest request,
                                  ServerHttpResponse response) {
        try {
            String redisKey = RedisCacheInterceptor.createRedisKey(((ServletServerHttpRequest)
                            request).getServletRequest());
            String redisValue;
            if (body instanceof String) {
                //如果是字符串则不需要反序列化处理
                redisValue = (String) body;
            } else {
                redisValue = mapper.writeValueAsString(body);
            }
            //把数据写入缓存，设置过期时间为一个小时
            this.redisTemplate.opsForValue().set(redisKey, redisValue,
                    Duration.ofHours(1));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return body;
    }
}