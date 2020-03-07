package com.ssk.haoke.cloud.manage.api.config;

import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@Configuration
public class WebConfig implements WebMvcConfigurer {
//    @Autowired
//    private RedisCacheInterceptor redisCacheInterceptor;

//    public void addInterceptors(InterceptorRegistry registry) {
//        registry.addInterceptor(this.redisCacheInterceptor).addPathPatterns("/**");
//        registry.addInterceptor(this.userInterceptor).addPathPatterns("/**")
//                .excludePathPatterns("/users/login");
//    }

    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        FastJsonHttpMessageConverter fastJsonHttpMessageConverter = new FastJsonHttpMessageConverter();
        converters.add(fastJsonHttpMessageConverter);
    }

}