package com.zhenmin.superboot.base.constant;

/**
 * Created by xuzhenmin on 17-6-1.
 */
public enum InsureChannelEnum implements MinEnum {

	INSURE_SALE_CHANNEL_OFFICIAL(0,"1","官网"),
	INSURE_SALE_CHANNEL_TAOBAO(1,"2","淘宝"),
	INSURE_SALE_CHANNEL_MIHOME(2,"3","米家"),//米家分正常购买和补购
	INSURE_SALE_CHANNEL_MIUI(3,"4","MIUI"),
	INSURE_SALE_CHANNEL_OFFLINE(4,"5","渠道销售");


	private int id;

	private String code;

	private String desc;

	InsureChannelEnum(int id, String code, String desc) {
		this.id = id;
		this.code = code;
		this.desc = desc;
	}

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
