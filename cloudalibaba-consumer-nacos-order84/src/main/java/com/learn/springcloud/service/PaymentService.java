package com.learn.springcloud.service;

import com.learn.springcloud.entities.CommonResult;
import com.learn.springcloud.entities.Payment;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @ClassName: PaymentService
 * @Description:
 * @Author: lin
 * @Date: 2020/8/25 17:38
 * History:
 * @<version> 1.0
 */
@FeignClient(value = "nacos-payment-provider", fallback = PaymentFallbackService.class)
public interface PaymentService {
    @GetMapping("/paymentSQL/{id}")
    CommonResult<Payment> paymentSQL(@PathVariable("id") Long id);
}
