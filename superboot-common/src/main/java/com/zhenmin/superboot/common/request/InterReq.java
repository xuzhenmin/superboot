package com.zhenmin.superboot.common.request;

public class InterReq {
    private InterReqHeader header;
    private String body;

    public InterReqHeader getHeader() {
        return header;
    }

    public InterReq setHeader(InterReqHeader header) {
        this.header = header;
        return this;
    }

    public String getBody() {
        return body;
    }

    public InterReq setBody(String body) {
        this.body = body;
        return this;
    }
}
