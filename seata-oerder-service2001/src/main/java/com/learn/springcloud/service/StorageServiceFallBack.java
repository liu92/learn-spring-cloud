package com.learn.springcloud.service;

import com.learn.springcloud.domain.CommonResult;
import org.springframework.stereotype.Component;

/**
 * @ClassName learn-spring-cloud
 * @Description 描述
 * @Date 2020/12/14 11:06 下午
 * @Author lin
 * @Version 1.0
 */
@Component
public class StorageServiceFallBack implements StorageService {
    @Override
    public CommonResult decrease(Long productId, Integer count) {
        return new CommonResult(404, "fallback");
    }
}
