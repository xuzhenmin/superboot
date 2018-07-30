/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2018 All Rights Reserved.
 */
package com.zhenmin.superboot.cache.impl;

import java.util.Optional;
import java.util.concurrent.Executors;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.ListenableFutureTask;
import com.google.common.util.concurrent.ListeningExecutorService;
import com.google.common.util.concurrent.MoreExecutors;
import com.zhenmin.superboot.cache.AbstractGuavaCacheStore;
import com.zhenmin.superboot.cache.CacheStore;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static java.util.concurrent.TimeUnit.MINUTES;

/**
 * 利用GuavaCache实现本地缓存
 * 功能：
 * 1、本地缓存降低数据库压力，提供服务处理能力
 * 2、配合共享缓存，进一步降低访问DB的频次
 * 3、加载数据到共享缓存时，可利用分布式锁读取DB，在大集群部署时将大幅降低访问DB的频次
 * 4、利用GuavaCache#refreshAfterWrite特性，提供强制刷新缓存的功能，超时后可自动刷新数据到本地缓存
 * 5、预防缓存击穿的一些场景
 *
 * 其他：
 * 1、共享缓存要考虑跨机房等因素
 * 2、数据量较大的场景要特殊处理，防止单key的value过大
 *
 * @author zhenmin
 * @version $Id: GuavaCacheStoreImpl.java, v 0.1 2018-07-24 下午4:49 zhenmin Exp $$
 */
public class GuavaCacheStoreImpl<T> extends AbstractGuavaCacheStore implements CacheStore {

    private static final Logger LOGGER = LoggerFactory.getLogger(GuavaCacheStoreImpl.class);

    private static final String CACHE_STORE_KEY = "guava_cache_store_key";

    private static final String LOCK_KEY = "lock";

    /**
     * 本地缓存
     *
     * TODO 要注意Optional能否被序列化，并注意该类构造方法为private的影响。
     */
    private LoadingCache<String, Optional<T>> locCache;

    /**
     * guava事件处理类
     */
    private ListeningExecutorService executor;

    /**
     * 初始化
     */
    public void init() {

        executor = MoreExecutors.listeningDecorator(Executors.newFixedThreadPool(5));

        // 初始化Guava缓存
        locCache = CacheBuilder.newBuilder().maximumSize(10).refreshAfterWrite(10, MINUTES)
            .build(new CacheLoader<String, Optional<T>>() {
                @Override
                public Optional<T> load(final String key) throws Exception {
                    return initData(key);
                }

                @Override
                public ListenableFuture<Optional<T>> reload(final String key, Optional<T> oldData)
                    throws Exception {
                    CacheRefresh<Optional<T>> cacheRefresh = needRefresh(key);
                    if (cacheRefresh.getNeedRefresh()) {
                        ListenableFutureTask<Optional<T>> task = ListenableFutureTask.create(
                            () -> loadData(key, oldData));
                        executor.execute(task);
                        return task;
                    } else {
                        //如果缓存时间到，且不需要刷新，则返回Redis中的数据
                        if (cacheRefresh.getValue().isPresent()) {
                            return Futures.immediateFuture(cacheRefresh.getValue());
                        }
                        return Futures.immediateFuture(oldData);
                    }
                }
            });
    }

    /**
     * 数据初始化加载
     *
     * 防止对DB压力过大，优先加载共享缓存数据到本地缓存。
     *
     * @param key
     * @return
     */
    private Optional<T> initData(String key) {
        Optional<T> cacheData = Optional.empty();
        try {
            String redisKey = buildCacheKey(REDIS_CACHE_PREFIX, key);
            //读取共享缓存,共享缓存不存在，读取DB
            T data = loadDataFromRedis(redisKey);
            if (data == null) {
                data = loadDataFromDB();
                if (data != null) {
                    cacheData = Optional.of(data);
                    //将DB数据放入共享缓存
                    putData2Redis(redisKey, data);
                } else {
                    //放置空对象到redis，防止缓存击穿 Optional<T>
                    putData2Redis(redisKey, null);
                }
            }
        } catch (Exception e) {
            logError(LOGGER, "init data error:{}", e);
        }
        return cacheData;
    }

    private Optional<T> loadData(String key, Optional<T> oldData) {

        Optional<T> optional = Optional.empty();
        try {
            //从DB加载数据
            String lockKey = buildCacheKey(LOCK_KEY, key);
            boolean lock = getLock(lockKey);
            logInfo(LOGGER, "get lock result:{}", lock);
            try {
                if (lock) {
                    //读取DB
                    T data = loadDataFromDB();
                    optional = Optional.of(data);
                    String redisKey = buildCacheKey(REDIS_CACHE_PREFIX, key);
                    //缓存到Redis
                    putData2Redis(redisKey, data);
                    //缓存更新成功，刷新标识
                    refreshCacheMark(getMyCacheMark());
                } else {
                    //返回旧数据
                    return oldData;
                }
            } catch (Exception e) {
                logError(LOGGER, "loadData in error:{}", e);
                //异常返回旧数据
                return oldData;
            } finally {
                boolean unlock = unlock(lockKey);
                logInfo(LOGGER, "unlock result:{}", unlock);
            }
        } catch (Exception e) {
            logError(LOGGER, "loadData out error:{}", e);
            //异常返回旧数据
            return oldData;
        }
        return optional;
    }

