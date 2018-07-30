/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2018 All Rights Reserved.
 */
package com.zhenmin.superboot.transaction;

/**
 * @author zhenmin
 * @version $Id: KVStore.java, v 0.1 2018-07-27 下午4:12 zhenmin Exp $$
 */
public class KVStore {

    /**
     * 数据版本，可以再update操作时作版本控制
     */
    private int version;

    /**
     * 业务key
     */
    private String key = null;
    /**
     * 业务数据
     */
    private String value = null;
    /**
     * 业务时间戳
     */
    private long timestamp = -1;
    /**
     * 是否超时
     */
    private boolean expired = false;

    private boolean sendMessage = false;

    private boolean existInMysql = false;

    private boolean executeFuture;

    private boolean insertRedis;

    public boolean isInsertRedis() {
        return insertRedis;
    }

    public void setInsertRedis(boolean insertRedis) {
        this.insertRedis = insertRedis;
    }

    public boolean isSendMessage() {
        return sendMessage;
    }

    public boolean isExistInMysql() {
        return existInMysql;
    }

    public void setExistInMysql(boolean existInMysql) {
        this.existInMysql = existInMysql;
    }

    public boolean isExecuteFuture() {
        return executeFuture;
    }

    public void setExecuteFuture(boolean executeFuture) {
        this.executeFuture = executeFuture;
    }

    /**
     * Setter method for property sendMessage <tt>sendMessage</tt>.
     *
     * @param sendMessage value to be assigned to property sendMessage
     */
    public void setSendMessage(boolean sendMessage) {
        this.sendMessage = sendMessage;
    }

    public boolean isExpired() {
        return expired;
    }

    /**
     * Setter method for property expired <tt>expired</tt>.
     *
     * @param expired value to be assigned to property expired
     */
    public void setExpired(boolean expired) {
        this.expired = expired;
    }

    /**
     * Getter method for property version <tt>version</tt>.
     *
     * @return property value of version
     */
    public int getVersion() {
        return version;
    }

    /**
     * Setter method for property version <tt>version</tt>.
     *
     * @param version value to be assigned to property version
     */
    public void setVersion(int version) {
        this.version = version;
    }

    /**
     * Getter method for property key <tt>key</tt>.
     *
     * @return property value of key
     */
    public String getKey() {
        return key;
    }

    /**
     * Setter method for property key <tt>key</tt>.
     *
     * @param key value to be assigned to property key
     */
    public void setKey(String key) {
        this.key = key;
    }

    /**
     * Getter method for property value <tt>value</tt>.
     *
     * @return property value of value
     */
    public String getValue() {
        return value;
    }

    /**
     * Setter method for property value <tt>value</tt>.
     *
     * @param value value to be assigned to property value
     */
    public void setValue(String value) {
        this.value = value;
    }

    /**
     * Getter method for property timestamp <tt>timestamp</tt>.
     *
     * @return property value of timestamp
     */
    public long getTimestamp() {
        return timestamp;
    }

    /**
     * Setter method for property timestamp <tt>timestamp</tt>.
     *
     * @param timestamp value to be assigned to property timestamp
     */
    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }
}