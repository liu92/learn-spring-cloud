package com.learn.springcloud.threadfatory;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @ClassName: DefaultThreadFactory
 * @Description: 自定义工厂
 * @Author: lin
 * @Date: 2020/7/24 16:10
 * History:
 * @<version> 1.0
 */
public class DefaultThreadFactory implements ThreadFactory {
    private static final AtomicInteger POOL_NUMBER = new AtomicInteger(1);

    private static ThreadGroup group;

    private final AtomicInteger threadNumber = new AtomicInteger(1);

    private String namePrefix;

    public DefaultThreadFactory() {
    }

    public DefaultThreadFactory(String name) {
        SecurityManager s = System.getSecurityManager();
        group = (s != null) ? s.getThreadGroup() : Thread.currentThread().getThreadGroup();
        namePrefix = "pool-" + POOL_NUMBER.getAndIncrement() + name + "-thread";
    }


    @Override
    public Thread newThread(Runnable r) {
        Thread t = new Thread(group, r, namePrefix + threadNumber.getAndIncrement());
        if (t.isDaemon()) {
            t.setDaemon(true);
        }

        if (t.getPriority() != Thread.NORM_PRIORITY) {
            t.setPriority(Thread.NORM_PRIORITY);
        }
        return t;
    }
}
