/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2018 All Rights Reserved.
 */
package com.zhenmin.superboot.cache;

/**
 * @author zhenmin
 * @version $Id: CacheStore.java, v 0.1 2018-07-24 下午4:55 zhenmin Exp $$
 */
public interface CacheStore<T> {

    /**
     * 查询缓存信息
     *
     * @return
     */
    <V> V queryCacheData();

    /**
     * 查询缓存信息
     *
     * @param key
     * @return
     */
    <V> V queryCacheData(String key);

    /**
     * 更新数据后刷新缓存
     */
    void updateDate();

    /**
     * 更新缓存
     *
     * @param key
     * @param value
     */
    void updateDate(String key, T value);

    /**
     * TODO 次数不实现带expire的cache
     *
     * @param key
     * @param value
     * @param expire
     */
    void updateDate(String key, T value, int expire);

}