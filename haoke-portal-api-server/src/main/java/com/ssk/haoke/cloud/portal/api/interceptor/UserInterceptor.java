package com.ssk.haoke.cloud.portal.api.interceptor;

import com.ssk.haoke.cloud.portal.api.util.impl.RedisTokenManager;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class UserInterceptor implements HandlerInterceptor {
    @Autowired
    RedisTokenManager tokenManager;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String requestURI = request.getRequestURI();
        if("/graphql".equals(requestURI)){
            return true;
        }
        /**
         * 当请求是options时，响应200就代表允许所请求的方法和请求头（可能是框架帮忙实现的，实际得手动设置）
            !--待debug
         */
        if (StringUtils.equalsIgnoreCase("options",request.getMethod())){
            return true;
        }

        response.setCharacterEncoding("utf-8");
        //当请求options有到这里时，该请求不会携带自定义请求头，Authorization，
        //所有会认证失败
        String authorization = request.getHeader("Authorization");
        String info = tokenManager.getUserInfoByToken(authorization);

        if(info == null){
            System.out.println("认证失败");
            // 支持跨域
            response.setHeader("Access-Control-Allow-Origin", "http://localhost:9000");
            response.setHeader("Access-Control-Allow-Methods", "GET,POST,PUT,DELETE,OPTIONS");
            response.setHeader("Access-Control-Allow-Credentials", "true");
            response.setHeader("Access-Control-Allow-Headers", "Content-Type,X-Token,Authorization");
            response.setHeader("Access-Control-Allow-Credentials", "true");
            response.getWriter().write("50000");
            return false;
        }

        System.out.println("拦截器"+request.getRequestURL()+authorization);
        return true;
    }
}
