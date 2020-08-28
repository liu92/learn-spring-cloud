package com.learn.springcloud.controller;

import com.learn.springcloud.domain.CommonResult;
import com.learn.springcloud.domain.Order;
import com.learn.springcloud.service.OrderService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @ClassName: OrderController
 * @Description:
 * @Author: lin
 * @Date: 2020/8/26 16:08
 * History:
 * @<version> 1.0
 */
@RestController
public class OrderController {

    @Resource
    private OrderService orderService;

    /**
     * 创建订单
     * @param order
     * @return
     */
    @GetMapping("/order/create")
    public CommonResult create(Order order){
        orderService.create(order);
        return  new CommonResult(200, "订单创建成功");
    }









}
