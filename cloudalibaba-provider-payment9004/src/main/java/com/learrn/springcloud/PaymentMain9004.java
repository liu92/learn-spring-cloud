package com.learrn.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @ClassName: PaymentMain9004
 * @Description:
 * @Author: lin
 * @Date: 2020/8/25 17:12
 * History:
 * @<version> 1.0
 */
@SpringBootApplication
@EnableDiscoveryClient
public class PaymentMain9004 {
    public static void main(String[] args) {
        SpringApplication.run(PaymentMain9004.class, args);
    }
}
