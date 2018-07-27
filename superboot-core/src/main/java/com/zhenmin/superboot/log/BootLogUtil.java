/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2018 All Rights Reserved.
 */
package com.zhenmin.superboot.log;

import org.slf4j.Logger;

/**
 * @author zhenmin
 * @version $Id: BootLogUtil.java, v 0.1 2018-07-27 下午2:49 zhenmin Exp $$
 */
public class BootLogUtil {

    protected void logError(Logger logger, String message, Exception e, Object... params) {
        if (e == null) {
            BootLog.Builder.newBuilder().logger(logger).message(message).params(
                params).build().error();
        } else {
            BootLog.Builder.newBuilder().logger(logger).message(message).throwable(e).params(
                params).build().error();
        }
    }

    protected void logError(Logger logger, String message, Object... params) {
        this.logError(logger, message, null, params);
    }

    protected void logInfo(Logger logger, String message, Object... params) {
        BootLog.Builder.newBuilder().logger(logger).message(message).params(
            params).build().info();
    }

    protected void logWarn(Logger logger, String message, Object... params) {
        BootLog.Builder.newBuilder().logger(logger).message(message).params(
            params).build().warn();
    }

}