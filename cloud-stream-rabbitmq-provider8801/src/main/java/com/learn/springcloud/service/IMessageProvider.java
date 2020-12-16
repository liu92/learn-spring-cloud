package com.learn.springcloud.service;

/**
 * @ClassName: IMessageProvider
 * @Description:
 * @Author: lin
 * @Date: 2020/8/21 16:15
 * @History:
 * @<version> 1.0
 */
public interface IMessageProvider {
    /**
     * 消息发送
     *
     * @return
     */
    String send();
}
