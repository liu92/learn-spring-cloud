package com.learn.springcloud.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName: ConfigClientController
 * @Description:
 * @Author: lin
 * @Date: 2020/8/21 11:02
 * History:
 * @<version> 1.0
 */
@RestController
@RefreshScope
public class ConfigClientController {

    @Value("${server.port}")
    private String serverPort;

    /**
     * 这里获取的是中心配置 中的配置信息
     */
    @Value("${config.info}")
    private String configInfo;

    @GetMapping("/configInfo")
    public String getConfigInfo() {
        return "serverPort= " + serverPort + "\t configInfo=" + configInfo;
    }
}
