/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2018 All Rights Reserved.
 */
package com.zhenmin.superboot.seda;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

import com.zhenmin.superboot.log.BootLogUtil;
import com.zhenmin.superboot.transaction.BootRunnable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static java.util.concurrent.TimeUnit.MILLISECONDS;
import static java.util.concurrent.TimeUnit.MINUTES;
import static java.util.concurrent.TimeUnit.SECONDS;

/**
 * SEDA(Staged Event-Driven Architecture)的核心思想是把一个请求处理过程分成几个Stage，
 * 不同资源消耗的Stage使用不同数量的线程来处理，
 * Stage间使用事件驱动的异步通信模式。
 *
 * @author zhenmin
 * @version $Id: AbstractSEDA.java, v 0.1 2018-07-30 下午5:15 zhenmin Exp $$
 */
public abstract class AbstractSEDA<I extends CalData> extends BootLogUtil {

    private static final Logger LOGGER = LoggerFactory.getLogger(AbstractSEDA.class);

    /**
     * 任务队列
     */
    private final BlockingQueue<I> QUEUE = getBlockingQueue();

    private final ThreadPoolExecutor WORKER_POOL = getWorker();

    /**
     * 任务计数器
     */
    private final AtomicInteger CAL_QUEUE_COUNT = new AtomicInteger(0);

    /**
     * 出队计数器
     */
    private final AtomicInteger PULL_QUEUE_COUNT = new AtomicInteger(0);

    /**
     * 执行耗时
     */
    private final AtomicLong INVOKE_TIME = new AtomicLong(0);

    /**
     * 工作线程数量
     */
    private final AtomicInteger WORK_THREAD_COUNT = new AtomicInteger(0);

    /**
     * 活跃线程数量
     */
    private final AtomicInteger ACTIVE_THREAD_COUNT = new AtomicInteger(0);

    public void addData(I calData) {
        //任务队列
        QUEUE.add(calData);
        CAL_QUEUE_COUNT.incrementAndGet();
    }

    /**
     * seda 工作入口
     *
     * @param calData
     */
    protected abstract void sedaWorker(final I calData);

    /**
     * 数据初始化
     */
    public void init() {
        BootThreadFactory threadFactory = new BootThreadFactory(this.getClass().getSimpleName() + "_SEDA");
        ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor(threadFactory);
        executorService.scheduleWithFixedDelay(new BootRunnable() {
            @Override
            public void goRun() {
                try {
                    while (true) {

                        final I calData = QUEUE.poll(200, MILLISECONDS);
                        if (calData == null) {
                            continue;
                        }
                        //出队计数
                        PULL_QUEUE_COUNT.incrementAndGet();
                        WORKER_POOL.execute(new BootRunnable() {
                            @Override
                            public void goRun() {
                                WORK_THREAD_COUNT.incrementAndGet();
                                ACTIVE_THREAD_COUNT.incrementAndGet();
                                long startTime = System.currentTimeMillis();
                                try {
                                    sedaWorker(calData);
                                } catch (Exception e) {
                                    logError(LOGGER, "seda work error,calData:{}", e, calData);
                                } finally {
                                    long endTime = System.currentTimeMillis();
                                    INVOKE_TIME.getAndAdd(endTime - startTime);
                                    ACTIVE_THREAD_COUNT.decrementAndGet();
                                }
                            }
                        });
                    }
                } catch (Exception e) {
                    logError(LOGGER, "seda executor error:{}", e);
                }

            }
        }, 1, 1, SECONDS);

        /**
         * 数据统计
         */
        Executors.newSingleThreadScheduledExecutor().scheduleAtFixedRate(new BootRunnable() {
            @Override
            public void goRun() {
                //服务统计
                int activeThread = ACTIVE_THREAD_COUNT.get();
                int workThreadCount = WORK_THREAD_COUNT.getAndSet(0);
                int pullCount = PULL_QUEUE_COUNT.getAndSet(0);
                logInfo(LOGGER, "SEDA statistic pullCount:{},activeThread:{},workThreadCount{}", pullCount,
                    activeThread, workThreadCount);
            }
        }, 5, 5, MINUTES);

    }

    protected ThreadPoolExecutor getWorker() {
        return new ThreadPoolExecutor(5, 10, 60L, TimeUnit.SECONDS,
            new LinkedBlockingQueue<>(), new BootThreadFactory(this.getClass().getSimpleName() + "_WORKER"),
            new ThreadPoolExecutor.CallerRunsPolicy());
    }

    protected BlockingQueue<I> getBlockingQueue() {
        ArrayBlockingQueue<I> queue = new ArrayBlockingQueue<>(5000);
        return queue;
    }
}