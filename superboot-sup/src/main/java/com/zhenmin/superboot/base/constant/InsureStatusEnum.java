package com.zhenmin.superboot.base.constant;

/**
 * 保险状态
 * Created by xuzhenmin on 17-5-31.
 */
public enum InsureStatusEnum implements MinEnum {

	INSURE_STATUS_INVALID(0, "0", "未投保"),
	INSURE_STATUS_VALID(1, "1", "已投保"),
	INSURE_STATUS_RETURN(2, "2", "已退保"),
	INSURE_STATUS_FAILURE(3, "3", "已失效");

	private int id;

	private String code;

	private String desc;

	InsureStatusEnum(int id, String code, String desc) {
		this.id = id;
		this.code = code;
		this.desc = desc;
	}

	@Override
	public int getId() {
		return id;
	}

	public String getCode() {
		return code;
	}

	public String getDesc() {
		return desc;
	}

}
