package com.yupi.yupiinterface;

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableAspectJAutoProxy
@EnableDubbo
@EnableDiscoveryClient
public class YupiInterfaceApplication {

    public static void main(String[] args) {
        SpringApplication.run(YupiInterfaceApplication.class, args);
    }

}
