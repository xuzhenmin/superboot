package com.zhenmin.superboot.base.constant;

/**
 * 保险相关品牌(后续可通过表配置)
 * Created by xuzhenmin on 17-5-31.
 */
public enum BrandEnum implements MinEnum {

	INSURE_BRAND_PHONE(0, "1", "手机品牌"),
	INSURE_OPER_PAD(1, "13", "小米平板"),
	INSURE_OPER_UAV(2, "35", "无人机");

	private int id;

	private String code;

	private String desc;

	BrandEnum(int id, String code, String desc) {
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
