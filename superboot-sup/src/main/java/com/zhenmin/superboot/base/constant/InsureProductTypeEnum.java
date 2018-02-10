package com.zhenmin.superboot.base.constant;

/**
 * 保险产品类型
 * Created by xuzhenmin on 17-5-31.
 */
public enum InsureProductTypeEnum implements MinEnum {

	INSURE_TYPE_NONE(0, "0", "未知"),
	INSURE_TYPE_ZA_PHONE(1, "1", "众安手机"),
	INSURE_TYPE_ZA_PAD(2, "2", "众安平板"),
	INSURE_TYPE_JR_UAV(3, "3", "金融无人机"),
	INSURE_TYPE_JR_PHONE(4, "4", "金融手机"),
	INSURE_TYPE_JR_PHONR2(5, "5", "金融手机2.0");

	private int id;

	private String code;

	private String desc;

	InsureProductTypeEnum(int id, String code, String desc) {
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
