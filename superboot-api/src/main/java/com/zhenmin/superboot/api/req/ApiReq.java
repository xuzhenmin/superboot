package com.zhenmin.superboot.api.req;

/**
 * Created by xuzhenmin on 17-6-5.
 */
public class ApiReq {
	private ApiReqHeader header;
	private String body;

	public ApiReqHeader getHeader() {
		return header;
	}

	public void setHeader(ApiReqHeader header) {
		this.header = header;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}
}
