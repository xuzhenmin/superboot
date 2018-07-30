/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2018 All Rights Reserved.
 */
package com.zhenmin.superboot.transaction;

import java.util.concurrent.Future;

import com.zhenmin.superboot.log.BootLogUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionSynchronizationAdapter;
import org.springframework.transaction.support.TransactionSynchronizationManager;

/**
 * 自定义事务，可实现分布式事务入口
 *
 * TODO 事务控制可以使用redis + mysql实现。redis作并发控制，mysql作持久化
 *
 * @author zhenmin
 * @version $Id: TradeCoreTransaction.java, v 0.1 2018-07-27 下午3:28 zhenmin Exp $$
 */
public class TradeCoreTransaction extends BootLogUtil {

    private static final Logger LOGGER = LoggerFactory.getLogger(TradeCoreTransaction.class);

    /**
     * 异步任务线程池
     */
    private ThreadPoolTaskExecutor assertExecutor;

    public void init() {
        //任务线程池初始化
        assertExecutor = new ThreadPoolTaskExecutor();
        assertExecutor.setCorePoolSize(10);
        assertExecutor.setMaxPoolSize(10);
        assertExecutor.setQueueCapacity(100);
        assertExecutor.initialize();
    }

    @Transactional(rollbackFor = Exception.class)
    public void tradeCore() {
        /**
         * 初始化数据
         */
        KVStore kvStore = new KVStore();

        AssertTransactionSync sync = new AssertTransactionSync(kvStore);

        TransactionSynchronizationManager.registerSynchronization(sync);

    }

    /**
     * 发送异步事务型消息
     *
     *
     * TODO 消息机制  p生产者 m消息服务 c消费者
     *
     * 1普通消息：
     * 发消息(p) --> 入库(m) --> 收消息(c)
     * 确认消息(c) --> 入库(m) --> 消息回执(p)
     *
     * 2重试消息：
     * 发消息(p) --> 入库(m) --> 收消息(c)
     * 投递失败 定时重试(m) --> 收消息(c)  --> 成功(m) -> 回执(p)
     *
     * 消息重试时间间隔可以参考 1  2 4 6 8 10 ,5 ,20,100
     *
     * 3事务型消息：
     * 发消息(p) --> 入库(m)
     * 更新事务状态(p) --> 状态变更(m) --> 收消息(c)
     * 确认消息(c) --> 入库(m) --> 消息回执(p)
     *
     * 4事务回查：
     * 发消息(p) --> 入库(m)
     * 更新事务状态(p) --> 状态变更 失败(m)
     * 回查事务状态(p) --> 更新事务状态(m) --> 收消息(c)
     * 确认消息(c) --> 入库(m) --> 消息回执(p)
     *
     * @param body
     * @param commit
     */
    private void sendMessage(String body, boolean commit) {
        logInfo(LOGGER, "send uncommit message:{}", commit);
    }

    /**
     * 自定义事务处理类
     * TODO 可以用过ThreadLocal记录一些上下文信息，起到在事务提交前后传递信息的作用
     */
    class AssertTransactionSync extends TransactionSynchronizationAdapter {

        private KVStore kvStore;

        public AssertTransactionSync() {

        }

        public AssertTransactionSync(KVStore kvStore) {
            this.kvStore = kvStore;
        }

        /**
         * 事务提交之前
         *
         * @param readOnly
         */
        @Override
        public void beforeCommit(boolean readOnly) {


            /*
            * 可发送未提交的消息 sendMessage(message,false)
            */
            sendMessage("message", false);
        }

        @Override
        public void afterCompletion(int status) {

            if (status == STATUS_COMMITTED) {
                //提交
                assertExecutor.execute(new BootRunnable() {
                    @Override
                    public void goRun() {
                        //do something
                        //更新redis中key的失效时间
                    }
                });
            }
            /**
             * 事务回滚。正常来说事务回滚后，需要删除掉redis的key
             * 但是 使用getFuture的方式，需要以下情况不删除key
             *
             * 未执行getFuture/未写入Redis，则不删除Redis中的数据。
             * 否则清理Redis中的数据。未执行getFuture(极有可能为业务自身异常)，
             * 这种情况下如果回滚清除Redis中数据，而此时若Mysql因延迟等原因未落库，
             * 则有较大可能导致下一笔数据幂等校验成功，造成资损。
             * 已执行getFuture并且已写入Redis，则可认为是第一次幂等业务校验，可回滚。
             * 此时需要将KvStore增加isExecuteFuture，isInsertRedis，记录getFuture与insertRedis操作。
             *
             */
            else if (status == STATUS_ROLLED_BACK) {
                //回滚
                if (!kvStore.isExecuteFuture()) {
                    //未执行getFuture
                    return;
                }
                if (!kvStore.isInsertRedis()) {
                    //未执行插入redis
                    return;
                }

                //do ...

                boolean result = removeRedisKey(kvStore);
                if (!result) {
                    throw new RuntimeException("delete redis error");
                }
            }

        }

        /**
         * 校验值是否在数据库中存在
         *
         * @param kvStore
         * @return
         */
        private Future<Boolean> getFuture(final KVStore kvStore) {
            return assertExecutor.submit(new BootCallable<Boolean>() {
                @Override
                public Boolean doCall() throws Exception {
                    //do biz
                    if (checkMysql()) {
                        //如果mysql存在数据
                        kvStore.setExecuteFuture(true);
                        kvStore.setExistInMysql(true);
                        //do ...

                        return false;
                    }
                    //mysql不存在
                    //do ...
                    boolean existRedis = insertRedis(kvStore);

                    //如果写入redis成功，认<<初次>>
                    if (existRedis) {
                        kvStore.setExecuteFuture(true);
                        kvStore.setInsertRedis(true);
                        return true;
                    }
                    /* if DUPLICATE_KEY */
                    if (!existRedis) {
                        kvStore.setExecuteFuture(true);
                        return false;
                    }
                    /* other*/
                    kvStore.setExecuteFuture(true);
                    throw new RuntimeException(" other ..");

                }
            });
        }

        private boolean removeRedisKey(KVStore kvStore) {
            return false;
        }

        private boolean checkMysql() {
            return false;
        }

        /**
         * 插入redis，如果key存在，则返回false
         */
        private boolean insertRedis(KVStore kvStore) {

            return false;
        }
    }
}