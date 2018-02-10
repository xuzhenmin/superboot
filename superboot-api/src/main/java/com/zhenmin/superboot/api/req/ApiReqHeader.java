package com.zhenmin.superboot.api.req;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by xuzhenmin on 17-6-5.
 */
public class ApiReqHeader {
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


	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getAppId() {
		return appId;
	}

	public void setAppId(String appId) {
		this.appId = appId;
	}

	public String getOperatorId() {
		return operatorId;
	}

	public void setOperatorId(String operatorId) {
		this.operatorId = operatorId;
	}

	public String getOperatorName() {
		return operatorName;
	}

	public void setOperatorName(String operatorName) {
		this.operatorName = operatorName;
	}

	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	public String getSign() {
		return sign;
	}

	public void setSign(String sign) {
		this.sign = sign;
	}
}
