package com.yupi.gateway;


import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;

/**
 * Hello world!
 *
 * @author 0.0
 */
@SpringBootApplication
@EnableDubbo
@EnableDiscoveryClient
public class GatewayApplication
{
    public static void main( String[] args )
    {
        SpringApplication.run( GatewayApplication.class, args);
    }

    /*@Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("baudi", r -> r.path("/baidu")
                        .filters(f -> f.rewritePath("/baidu", "/"))
                        .uri("https://www.youyou.love"))
                .build();
    }*/
}
