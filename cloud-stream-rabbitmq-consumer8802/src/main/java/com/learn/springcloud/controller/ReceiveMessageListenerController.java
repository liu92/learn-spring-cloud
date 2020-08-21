package com.learn.springcloud.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

/**
 * @ClassName: ReceiveMessageListenerController
 * @Description:
 * @Author: lin
 * @Date: 2020/8/21 17:27
 * History:
 * @<version> 1.0
 */
@Component
@EnableBinding(Sink.class)
public class ReceiveMessageListenerController {

    @Value("${server.port}")
    private String serverPort;

    /**
     *  @StreamListener(Sink.INPUT) 消息接收，指定通道
     *  监听的是Sink.INPUT 输入源
     * @param message
     */
    @StreamListener(Sink.INPUT)
    public void input(Message<String> message){
        System.out.println("消费者1号，----->接收到的消息："+
                message.getPayload() +"\t port:" + serverPort);
    }
}
