package com.ssk.haoke.cloud.api.config;

import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import com.ssk.haoke.cloud.api.interceptor.UserInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@Configuration
public class WebConfig implements WebMvcConfigurer {
//    @Autowired
//    private RedisCacheInterceptor redisCacheInterceptor;
    @Autowired
    private UserInterceptor userInterceptor;

    public void addInterceptors(InterceptorRegistry registry) {
//        registry.addInterceptor(this.redisCacheInterceptor).addPathPatterns("/**");
//        registry.addInterceptor(this.userInterceptor).addPathPatterns("/**")
//                .excludePathPatterns("/users/login");
    }

    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        FastJsonHttpMessageConverter fastJsonHttpMessageConverter = new FastJsonHttpMessageConverter();
        converters.add(fastJsonHttpMessageConverter);
    }

}