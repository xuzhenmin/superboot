package com.zhenmin.superboot.api.vo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by xuzhenmin on 17-6-5.
 */
public class ApimLogVO {
	private String log_app_id;// 日志唯一标识
	private String log_biz_id;// 日志主键
	private String log_other_id;// 日志主键(other)
	@JsonProperty
	private String T;// 时间
	@JsonProperty
	private String F;// 方法
	@JsonProperty
	private String SC;// 接口签名
	@JsonProperty
	private String SS;// 服务签名
	@JsonProperty
	private int RL;// 返回数据长度
	@JsonProperty
	private String S;// 服务端IP
	@JsonProperty
	private String C;// 客户端IP
	@JsonProperty
	private String RT;// 运行时间(秒)
	@JsonProperty
	private String RC; // (response code) 响应码
	@JsonProperty
	private String EB; // (exception​ brief) 错误信息摘要
	@JsonProperty
	private String ED; // exception detail）错误详情

	public String getLog_app_id() {
		return log_app_id;
	}

	public void setLog_app_id(String log_app_id) {
		this.log_app_id = log_app_id;
	}

	public String getLog_biz_id() {
		return log_biz_id;
	}

	public void setLog_biz_id(String log_biz_id) {
		this.log_biz_id = log_biz_id;
	}

	public String getLog_other_id() {
		return log_other_id;
	}

	public void setLog_other_id(String log_other_id) {
		this.log_other_id = log_other_id;
	}

	@JsonIgnore
	public String getT() {
		return T;
	}

	@JsonIgnore
	public void setT(String t) {
		T = t;
	}

	@JsonIgnore
	public String getF() {
		return F;
	}

	@JsonIgnore
	public void setF(String f) {
		F = f;
	}

	@JsonIgnore
	public String getSC() {
		return SC;
	}

	@JsonIgnore
	public void setSC(String SC) {
		this.SC = SC;
	}

	@JsonIgnore
	public String getSS() {
		return SS;
	}

	@JsonIgnore
	public void setSS(String SS) {
		this.SS = SS;
	}

	@JsonIgnore
	public int getRL() {
		return RL;
	}

	@JsonIgnore
	public void setRL(int RL) {
		this.RL = RL;
	}

	@JsonIgnore
	public String getS() {
		return S;
	}

	@JsonIgnore
	public void setS(String s) {
		S = s;
	}

	@JsonIgnore
	public String getC() {
		return C;
	}

	@JsonIgnore
	public void setC(String c) {
		C = c;
	}

	@JsonIgnore
	public String getRT() {
		return RT;
	}

	@JsonIgnore
	public void setRT(String RT) {
		this.RT = RT;
	}

	@JsonIgnore
	public String getRC() {
		return RC;
	}

	@JsonIgnore
	public void setRC(String RC) {
		this.RC = RC;
	}

	@JsonIgnore
	public String getEB() {
		return EB;
	}

	@JsonIgnore
	public void setEB(String EB) {
		this.EB = EB;
	}

	@JsonIgnore
	public String getED() {
		return ED;
	}

	@JsonIgnore
	public void setED(String ED) {
		this.ED = ED;
	}
}
