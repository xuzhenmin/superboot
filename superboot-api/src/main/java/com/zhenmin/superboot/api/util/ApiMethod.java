package com.zhenmin.superboot.api.util;

import com.zhenmin.superboot.util.CollectionUtil;

import java.util.Map;

/**
 * Created by Junin on 2018/2/10.
 */
public enum ApiMethod {

    SUPERBOOT_OPENAPI("superBoot.openApi", "111743d3019e0bdb", ApiConstant.APIM_LOG_SS_AF, "测试");

    private String code;
    private String sign;
    private String serviceSign;
    private String desc;

    private static final Map<String, ApiMethod> lookup = CollectionUtil.newHashMap();

    static {
        for (ApiMethod s : ApiMethod.values()) {
            lookup.put(s.code, s);
        }
    }

    ApiMethod(String code, String sign, String serviceSign, String desc) {
        this.code = code;
        this.sign = sign;
        this.serviceSign = serviceSign;
        this.desc = desc;
    }

    public String getCode() {
        return code;
    }

    public String getSign() {
        return sign;
    }

    public String getServiceSign() {
        return serviceSign;
    }

    public String getDesc() {
        return desc;
    }

    public static ApiMethod get(String code) {
        return lookup.get(code);
    }
}
