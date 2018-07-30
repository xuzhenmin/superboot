/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2018 All Rights Reserved.
 */
package com.zhenmin.superboot.transaction;

/**
 * @author zhenmin
 * @version $Id: BootRunnable.java, v 0.1 2018-07-30 下午3:17 zhenmin Exp $$
 */
public abstract class BootRunnable implements Runnable {

    public BootRunnable() {
        init();
    }

    public final void init() {
        //init...
    }

    public abstract void goRun();

    @Override
    public void run() {
        //do something before
        //记录一些日志信息，线程信息

        try {
            goRun();
        } finally {
            //do after
            //记录线程后置信息

        }
    }
}