package com.learn.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @ClassName: OrderNacosMain84
 * @Description:
 * @Author: lin
 * @Date: 2020/8/25 17:31
 * History:
 * @<version> 1.0
 */
@SpringBootApplication
@EnableDiscoveryClient
public class OrderNacosMain84 {

    public static void main(String[] args) {
        SpringApplication.run(OrderNacosMain84.class ,args);
    }
}
