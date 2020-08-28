package com.learn.springcloud.service;

import com.learn.springcloud.domain.CommonResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;

/**
 * 账户service ,通过Feign来调用 账户微服务。
 * @ClassName: AccountService
 * @Description:
 * @Author: lin
 * @Date: 2020/8/26 15:46
 * @History:
 * @<version> 1.0
 */
@FeignClient(value = "seata-account-service")
public interface AccountService {

    /**
     *  通过feign来查找微服务seata-account-service，然后通过post请求
     *  找下的这个方法来进行账户余额扣减操作。
     * @param userId 用户id
     * @param money 扣减金额
     * @return
     */
    @PostMapping("/account/decrease")
    CommonResult decrease(@RequestParam("userId") Long userId,
                          @RequestParam("money")BigDecimal money);
}
