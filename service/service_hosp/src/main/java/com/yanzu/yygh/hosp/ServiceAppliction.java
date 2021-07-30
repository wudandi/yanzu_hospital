package com.yanzu.yygh.hosp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

/**
 * @auther 吴彦祖
 * @date 2021/6/1
 */
@SpringBootApplication
@ComponentScan(basePackages = "com.yanzu.yygh")
@EnableDiscoveryClient
@EnableFeignClients(basePackages = "com.yanzu.yygh")
public class ServiceAppliction {
    public static void main(String[] args) {
        SpringApplication.run(ServiceAppliction.class,args);
    }
}
