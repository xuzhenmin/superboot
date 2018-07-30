/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2018 All Rights Reserved.
 */
package com.zhenmin.superboot.seda;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author zhenmin
 * @version $Id: BootThreadFactory.java, v 0.1 2018-07-30 下午5:29 zhenmin Exp $$
 */
public class BootThreadFactory implements ThreadFactory {

    private static final AtomicInteger poolNumber = new AtomicInteger(1);

    private final AtomicInteger threadNumber = new AtomicInteger(1);

    private final ThreadGroup group;

    private final String namePrefix;

    private final boolean isDaemon;

    public BootThreadFactory(String name) {
        this(name, false);
    }

    public BootThreadFactory(String prefix, boolean daemon) {
        SecurityManager s = System.getSecurityManager();
        group = (s != null) ? s.getThreadGroup() : Thread.currentThread().getThreadGroup();
        namePrefix = prefix + "-" + poolNumber.getAndIncrement() + "-thread-";
        isDaemon = daemon;
    }

    @Override
    public Thread newThread(Runnable r) {
        Thread t = new Thread(group, r, namePrefix + threadNumber.getAndIncrement(), 0);
        t.setDaemon(isDaemon);
        if (t.getPriority() != Thread.NORM_PRIORITY) {
            t.setPriority(Thread.NORM_PRIORITY);
        }
        return t;
    }
}