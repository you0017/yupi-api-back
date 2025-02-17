package com.yupi.yupiinterface;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableAspectJAutoProxy
public class YupiInterfaceApplication {

    public static void main(String[] args) {
        SpringApplication.run(YupiInterfaceApplication.class, args);
    }

}
