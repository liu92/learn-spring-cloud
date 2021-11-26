package org.example;

import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 *  Spring Boot 通过@EnableAutoConfiguration开启自动装配，
 * 通过 SpringFactoriesLoader 最终加载META-INF/spring.factories中的自动配置类实现自动装配，
 *
 * 自动配置类其实就是通过@Conditional按需加载的配置类，想要其生效必须引入spring-boot-starter-xxx包实现起步依赖
 * @ClassName ThreadPoolAutoConfiguration
 * @Description TODO
 * @Author lin
 * @Date 2021/11/16
 * @Version 1.0
 */
@Configuration
public class ThreadPoolAutoConfiguration {

    @Bean
    @ConditionalOnClass(ThreadPoolExecutor.class)
    public ThreadPoolExecutor MyThreadPool(){
       return  new ThreadPoolExecutor(10, 10, 10 , TimeUnit.SECONDS, new ArrayBlockingQueue<>(100));
    }
}
