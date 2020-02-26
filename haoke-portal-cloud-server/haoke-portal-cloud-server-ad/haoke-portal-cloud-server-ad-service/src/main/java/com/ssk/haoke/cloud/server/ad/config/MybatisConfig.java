package com.ssk.haoke.cloud.server.ad.config;

import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan("com.ssk.haoke.cloud.server.ad.mapper")
public class MybatisConfig {
/**
* 分页插件
*/
@Bean
public PaginationInterceptor paginationInterceptor() {

    return new PaginationInterceptor();
}
}