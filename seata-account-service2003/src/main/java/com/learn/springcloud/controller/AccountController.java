package com.learn.springcloud.controller;

import com.learn.springcloud.domain.CommonResult;
import com.learn.springcloud.service.AccountService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.math.BigDecimal;

/**
 * @ClassName: AccountController
 * @Description:
 * @Author: lin
 * @Date: 2020/8/26 17:18
 * History:
 * @<version> 1.0
 */
@RestController
public class AccountController {

    @Resource
    private AccountService accountService;

    @RequestMapping(value = "/account/decrease")
    public CommonResult decrease(@RequestParam("userId") Long userId,
                                 @RequestParam("money") BigDecimal money){
        accountService.decrease(userId, money);
        return  new CommonResult(200, "扣减账户余额成功");
    }
}
