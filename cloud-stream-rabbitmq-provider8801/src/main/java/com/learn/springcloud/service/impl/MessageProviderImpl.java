package com.learn.springcloud.service.impl;

import com.learn.springcloud.service.IMessageProvider;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.MessageChannel;

import javax.annotation.Resource;
import java.util.UUID;

/**
 * @ClassName: MessageProviderImpl
 * @Description:
 * @Author: lin
 * @Date: 2020/8/21 16:15
 * History:
 * @<version> 1.0
 * @EnableBinding 可以理解为我们要定义一个消息生产者的发送管道
 */
@EnableBinding(Source.class) //定义消息的推送管道
public class MessageProviderImpl implements IMessageProvider {

    /**
     * //消息发送管道
     */
    @Resource
    private MessageChannel output;


    @Override
    public String send() {
        String serial = UUID.randomUUID().toString();
        output.send(MessageBuilder.withPayload(serial).build());
        System.out.println("**********serial: " + serial);
        return null;
    }
}
