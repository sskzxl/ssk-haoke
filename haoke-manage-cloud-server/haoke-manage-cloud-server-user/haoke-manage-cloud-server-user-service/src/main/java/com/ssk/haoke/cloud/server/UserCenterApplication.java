package com.ssk.haoke.cloud.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableFeignClients
@EnableDiscoveryClient
@ComponentScan(basePackages = {"com.ssk.haoke.cloud.server.user.api",
        "com.ssk.haoke.cloud.server.user"})
public class UserCenterApplication {
    public static void main(String[] args) {
        new SpringApplication(UserCenterApplication.class).run(args);
    }
}