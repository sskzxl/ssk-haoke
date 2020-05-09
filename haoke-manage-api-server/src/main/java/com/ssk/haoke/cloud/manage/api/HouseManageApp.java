package com.ssk.haoke.cloud.manage.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableDiscoveryClient
//@ComponentScan(basePackages = {
//    "com.ssk.haoke.cloud.api",
//        "com.ssk.haoke.cloud.server.api"
//})
@EnableFeignClients(basePackages = {
        "com.ssk.haoke.cloud.manage.api",
        "com.ssk.haoke.cloud.server.*.api",
})
public class HouseManageApp {
    public static void main(String[] args) {
        System.setProperty("es.set.netty.runtime.available.processors","false");
        SpringApplication.run(HouseManageApp.class, args);
    }
}