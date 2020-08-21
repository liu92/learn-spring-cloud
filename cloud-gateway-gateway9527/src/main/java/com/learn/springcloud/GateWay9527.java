package com.learn.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @ClassName: GateWay9527
 * @Description:
 * @Author: lin
 * @Date: 2020/8/20 11:30
 * History:
 * @<version> 1.0
 */
@SpringBootApplication
@EnableEurekaClient
public class GateWay9527 {

    public static void main(String[] args) {
        SpringApplication.run(GateWay9527.class, args);
    }
}
