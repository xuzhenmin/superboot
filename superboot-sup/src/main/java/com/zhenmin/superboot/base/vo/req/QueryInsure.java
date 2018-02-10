package com.zhenmin.superboot.base.vo.req;

/**
 * Created by xuzhenmin on 17-6-5.
 */
public class QueryInsure {

	private Long orderId;

	private String sn;

	private String imei;

	private Integer bizTime;

	private String bizCode;

	public Long getOrderId() {
		return orderId;
	}

	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}

	public String getSn() {
		return sn;
	}

	public void setSn(String sn) {
		this.sn = sn;
	}

	public String getImei() {
		return imei;
	}

	public void setImei(String imei) {
		this.imei = imei;
	}

	public Integer getBizTime() {
		return bizTime;
	}

	public void setBizTime(Integer bizTime) {
		this.bizTime = bizTime;
	}

	public String getBizCode() {
		return bizCode;
	}

	public void setBizCode(String bizCode) {
		this.bizCode = bizCode;
	}
}
