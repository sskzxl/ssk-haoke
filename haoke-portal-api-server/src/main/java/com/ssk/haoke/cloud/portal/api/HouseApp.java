package com.ssk.haoke.cloud.portal.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableDiscoveryClient
@ComponentScan(basePackages = {
        "com.ssk.haoke.cloud.portal.api",
        "com.ssk.haoke.cloud.server.*.api"
})
@EnableFeignClients(basePackages = "com.ssk.haoke.cloud.server.*.api")
public class HouseApp {
    public static void main(String[] args) {
        System.setProperty("es.set.netty.runtime.available.processors","false");
        SpringApplication.run(HouseApp.class, args);
    }
}