package com.zhenmin.superboot.common.response;

public class InterRespHeader {
    private Integer code;
    private String desc;
    private String location;

    public Integer getCode() {
        return code;
    }

    public InterRespHeader setCode(Integer code) {
        this.code = code;
        return this;
    }

    public String getDesc() {
        return desc;
    }

    public InterRespHeader setDesc(String desc) {
        this.desc = desc;
        return this;
    }

    public String getLocation() {
        return location;
    }

    public InterRespHeader setLocation(String location) {
        this.location = location;
        return this;
    }
}
