package com.zhenmin.superboot.base.vo.resp;

import java.io.Serializable;

/**
 * 保险汇总信息
 * Created by xuzhenmin on 17-5-31.
 */
public class InsureVO implements Serializable {

	private boolean buy_insurance;

	private String insure_status;

	private ReportItem report;

	private InsureItem insurance;

	private boolean premium_management;           //是否保额管理


	private boolean can_cancel; //是否可退

	private boolean can_exchange;//是否可换

	private boolean report_status;//报案状态


	private Float repairAmountNow;         //包含本次维修累计维修费

	private Float repairAmountRate;        //维修占比


	public boolean isBuy_insurance() {
		return buy_insurance;
	}

	public void setBuy_insurance(boolean buy_insurance) {
		this.buy_insurance = buy_insurance;
	}

	public String getInsure_status() {
		return insure_status;
	}

	public void setInsure_status(String insure_status) {
		this.insure_status = insure_status;
	}

	public ReportItem getReport() {
		return report;
	}

	public void setReport(ReportItem report) {
		this.report = report;
	}

	public InsureItem getInsurance() {
		return insurance;
	}

	public void setInsurance(InsureItem insurance) {
		this.insurance = insurance;
	}

	public boolean isPremium_management() {
		return premium_management;
	}

	public void setPremium_management(boolean premium_management) {
		this.premium_management = premium_management;
	}

	public boolean isReport_status() {
		return report_status;
	}

	public void setReport_status(boolean report_status) {
		this.report_status = report_status;
	}


	public Float getRepairAmountNow() {
		return repairAmountNow;
	}

	public void setRepairAmountNow(Float repairAmountNow) {
		this.repairAmountNow = repairAmountNow;
	}

	public Float getRepairAmountRate() {
		return repairAmountRate;
	}

	public void setRepairAmountRate(Float repairAmountRate) {
		this.repairAmountRate = repairAmountRate;
	}

	public boolean isCan_cancel() {
		return can_cancel;
	}

	public void setCan_cancel(boolean can_cancel) {
		this.can_cancel = can_cancel;
	}

	public boolean isCan_exchange() {
		return can_exchange;
	}

	public void setCan_exchange(boolean can_exchange) {
		this.can_exchange = can_exchange;
	}
}