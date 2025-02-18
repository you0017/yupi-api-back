package com.yupi.gateway;


import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * Hello world!
 *
 * @author 0.0
 */
@SpringBootApplication(exclude={DataSourceAutoConfiguration.class, HibernateJpaAutoConfiguration.class})
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
