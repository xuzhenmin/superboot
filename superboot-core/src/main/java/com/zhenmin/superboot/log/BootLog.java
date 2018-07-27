/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2018 All Rights Reserved.
 */
package com.zhenmin.superboot.log;

import com.google.common.collect.Lists;
import org.slf4j.Logger;

/**
 * @author zhenmin
 * @version $Id: BootLog.java, v 0.1 2018-07-24 下午9:02 zhenmin Exp $$
 */
public class BootLog {

    /**
     * 线程编号修饰符
     */
    private static final char THREAD_RIGHT_TAG = ']';

    /**
     * 线程编号修饰符
     */
    private static final char THREAD_LEFT_TAG = '[';

    private String level;

    private Logger logger;

    private Throwable throwable;

    private String message;

    private Object[] params;

    public BootLog(Logger logger, String message, Object... params) {
        this.logger = logger;
        this.message = message;
        this.params = params;
    }

    public BootLog() {

    }

    public static class Builder {

        private BootLog bootLog;

        private Builder(Logger logger, String message, Object... params) {
            bootLog = new BootLog(logger, message, params);
        }

        private Builder() {
            bootLog = new BootLog();
        }

        public static Builder statBuilder(Logger logger, String message) {
            Builder builder = new Builder(logger, message);
            return builder;
        }

        public static Builder newBuilder(Logger logger, String message, Object[] params) {
            Builder builder = new Builder(logger, message, params);
            return builder;
        }

        public static Builder newBuilder() {
            Builder builder = new Builder();
            return builder;
        }

        public Builder logger(Logger logger) {
            bootLog.logger = logger;
            return this;
        }

        public Builder throwable(Throwable throwable) {
            bootLog.throwable = throwable;
            return this;
        }

        public Builder message(String message) {
            bootLog.message = message;
            return this;
        }

        public Builder params(Object... params) {
            bootLog.params = params;
            return this;
        }

        public BootLog build() {
            return bootLog;
        }

    }

    private String format() {
        StringBuilder log = new StringBuilder();
        log.append(THREAD_LEFT_TAG).append(Thread.currentThread().getId()).append(THREAD_RIGHT_TAG)
            .append(message);
        if (params != null && params.length != 0) {
            return MessageUtil.formatMessage(log.toString(), params);
        }
        return log.toString();
    }

    private String format(Object param) {
        StringBuilder log = new StringBuilder();
        log.append(THREAD_LEFT_TAG).append(Thread.currentThread().getId()).append(THREAD_RIGHT_TAG)
            .append(message);
        if (param != null) {
            return MessageUtil.formatMessage(log.toString(), param);
        }
        return log.toString();
    }

    /**
     * info日志
     */
    public void info() {
        try {
            this.level = "INFO";
            if (logger.isInfoEnabled()) {
                if (throwable == null) {
                    logger.info(format());
                } else {
                    logger.info(format(), throwable);
                }
            }
        } catch (Throwable e) {

        }
    }

    /**
     * 统计日志
     */
    public void stat() {
        try {
            if (logger.isInfoEnabled() && params != null) {
                Lists.newArrayList(params).stream().forEach(o -> {
                    if (throwable == null) {
                        logger.warn(format(o));
                    } else {
                        logger.warn(format(o), throwable);
                    }
                });
            }
        } catch (Throwable e) {
        }
    }

    /**
     * 错误日志
     */
    public void error() {
        try {
            this.level = "ERROR";
            if (logger.isErrorEnabled()) {
                if (throwable == null) {
                    logger.error(format());
                } else {
                    logger.error(format(), throwable);
                }
            }
        } catch (Throwable e) {
        }
    }

    /**
     * 警告日志
     */
    public void warn() {
        try {
            this.level = "WARN";
            if (logger.isWarnEnabled()) {
                if (throwable == null) {
                    logger.warn(format());
                } else {
                    logger.warn(format(), throwable);
                }
            }
        } catch (Throwable e) {
        }
    }

    /**
     * Getter method for property level <tt>level</tt>.
     *
     * @return property value of level
     */
    public String getLevel() {
        return level;
    }

    /**
     * Setter method for property level <tt>level</tt>.
     *
     * @param level value to be assigned to property level
     */
    public void setLevel(String level) {
        this.level = level;
    }

    /**
     * Getter method for property logger <tt>logger</tt>.
     *
     * @return property value of logger
     */
    public Logger getLogger() {
        return logger;
    }

    /**
     * Setter method for property logger <tt>logger</tt>.
     *
     * @param logger value to be assigned to property logger
     */
    public void setLogger(Logger logger) {
        this.logger = logger;
    }

    /**
     * Getter method for property throwable <tt>throwable</tt>.
     *
     * @return property value of throwable
     */
    public Throwable getThrowable() {
        return throwable;
    }

    /**
     * Setter method for property throwable <tt>throwable</tt>.
     *
     * @param throwable value to be assigned to property throwable
     */
    public void setThrowable(Throwable throwable) {
        this.throwable = throwable;
    }

    /**
     * Getter method for property message <tt>message</tt>.
     *
     * @return property value of message
     */
    public String getMessage() {
        return message;
    }

    /**
     * Setter method for property message <tt>message</tt>.
     *
     * @param message value to be assigned to property message
     */
    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * Getter method for property params <tt>params</tt>.
     *
     * @return property value of params
     */
    public Object[] getParams() {
        return params;
    }

    /**
     * Setter method for property params <tt>params</tt>.
     *
     * @param params value to be assigned to property params
     */
    public void setParams(Object[] params) {
        this.params = params;
    }

}