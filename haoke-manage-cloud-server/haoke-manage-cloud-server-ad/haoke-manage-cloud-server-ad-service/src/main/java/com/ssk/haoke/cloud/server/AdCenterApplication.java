package com.ssk.haoke.cloud.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

@EnableDiscoveryClient
@EnableFeignClients
@SpringBootApplication
@ComponentScan(basePackages = {"com.ssk.haoke.cloud.server.api",
            "com.ssk.haoke.cloud.server"})
public class AdCenterApplication {
    public static void main(String[] args) {
        new SpringApplication(AdCenterApplication.class).run(args);
    }
}