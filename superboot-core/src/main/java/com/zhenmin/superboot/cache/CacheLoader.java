/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2018 All Rights Reserved.
 */
package com.zhenmin.superboot.cache;

/**
 * @author zhenmin
 * @version $Id: CacheLoader.java, v 0.1 2018-07-30 上午11:08 zhenmin Exp $$
 */
public interface CacheLoader<V> {

    /**
     * 加载数据
     *
     * @param <V>
     * @return
     * @throws Exception
     */
    <V> V getData() throws Exception;

    /**
     * 校验数据
     *
     * @param data
     * @return
     */
    boolean checkData(V data);

}