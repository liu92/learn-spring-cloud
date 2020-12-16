package com.myrule;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @ClassName: MySelfRule
 * @Description:
 * @Author: lin
 * @Date: 2020/8/17 22:53
 * History:
 * @<version> 1.0
 */
@Configuration
public class MySelfRule {
    /**
     * @return
     */
    @Bean
    public IRule myRule() {
        return new RandomRule();
    }
}
