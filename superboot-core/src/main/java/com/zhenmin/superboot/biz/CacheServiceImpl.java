/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2018 All Rights Reserved.
 */
package com.zhenmin.superboot.biz;

import java.util.List;

import com.zhenmin.superboot.cache.CacheLoader;
import com.zhenmin.superboot.cache.CacheStore;
import com.zhenmin.superboot.cache.CacheTemplate;
import com.zhenmin.superboot.log.BootLogUtil;
import org.assertj.core.util.Lists;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author zhenmin
 * @version $Id: CacheServiceImpl.java, v 0.1 2018-07-30 下午2:27 zhenmin Exp $$
 */
public class CacheServiceImpl extends BootLogUtil {

    private static final Logger LOGGER = LoggerFactory.getLogger(CacheServiceImpl.class);

    @Autowired
    private CacheStore cacheStore;

    public List<String> queryFromCache() {
        List<String> list = Lists.newArrayList();
        try {
            list = CacheTemplate.get(cacheStore, "cache_key", 200, new CacheLoader<List<String>>() {
                @Override
                public <V> V getData() throws Exception {
                    //Load data from db
                    return null;
                }

                @Override
                public boolean checkData(List<String> data) {
                    //校验返回结果
                    return data == null;
                }
            });
        } catch (Exception e) {
            logError(LOGGER, "queryFromCache error:{}", e);
        }
        return list;
    }

}