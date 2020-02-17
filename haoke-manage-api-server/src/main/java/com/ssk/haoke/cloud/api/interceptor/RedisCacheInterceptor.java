package com.ssk.haoke.cloud.api.interceptor;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * 无法通过拦截器实现把查询到的数据写入到缓存，原因：
 * 1.如果通过重写postHandle如拦截，我们无法从HttpServletResponse中获取数据
 * 因为controller返回的是json数据（@responseBody）
 * 2.如果在处理请求之前拦截，此时还没有得到数据，更不行。
 *      可以通过spring提供的responseBodyAdvice来实现在获取数据后相应之前写入缓存
 */
@Component
public class RedisCacheInterceptor implements HandlerInterceptor {

    @Autowired
    private RedisTemplate<String, String> redisTemplate;
    private static ObjectMapper mapper = new ObjectMapper();

    //在controller之前处理，如为true放行
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //todo
        if (StringUtils.equalsIgnoreCase(request.getMethod(), "options")){
            return true;
        }
        //请求方法是否是get,缓存一般只做查询
        if (!StringUtils.equalsIgnoreCase(request.getMethod(), "get")) {
            //graphql的post查询请求
            if (StringUtils.equalsIgnoreCase(request.getRequestURL(), "/graphql")) {
                return true;
            }
        }
        //查询缓存是否有要的数据
        String data = this.redisTemplate.opsForValue().get(createRedisKey(request));
        if (StringUtils.isEmpty(data)) {
            // 缓存未命中
            return true;
        }
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json; charset=utf-8");
        // 支持跨域
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", "GET,POST,PUT,DELETE,OPTIONS");
        response.setHeader("Access-Control-Allow-Credentials", "true");
        response.setHeader("Access-Control-Allow-Headers", "Content-Type,X-Token");
        response.setHeader("Access-Control-Allow-Credentials", "true");
        response.getWriter().write(data);
        return false;
    }

    public static String createRedisKey(HttpServletRequest request) throws Exception {
        String paramStr = request.getRequestURI();
        Map<String, String[]> parameterMap = request.getParameterMap();
        if (parameterMap.isEmpty()) {
            /*
                由于在拦截器中读取了输入流的数据，在request中的输入流只能读取一次，请求进去Controller时，输
                入流中已经没有数据了，导致获取不到数据
             */
            paramStr += IOUtils.toString(request.getInputStream(), "UTF-8");
        } else {
            paramStr += mapper.writeValueAsString(request.getParameterMap());
        }
        String redisKey = "WEB_DATA_" + DigestUtils.md5Hex(paramStr);
        return redisKey;
    }

}