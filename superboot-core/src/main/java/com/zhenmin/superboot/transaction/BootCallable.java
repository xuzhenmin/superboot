/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2018 All Rights Reserved.
 */
package com.zhenmin.superboot.transaction;

import java.util.concurrent.Callable;

/**
 * @author zhenmin
 * @version $Id: BootCallable.java, v 0.1 2018-07-30 下午3:35 zhenmin Exp $$
 */
public abstract class BootCallable<V> implements Callable<V> {

    public BootCallable() {
        init();
    }

    public final void init() {
        //init...
    }

    public abstract V doCall() throws Exception;

    @Override
    public V call() throws Exception {
        //do something before
        //记录一些日志信息，线程信息

        try {
            return doCall();
        } finally {
            //do after
            //记录线程后置信息
        }
    }

}