/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2018 All Rights Reserved.
 */
package com.zhenmin.superboot.cache;

import java.util.concurrent.locks.ReentrantLock;

import com.google.common.base.Joiner;
import com.zhenmin.superboot.log.BootLogUtil;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 基于guavaCache实现的缓存应用
 *
 * @author zhenmin
 * @version $Id: AbstractGuavaCacheStore.java, v 0.1 2018-07-24 下午4:28 zhenmin Exp $$
 */
public abstract class AbstractGuavaCacheStore extends BootLogUtil {

    private static final Logger LOGGER = LoggerFactory.getLogger(AbstractGuavaCacheStore.class);

    /**
     * 缓存前缀标识(作用:强制更新缓存)
     */
    protected volatile String cacheMark = "";

    protected static final String CACHE_KEY_SPLITER = "_";

    /**
     * 共享缓存前缀
     */
    protected static final String REDIS_CACHE_PREFIX = "redis";

    /**
     * 强制更新缓存
     *
     * @param myCacheKey 外部可控的缓存刷新标识
     */
    public abstract void refreshCache(String myCacheKey);

    /**
     * Main lock guarding all access
     */
    final ReentrantLock lock = new ReentrantLock();

    /**
     * 更新缓存标识
     *
     * 该标识作用：通过人工触发，手动刷新本地缓存，不会频繁触发。
     *
     * @param myCacheKey
     */
    protected void refreshCacheMark(String myCacheKey) {
        //不相同则更新缓存标识
        if (!this.cacheMark.equals(myCacheKey)) {
            this.cacheMark = myCacheKey;
        }
    }

    /**
     * 刷新缓存前置
     * 双重锁防止重复触发
     * ==========================
     * 在异常情况下，本机缓存更新失败
     *
     * @param myCacheKey
     * @param bizKey     业务Key
     */
    public void refreshIfNeed(String myCacheKey, String bizKey) {
        //空标识不需要强制刷新
        if (StringUtils.isBlank(getCacheMark()) && StringUtils.isBlank(myCacheKey)) {
            return;
        }
        if (!StringUtils.equals(getCacheMark(), myCacheKey)) {
            final ReentrantLock refreshLock = this.lock;
            refreshLock.lock();
            try {
                if (!StringUtils.equals(getCacheMark(), myCacheKey)) {
                    //更新缓存
                    if (StringUtils.isNotBlank(bizKey)) {
                        refreshCache(buildCacheKey(myCacheKey, bizKey));
                    } else {
                        refreshCache(myCacheKey);
                    }
                }
            } catch (Exception e) {
                LOGGER.error("refresh cache error:{}", e);
            } finally {
                refreshLock.unlock();
            }
        }
    }

    /**
     * 构建缓存Key
     *
     * 默认缓存规则
     * 本地缓存：Mark_LocalKey
     * 共享缓存：Mark_RedisKey_LocalKey
     *
     * @param keys 条件
     * @return
     */
    protected String buildCacheKey(String... keys) {
        if (keys == null || keys.length < 1) {
            throw new IllegalArgumentException("The parameter is empty!");
        }
        return Joiner.on(CACHE_KEY_SPLITER).join(keys);
    }

    /**
     * 获取cacheMark
     *
     * @return
     */
    protected abstract String getCacheMark();

}