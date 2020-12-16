package com.learn.springcloud.controller;

import com.learn.springcloud.entities.CommonResult;
import com.learn.springcloud.entities.Payment;
import com.learn.springcloud.service.PaymentService;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @ClassName: PaymentController
 * @Description:
 * @Author: lin
 * @Date: 2020/8/15 22:20
 * History:
 * @<version> 1.0
 */
@RestController
@Slf4j
public class PaymentController {

    @Resource
    private PaymentService paymentService;

    @Value("${server.port}")
    private String serverPort;

    @Resource
    private DiscoveryClient discoveryClient;

    @PostMapping(value = "/payment/create")
    public CommonResult create(@RequestBody Payment payment) {
        int result = paymentService.create(payment);
        log.info("*****插入结果:" + result);

        if (result > 0) {
            return new CommonResult(200, "插入成功,serverPort: " + serverPort, result);
        } else {
            return new CommonResult(444, "插入失败", null);
        }
    }

    @GetMapping(value = "/payment/get/{id}")
    public CommonResult getPaymentById(@PathVariable("id") Long id) {
        Payment payment = paymentService.getPaymentById(id);
        log.info("*****查询结果:" + payment);

        if (payment != null) {
            return new CommonResult(200, "查询成功,serverPort: " + serverPort, payment);
        } else {
            return new CommonResult(444, "没有对应记录，查询id:" + id, null);
        }
    }

    /**
     * 服务发现，查看这个注册注册中心由那些服务，
     * 这个发服务下的具体信息
     *
     * @return
     */
    @GetMapping(value = "/payment/discovery")
    public Object discovery() {
        List<String> services = discoveryClient.getServices();
        for (String element : services) {
            log.info("*****element:" + element);
        }
        // 一个微服务下的全部实例
        List<ServiceInstance> instances = discoveryClient.getInstances("CLOUD-PAYMENT-SERVICE");
        for (ServiceInstance instance : instances) {
            log.info(instance.getServiceId() + "\t" + instance.getHost() + "\t" + instance.getPort() + instance.getUri());
        }
        return this.discoveryClient;
    }

    @GetMapping(value = "/payment/lb")
    public String getPaymentLb() {
        return serverPort;
    }


    @GetMapping(value = "/payment/feign/timeout")
    public String paymentFeignTimeout() {
        try {
            Thread.sleep(6);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return serverPort;
    }


    /**
     * 链路跟踪
     *
     * @return
     */
    @GetMapping(value = "/payment/zipkin")
    public String paymentZipkin() {
        return "hi,i'am paymentZipkin server fall back,welcome to atguigu,O(∩_∩)O哈哈~";
    }
}
