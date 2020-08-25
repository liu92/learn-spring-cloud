package com.learn.springcloud.handler;

import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.learn.springcloud.entities.CommonResult;

/**
 * @ClassName: CustomerBlockHandler
 * @Description:
 * @Author: lin
 * @Date: 2020/8/25 15:56
 * History:
 * @<version> 1.0
 */
public class CustomerBlockHandler {

    public static CommonResult handlerException(BlockException exception) {
        return new CommonResult(444, "客户自定义，global handlerException---1");
    }

    public static CommonResult handlerException2(BlockException exception) {
        return new CommonResult(444, "客户自定义，global handlerException---2");
    }
}
