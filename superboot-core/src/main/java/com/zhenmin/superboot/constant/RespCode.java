package com.zhenmin.superboot.constant;

import com.google.common.collect.Sets;

import java.util.Set;

/**
 * Created by Junin on 2018/2/10.
 */
public class RespCode {


    public static final Integer API_COMMON_SUCCESS = 200;
    public static final int COMMON_PARAM_ERROR = 201;                           // 参数错误
    public static final int COMMON_REQUEST_EMPTY_ERROR = 206;                   // 请求消息为空

    // 1000-1999 API
    // 1000-1099 API COMMON
    public static final Integer API_COMMON_ERROR_PARAM = 1001;// 参数错误
    public static final Integer API_COMMON_ERROR_CHECK_SIGN = 1002;// 签名校验失败
    public static final Integer API_COMMON_ERROR_METHOD_ILLEGAL = 1003;// 请求方法不合法
    public static final Integer API_COMMON_ERROR_IP_ILLEGAL = 1004;// 请求IP不合法
    public static final Integer API_COMMON_ERROR_HTTP_GET_FORBID = 1005;// HTTP GET请求屏蔽
    public static final Integer API_COMMON_ERROR_SEC_CONF_NOT_FOUND = 1006;// 安全配置项不存在

    public static final Set<Integer> RULES_VALID = Sets.newHashSet();

    //5000-5999 check rules
    public static final int RULES_IN_WARRANTY = 5000;
    public static final int RULES_SPECIAL_APPROVAL_PASS = 5001;


    //外部接口
    public static final int INSURANCE_CHANGE_INFO_ERROR = 6001;
    // 9000-9999 EXCEPTION
    public static final Integer EXP_IO = 9001;
    public static final Integer EXP_RUNTIME = 9002;
    public static final Integer EXP_KEYCENTER = 9101;
    public static final Integer EXP_JSON = 9102;
    public static final Integer EXP_DATABASE = 9103;
    public static final Integer EXP_NULL = 9104;
    public static final Integer EXP_ILLEGAL_ARGUMENT = 9105;
    public static final Integer EXP_PROCESS = 9106;
    public static final Integer EXP_REFLACT = 9107;
    public static final Integer MAF_EXCEPTION = 9998;
    public static final Integer EXP_OTHERS = 9999;

    static {
        RULES_VALID.add(RULES_IN_WARRANTY);
        RULES_VALID.add(RULES_SPECIAL_APPROVAL_PASS);
    }
}
