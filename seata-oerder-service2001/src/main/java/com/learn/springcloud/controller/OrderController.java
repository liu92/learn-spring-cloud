package com.learn.springcloud.controller;

import com.learn.springcloud.domain.CommonResult;
import com.learn.springcloud.domain.Order;
import com.learn.springcloud.service.IdGeneratorSnowflake;
import com.learn.springcloud.service.OrderService;
import com.learn.springcloud.threadfatory.DefaultThreadFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.concurrent.*;

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

    @Resource
    private IdGeneratorSnowflake idGeneratorSnowflake;

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


    @GetMapping(value = "/snowflake")
   public  String getIdBySnowFlake(){
       ThreadPoolExecutor threadPool = new ThreadPoolExecutor(5,
               20,60, TimeUnit.SECONDS,new LinkedBlockingDeque<>(10),
               new DefaultThreadFactory("-动态线程数"));
       int count = 20;
       for (int i = 0; i < count ; i++) {
           threadPool.submit(()->{
               System.out.println(idGeneratorSnowflake.snowflakeId() + "\t");
           });
       }
       threadPool.shutdown();
       
       return "hello snowflake";
   }






}
