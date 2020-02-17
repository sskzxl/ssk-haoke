package com.ssk.haoke.cloud.api.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan("com.ssk.haoke.cloud.api.mapper")
public class MybatisConfig {
}
