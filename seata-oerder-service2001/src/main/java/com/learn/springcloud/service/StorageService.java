package com.learn.springcloud.service;

import com.learn.springcloud.domain.CommonResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 库存service,是微服务，通过feign来调用库存服务,
 * @ClassName: StorageService
 * @Description:
 * @Author: lin
 * @Date: 2020/8/26 15:46
 * @History:
 * @<version> 1.0
 */
@FeignClient(value = "seata-storage-service")
public interface StorageService {

    /**
     * 通过feign来查找微服务seata-storage-service，然后找下的这个方法来进行扣减库存操作。
     * 扣减库存操作
     * @param productId 对应商品id
     * @param count  扣减数量
     * @return
     */
    @PostMapping("/storage/decrease")
    CommonResult decrease(@RequestParam("productId") Long productId, @RequestParam("count")
                          Integer count);
}
