package com.ssk.haoke.cloud.api.config.redis;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

@Data
@Component
//集群配置
//@ConfigurationProperties(prefix = "spring.redis.cluster")
//单机配置
@ConfigurationProperties(prefix = "spring.redis")
public class ClusterConfigurationProperties {
    private List<String> nodes;
    private Integer maxRedirects;
    //单机
    private String host;
    private Integer port;
}