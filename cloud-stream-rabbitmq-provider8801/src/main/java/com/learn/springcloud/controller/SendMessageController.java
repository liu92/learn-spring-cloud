package com.learn.springcloud.controller;

import com.learn.springcloud.service.IMessageProvider;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @ClassName: SendMessageController
 * @Description:
 * @Author: lin
 * @Date: 2020/8/21 17:02
 * History:
 * @<version> 1.0
 */
@RestController
public class SendMessageController {

    @Resource
    private IMessageProvider messageProvider;

    @GetMapping(value = "/sendMessage")
    public String sendMessage() {
        return messageProvider.send();
    }


}
