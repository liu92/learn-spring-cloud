package org.example;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.concurrent.ThreadPoolExecutor;

/**
 *  Spring Boot 通过@EnableAutoConfiguration开启自动装配，
 * 通过 SpringFactoriesLoader 最终加载META-INF/spring.factories中的自动配置类实现自动装配，
 *
 * 自动配置类其实就是通过@Conditional按需加载的配置类，想要其生效必须引入spring-boot-starter-xxx包实现起步依赖
 * @ClassName TestThreadPool
 * @Description TODO
 * @Author lin
 * @Date 2021/11/16
 * @Version 1.0
 */
@SpringBootTest
public class TestThreadPool {

    @Autowired
    private ThreadPoolExecutor myThreadPool;


    @Test
    public void test(){
        System.out.println("corePoolSize ==" + myThreadPool.getCorePoolSize());
    }
}
