/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2018 All Rights Reserved.
 */
package com.zhenmin.superboot.log;

import java.text.MessageFormat;

/**
 * @author zhenmin
 * @version $Id: MessageUtil.java, v 0.1 2018-07-24 下午9:08 zhenmin Exp $$
 */
public class MessageUtil {

    /**
     * 使用<code>MessageFormat</code>格式化字符串.
     *
     * @param message 要格式化的字符串
     * @param params  参数表
     * @return 格式化的字符串，如果message为<code>null</code>，则返回<code>null</code>
     */
    public static String formatMessage(String message, Object[] params) {
        if ((message == null) || (params == null) || (params.length == 0)) {
            return message;
        }

        return MessageFormat.format(message, params);
    }

    /**
     * 使用<code>MessageFormat</code>格式化字符串.
     *
     * @param message 要格式化的字符串
     * @param param1  参数1
     * @return 格式化的字符串，如果message为<code>null</code>，则返回<code>null</code>
     */
    public static String formatMessage(String message, Object param1) {
        return formatMessage(message, new Object[] {param1});
    }

    /**
     * 使用<code>MessageFormat</code>格式化字符串.
     *
     * @param message 要格式化的字符串
     * @param param1  参数1
     * @param param2  参数2
     * @return 格式化的字符串，如果message为<code>null</code>，则返回<code>null</code>
     */
    public static String formatMessage(String message, Object param1, Object param2) {
        return formatMessage(message, new Object[] {param1, param2});
    }

    /**
     * 使用<code>MessageFormat</code>格式化字符串.
     *
     * @param message 要格式化的字符串
     * @param param1  参数1
     * @param param2  参数2
     * @param param3  参数3
     * @return 格式化的字符串，如果message为<code>null</code>，则返回<code>null</code>
     */
    public static String formatMessage(String message, Object param1, Object param2, Object param3) {
        return formatMessage(message, new Object[] {param1, param2, param3});
    }

    /**
     * 使用<code>MessageFormat</code>格式化字符串.
     *
     * @param message 要格式化的字符串
     * @param param1  参数1
     * @param param2  参数2
     * @param param3  参数3
     * @param param4  参数4
     * @return 格式化的字符串，如果message为<code>null</code>，则返回<code>null</code>
     */
    public static String formatMessage(String message, Object param1, Object param2, Object param3,
                                       Object param4) {
        return formatMessage(message, new Object[] {param1, param2, param3, param4});
    }

    /**
     * 使用<code>MessageFormat</code>格式化字符串.
     *
     * @param message 要格式化的字符串
     * @param param1  参数1
     * @param param2  参数2
     * @param param3  参数3
     * @param param4  参数4
     * @param param5  参数5
     * @return 格式化的字符串，如果message为<code>null</code>，则返回<code>null</code>
     */
    public static String formatMessage(String message, Object param1, Object param2, Object param3,
                                       Object param4, Object param5) {
        return formatMessage(message, new Object[] {param1, param2, param3, param4, param5});
    }
}