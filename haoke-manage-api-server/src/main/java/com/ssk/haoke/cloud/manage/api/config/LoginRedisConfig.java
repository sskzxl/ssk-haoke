package com.ssk.haoke.cloud.manage.api.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@Data
@ConfigurationProperties(prefix = "login")
public class LoginRedisConfig {

    private  Long expires;

}
