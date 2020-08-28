package com.learn.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * 库存模块，主启动类
 * @ClassName: StorageMainApp2002
 * @Description:
 * @Author: lin
 * @Date: 2020/8/26 17:08
 * History:
 * @<version> 1.0
 */
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
@EnableDiscoveryClient
@EnableFeignClients
public class StorageMainApp2002 {
    public static void main(String[] args) {
        SpringApplication.run(StorageMainApp2002.class, args);
    }
}
