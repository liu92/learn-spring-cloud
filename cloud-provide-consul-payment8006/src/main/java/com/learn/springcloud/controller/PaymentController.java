package com.learn.springcloud.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

/**
 * @ClassName: PaymentController
 * @Description:
 * @Author: lin
 * @Date: 2020/8/17 15:33
 * History:
 * @<version> 1.0
 */
@RestController
@Slf4j
public class PaymentController {


    @Value("${server.port}")
    private String serverPort;

    @RequestMapping(value = "/payment/consul")
    public String paymentConsul() {
        return "springCloud with consul: " + serverPort + "\t" + UUID.randomUUID().toString();
    }
}
