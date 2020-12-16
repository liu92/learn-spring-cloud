package com.learn.springcloud.service;

import org.springframework.web.bind.annotation.RequestParam;

/**
 * 库存service
 *
 * @ClassName: StorageService
 * @Description:
 * @Author: lin
 * @Date: 2020/8/26 17:09
 * History:
 * @<version> 1.0
 */
public interface StorageService {

    /**
     * 减库存
     *
     * @param productId
     * @param count
     */
    void decrease(Long productId, Integer count);
}
