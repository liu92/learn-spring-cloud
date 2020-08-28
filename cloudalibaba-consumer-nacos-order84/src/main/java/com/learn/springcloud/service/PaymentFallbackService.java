package com.learn.springcloud.service;

import com.learn.springcloud.entities.CommonResult;
import com.learn.springcloud.entities.Payment;
import org.springframework.stereotype.Component;

/**
 * @ClassName: PaymentFallbackService
 * @Description:
 * @Author: lin
 * @Date: 2020/8/25 17:39
 * History:
 * @<version> 1.0
 */
@Component
public class PaymentFallbackService implements PaymentService{

    @Override
    public CommonResult<Payment> paymentSQL(Long id) {
        return new CommonResult<>(444, "服务降级返回，----PaymentFallbackService",
                new Payment(id, "errorSerial"));
    }
}
