package com.zhenmin.superboot.common.request;

import com.fasterxml.jackson.annotation.JsonProperty;

public class InterReqHeader {
    @JsonProperty("appid")
    private String appId;
    @JsonProperty("operator_id")
    private String operatorId;
    @JsonProperty("operator_name")
    private String operatorName;
    private String method;
    private String sign;
    @JsonProperty("user_id")
    private String userId;
    @JsonProperty("user_name")
    private String userName;
    private String language;


    public String getAppId() {
        return appId;
    }

    public InterReqHeader setAppId(String appId) {
        this.appId = appId;
        return this;
    }

    public String getOperatorId() {
        return operatorId;
    }

    public InterReqHeader setOperatorId(String operatorId) {
        this.operatorId = operatorId;
        return this;
    }

    public String getOperatorName() {
        return operatorName;
    }

    public InterReqHeader setOperatorName(String operatorName) {
        this.operatorName = operatorName;
        return this;
    }

    public String getMethod() {
        return method;
    }

    public InterReqHeader setMethod(String method) {
        this.method = method;
        return this;
    }

    public String getSign() {
        return sign;
    }

    public InterReqHeader setSign(String sign) {
        this.sign = sign;
        return this;
    }

    public String getUserId() {
        return userId;
    }

    public InterReqHeader setUserId(String userId) {
        this.userId = userId;
        return this;
    }

    public String getUserName() {
        return userName;
    }

    public InterReqHeader setUserName(String userName) {
        this.userName = userName;
        return this;
    }

    public String getLanguage() {
        return language;
    }

    public InterReqHeader setLanguage(String language) {
        this.language = language;
        return this;
    }

}
