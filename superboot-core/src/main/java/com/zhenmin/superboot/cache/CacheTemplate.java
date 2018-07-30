/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2018 All Rights Reserved.
 */
package com.zhenmin.superboot.cache;

import com.zhenmin.superboot.log.BootLogUtil;

/**
 * @author zhenmin
 * @version $Id: CacheTemplate.java, v 0.1 2018-07-30 上午11:03 zhenmin Exp $$
 */
public class CacheTemplate extends BootLogUtil {

    private static final int DEFAULT_EXPIRE = 0;

    /**
     * 获取缓存数据
     *
     * TODO 方法内可以捕获异常，重试
     *
     * @param cacheStore
     * @param key
     * @param expire
     * @param loader
     * @param <V>
     * @return
     * @throws Exception
     */
    public static <V> V get(CacheStore cacheStore, String key, int expire, CacheLoader<V> loader)
        throws Exception {
        //缓存取数据
        V result = (V)cacheStore.queryCacheData(key);
        if (result != null && loader.checkData(result)) {
            return result;
        }
        //缓存没有，或者校验不通过，下层加载数据
        result = loader.getData();
        if (expire >= DEFAULT_EXPIRE && result != null && loader.checkData(result)) {
            cacheStore.updateDate(key, result, expire);
        }
        return result;
    }

}