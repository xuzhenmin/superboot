package com.zhenmin.superboot.api.resp;

/**
 * Created by xuzhenmin on 17-6-5.
 */
public class ApiRespHeader {
	private Integer code;
	private String desc;

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}
}
