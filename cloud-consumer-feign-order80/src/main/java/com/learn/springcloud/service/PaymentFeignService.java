package com.learn.springcloud.service;

import com.learn.springcloud.entities.CommonResult;
import com.learn.springcloud.entities.Payment;
import org.apache.ibatis.annotations.Param;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * 定义接口，这个接口添加@FeignClient 注解来调用 provider服务
 *
 * @ClassName: PaymentFeignService
 * @Description:
 * @Author: lin
 * @Date: 2020/8/18 14:33
 * History:
 * @<version> 1.0
 */
@Component
@FeignClient(value = "CLOUD-PAYMENT-SERVICE")
public interface PaymentFeignService {

    /**
     * 根据id查询
     *
     * @param id
     * @return
     */
    @GetMapping(value = "/payment/get/{id}")
    public CommonResult getPaymentById(@PathVariable("id") Long id);

    /**
     * 超时访问
     *
     * @return
     */
    @GetMapping(value = "/payment/feign/timeout")
    public String paymentFeignTimeout();
}
