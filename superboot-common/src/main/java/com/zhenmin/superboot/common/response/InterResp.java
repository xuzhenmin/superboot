package com.zhenmin.superboot.common.response;

public class InterResp<T> {
    private InterRespHeader header;
    private T body;

    public InterRespHeader getHeader() {
        return header;
    }

    public InterResp setHeader(InterRespHeader header) {
        this.header = header;
        return this;
    }

    public T getBody() {
        return body;
    }

    public InterResp setBody(T body) {
        this.body = body;
        return this;
    }
}