    private T loadDataFromRedis(String redisKey) {
        //读取共享缓存
        return null;
    }

    private T loadDataFromDB() {
        //从DB加载数据
        return null;
    }

    private void putData2Redis(String redisKey, T data) {
        //更新共享缓存
    }

    /**
     * 获取分布式锁
     *
     * @return
     */
    private boolean getLock(String lockKey) {
        //获取分布式锁

        //TODO 注意设置分布式锁key的有效时间，避免长时间锁死

        return true;
    }

    private boolean unlock(String lockKey) {

        return true;
    }

    /**
     * 判断是否需要刷新缓存
     * 1、如果Redis非空，返回Redis中的数据
     * 2、如果Redis访问异常，返回旧数据
     * 3、Redis为空，则调用刷新任务
     * 4、如果触发强制刷新，则更新数据
     *
     * @return
     */
    private CacheRefresh<Optional<T>> needRefresh(String key) {
        boolean need = false;
        Optional<T> optional = Optional.empty();
        try {
            String redisKey = buildCacheKey(REDIS_CACHE_PREFIX, key);
            T cacheData = loadDataFromRedis(redisKey);
            if (cacheData == null) {
                need = true;
            } else {
                optional = Optional.of(cacheData);
            }
        } catch (Exception e) {
            logError(LOGGER, "need refresh error:{}", e);
        }
        //TODO 获取配置,判断是否需要强制刷新缓存
        if (!StringUtils.equals(cacheMark, getMyCacheMark())) {
            return new CacheRefresh<>(true, optional);
        }
        return new CacheRefresh<>(need, optional);
    }

    @Override
    public Object queryCacheData() {
        T cacheData = null;
        String cacheKey = "";
        try {
            //判断是否需要刷新缓存
            refreshIfNeed(getMyCacheMark(), null);
            cacheKey = buildCacheKey(cacheMark, CACHE_STORE_KEY);
            Optional<T> optional = locCache.get(cacheKey);
            if (optional.isPresent()) {
                cacheData = optional.get();
            }
        } catch (Exception e) {
            logError(LOGGER, "queryCacheData cacheKey:{0}", e, cacheKey);
        }
        return cacheData;
    }

    @Override
    public Object queryCacheData(String key) {
        T cacheData = null;
        String cacheKey = "";
        try {
            //判断是否需要刷新缓存
            refreshIfNeed(getMyCacheMark(), key);
            cacheKey = buildCacheKey(cacheMark, key);
            Optional<T> optional = locCache.get(cacheKey);
            if (optional.isPresent()) {
                cacheData = optional.get();
            }
        } catch (Exception e) {
            logError(LOGGER, "queryCacheData cacheKey:{0}", e, cacheKey);
        }
        return cacheData;
    }

    @Override
    public void refreshCache(String myCacheKey) {
        //强制刷新缓存
        String cacheKey = buildCacheKey(getMyCacheMark(), CACHE_STORE_KEY);
        locCache.refresh(cacheKey);
    }

    @Override
    public void updateDate() {
        //更新业务数据
        //update ....id
        //强制刷新缓存
        String cacheKey = buildCacheKey(getMyCacheMark(), CACHE_STORE_KEY);
        locCache.refresh(cacheKey);
    }

    @Override
    public void updateDate(String key, Object value) {
        //更新业务数据
        //update ....id
        //强制刷新缓存
        String cacheKey = buildCacheKey(getMyCacheMark(), key);
        Optional<T> t = Optional.of((T)value);
        locCache.put(key, t);
    }

    /**
     * 强制刷新缓存标识
     *
     * @return
     */
    private String getMyCacheMark() {
        //此处可以调用服务或者本地设置 强制刷新本地缓存。
        //getCacheMark....
        //开源的配置管理工具，如Diamond
        return "";
    }

    @Override
    public void updateDate(String key, Object value, int expire) {
        logError(LOGGER, "not method key:{}", key);
    }

    @Override
    protected String getCacheMark() {
        return null;
    }

    class CacheRefresh<T> {

        private boolean needRefresh;

        private T value;

        private CacheRefresh() {}

        public CacheRefresh(boolean needRefresh, T value) {
            this.needRefresh = needRefresh;
            this.value = value;
        }

        /**
         * Getter method for property needRefresh <tt>needRefresh</tt>.
         *
         * @return property needRefresh of needRefresh
         */
        public boolean getNeedRefresh() {
            return needRefresh;
        }

        /**
         * Setter method for property needRefresh <tt>needRefresh</tt>.
         *
         * @param needRefresh value to be assigned to property needRefresh
         */
        public void setNeedRefresh(boolean needRefresh) {
            this.needRefresh = needRefresh;
        }

        /**
         * Getter method for property value <tt>value</tt>.
         *
         * @return property value of value
         */
        public T getValue() {
            return value;
        }

        /**
         * Setter method for property value <tt>value</tt>.
         *
         * @param value value to be assigned to property value
         */
        public void setValue(T value) {
            this.value = value;
        }
    }
}