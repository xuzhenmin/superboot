/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2018 All Rights Reserved.
 */
package com.zhenmin.superboot.cache;

import java.util.List;

/**
 * @author zhenmin
 * @version $Id: CacheStore.java, v 0.1 2018-07-24 下午4:55 zhenmin Exp $$
 */
public interface CacheStore {

    /**
     * 查询缓存信息
     *
     * @return
     */
    List<String> queryCacheData();

    /**
     * 更新数据后刷新缓存
     */
    void updateDate();

}