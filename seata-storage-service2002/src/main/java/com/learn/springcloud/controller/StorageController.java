package com.learn.springcloud.controller;

import com.learn.springcloud.domain.CommonResult;
import com.learn.springcloud.service.StorageService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @ClassName: StorageController
 * @Description:
 * @Author: lin
 * @Date: 2020/8/26 17:18
 * History:
 * @<version> 1.0
 */
@RestController
public class StorageController {

    @Resource
    private StorageService storageService;

    @RequestMapping("/storage/decrease")
    public CommonResult decrease(@RequestParam("productId") Long productId,
                                 @RequestParam("count") Integer count) {
        storageService.decrease(productId, count);
        return new CommonResult(200, "扣减库存成");
    }
}
