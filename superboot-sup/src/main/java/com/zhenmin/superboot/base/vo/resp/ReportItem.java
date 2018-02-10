package com.zhenmin.superboot.base.vo.resp;

import java.io.Serializable;
import java.util.Date;

/**
 * 报案信息
 * Created by xuzhenmin on 17-5-31.
 */
public class ReportItem implements Serializable {


	private Long id;
	//业务单号
	private String service_no;
	//报案号
	private String report_no;
	//订单号
	private String order_id;
	//单品号
	private String uniquecode;
	//保险凭证号
	private String evidence_no;
	//派单号
	private String assign_no;
	//商品名称
	private String goods_name;
	//出险日期
	private Date accident_date;
	//出险原因
	private String accident_reason;
	//报案日期
	private Date report_date;
	//报案渠道
	private String report_channel;
	//报案确认日期
	private Date report_insure_date;
	//保障起期
	private Date insure_date;
	//保障止期
	private Date expiry_date;
	//维修次数
	private int repair_times;
	//维修费用
	private Double repair_amount;
	//客户姓名
	private String customer_name;
	//客户电话
	private String customer_mobile;
	//客户邮箱
	private String customer_email;
	//客户地址
	private String customer_address;
	//故障描述
	private String fault_desc;
	//预约时间
	private Date order_time;
	//送修方式
	private String post_type;
	//机构ID
	private String org_id;
	//机构名称
	private String org_name;
	//备注
	private String remark;
	//报案结果
	private String report_result;
	//状态1:有效2:无效
	private int status;
	//创建日期
	private Date create_date;

	private Date update_date;


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getReport_no() {
		return report_no;
	}

	public void setReport_no(String report_no) {
		this.report_no = report_no;
	}

	public String getOrder_id() {
		return order_id;
	}

	public void setOrder_id(String order_id) {
		this.order_id = order_id;
	}

	public String getUniquecode() {
		return uniquecode;
	}

	public void setUniquecode(String uniquecode) {
		this.uniquecode = uniquecode;
	}

	public String getEvidence_no() {
		return evidence_no;
	}

	public void setEvidence_no(String evidence_no) {
		this.evidence_no = evidence_no;
	}

	public String getAssign_no() {
		return assign_no;
	}

	public void setAssign_no(String assign_no) {
		this.assign_no = assign_no;
	}

	public String getGoods_name() {
		return goods_name;
	}

	public void setGoods_name(String goods_name) {
		this.goods_name = goods_name;
	}

	public Date getAccident_date() {
		return accident_date;
	}

	public void setAccident_date(Date accident_date) {
		this.accident_date = parseDate(accident_date);
	}

	public String getAccident_reason() {
		return accident_reason;
	}

	public void setAccident_reason(String accident_reason) {
		this.accident_reason = accident_reason;
	}

	public Date getReport_date() {
		return report_date;
	}

	public void setReport_date(Date report_date) {
		this.report_date = parseDate(report_date);
	}

	public String getReport_channel() {
		return report_channel;
	}

	public void setReport_channel(String report_channel) {
		this.report_channel = report_channel;
	}

	public Date getReport_insure_date() {
		return report_insure_date;
	}

	public void setReport_insure_date(Date report_insure_date) {
		this.report_insure_date = parseDate(report_insure_date);
	}

	public Date getInsure_date() {
		return insure_date;
	}

	public void setInsure_date(Date insure_date) {
		this.insure_date = parseDate(insure_date);
	}

	public Date getExpiry_date() {
		return expiry_date;
	}

	public void setExpiry_date(Date expiry_date) {
		this.expiry_date = expiry_date;
	}

	public int getRepair_times() {
		return repair_times;
	}

	public void setRepair_times(int repair_times) {
		this.repair_times = repair_times;
	}

	public Double getRepair_amount() {
		return repair_amount;
	}

	public void setRepair_amount(Double repair_amount) {
		this.repair_amount = repair_amount;
	}

	public String getCustomer_name() {
		return customer_name;
	}

	public void setCustomer_name(String customer_name) {
		this.customer_name = customer_name;
	}

	public String getCustomer_mobile() {
		return customer_mobile;
	}

	public void setCustomer_mobile(String customer_mobile) {
		this.customer_mobile = customer_mobile;
	}

	public String getCustomer_email() {
		return customer_email;
	}

	public void setCustomer_email(String customer_email) {
		this.customer_email = customer_email;
	}

	public String getCustomer_address() {
		return customer_address;
	}

	public void setCustomer_address(String customer_address) {
		this.customer_address = customer_address;
	}

	public String getFault_desc() {
		return fault_desc;
	}

	public void setFault_desc(String fault_desc) {
		this.fault_desc = fault_desc;
	}

	public Date getOrder_time() {
		return order_time;
	}

	public void setOrder_time(Date order_time) {
		this.order_time = order_time;
	}

	public String getPost_type() {
		return post_type;
	}

	public void setPost_type(String post_type) {
		this.post_type = post_type;
	}

	public String getOrg_id() {
		return org_id;
	}

	public void setOrg_id(String org_id) {
		this.org_id = org_id;
	}

	public String getOrg_name() {
		return org_name;
	}

	public void setOrg_name(String org_name) {
		this.org_name = org_name;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getReport_result() {
		return report_result;
	}

	public void setReport_result(String report_result) {
		this.report_result = report_result;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public Date getCreate_date() {
		return create_date;
	}

	public void setCreate_date(Date create_date) {
		this.create_date = create_date;
	}

	public Date getUpdate_date() {
		return update_date;
	}

	public void setUpdate_date(Date update_date) {
		this.update_date = update_date;
	}

	public String getService_no() {
		return service_no;
	}

	public void setService_no(String service_no) {
		this.service_no = service_no;
	}

	//秒转换为毫秒
	private Date parseDate(Date date) {
		if (date == null || String.valueOf(date.getTime()).length() > 12) {
			return date;
		} else {
			return new Date(date.getTime() * 1000);
		}

	}
}