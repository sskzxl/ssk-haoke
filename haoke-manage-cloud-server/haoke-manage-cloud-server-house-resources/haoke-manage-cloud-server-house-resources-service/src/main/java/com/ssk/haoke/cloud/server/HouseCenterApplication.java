package com.ssk.haoke.cloud.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableFeignClients
@EnableDiscoveryClient
@ComponentScan(basePackages = {"com.ssk.haoke.cloud.server.house.api",
        "com.ssk.haoke.cloud.server.house"})
public class HouseCenterApplication {
    public static void main(String[] args) {
        new SpringApplication(HouseCenterApplication.class).run(args);
    }
}