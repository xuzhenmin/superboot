package com.zhenmin.superboot.base.constant;

/**
 * 出险相关
 * Created by xuzhenmin on 17-5-31.
 */
public enum InsureOperEnum implements MinEnum {

	INSURE_OPER_CHANGE_SN(0, "1", "换串"),
	INSURE_OPER_CHANGE_TERMINATE(1, "2", "换机终止保险"),
	INSURE_OPER_CHANGE_REPAIR(2, "3", "意外保工厂换机出险"),
	INSURE_OPER_RETURN(3, "4", "退保险"),
	INSURE_OPER_REPAIR(4, "5", "维修出险"),
	INSURE_OPER_CHANGE(5, "7", "普通保险换机");

	private int id;

	private String code;

	private String desc;

	InsureOperEnum(int id, String code, String desc) {
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
