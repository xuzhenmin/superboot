package com.zhenmin.superboot.base.constant;

/**
 * 同步报案
 * Created by xuzhenmin on 17-6-5.
 */
public enum InsureSyncReportEnum implements MinEnum {

	INSURE_SYNC_REPORT_FORBID(0, "0", "禁止关闭"),
	INSURE_SYNC_REPORT_VALID(1, "1", "有效"),
	INSURE_SYNC_REPORT_CLOSED(2, "2", "关闭");

	private int id;

	private String code;

	private String desc;

	InsureSyncReportEnum(int id, String code, String desc) {
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
