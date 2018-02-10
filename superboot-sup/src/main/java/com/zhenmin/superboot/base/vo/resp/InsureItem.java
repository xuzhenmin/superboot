package com.zhenmin.superboot.base.vo.resp;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 保险信息
 * Created by xuzhenmin on 17-5-31.
 */
public class InsureItem implements Serializable {

	//系统编号
	private Long id;
	private String key;
	//商品订单号
	private Long order_id;
	//商品单品号
	private String goods_uniquecode;
	//用户编号
	private Long user_id;
	//用户名
	private String user_name;
	//用户电话
	private String user_tel;
	//商品名称
	private String goods_name;
	//商品SKU
	private int goods_sku;
	//商品价格
	private BigDecimal goods_price;
	//保险单品号
	private String insure_uniquecode;
	//保险SKU
	private int insure_sku;
	//保险价格
	private BigDecimal insure_price;
	//保险人
	private String insure_person;
	//被保险人
	private String insured_person;
	//保障期限
	private int insure_period;
	//保障额度
	private BigDecimal sum_insured;
	//保费
	private BigDecimal premium;
	//剩余保障额度
	private BigDecimal rest_sum_insured;
	//维修次数
	private int repair_times;
	//累计维修费用
	private BigDecimal repair_fee;
	//交易号
	private Long trade_code;
	//保险类型(1:投保2:退保)
	private Byte insure_type;
	//保险状态(0新建,1成功,2失败,3关闭)
	private Byte insure_status;
	//支付日期
	private Date pay_date;
	//下单日期
	private Date order_date;
	//出库日期
	private Date out_date;
	//触发投保或退保的日期
	private Date trigger_date;
	//销售渠道(1:官网2:天猫3:米家4:商城)
	private Integer sale_channel;
	//保险起期
	private Date insure_start_date;
	//保险终期
	private Date insure_end_date;
	//SAP对账日期
	private Date sap_date;
	//保险凭证号
	private String evidence_no;
	//原始订单号
	private Long origin_order_id;
	//原始商品单品号
	private String origin_goods_uniquecode;
	//保险接口返回信息
	private String insure_response;
	//父记录编号
	private Long pid;
	//产品类型(1:手机2:平板3:无人机)
	private Integer product_type;
	//补购订单号
	private String supply_order_id;

	private Date create_date;

	private Date update_date;

	//XMS维修次数
	private int xms_repair_times;
	//XMS剩余额度
	private BigDecimal xms_rest_sum_insured;


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public Long getOrder_id() {
		return order_id;
	}

	public void setOrder_id(Long order_id) {
		this.order_id = order_id;
	}

	public String getGoods_uniquecode() {
		return goods_uniquecode;
	}

	public void setGoods_uniquecode(String goods_uniquecode) {
		this.goods_uniquecode = goods_uniquecode;
	}

	public Long getUser_id() {
		return user_id;
	}

	public void setUser_id(Long user_id) {
		this.user_id = user_id;
	}

	public String getUser_name() {
		return user_name;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}

	public String getUser_tel() {
		return user_tel;
	}

	public void setUser_tel(String user_tel) {
		this.user_tel = user_tel;
	}

	public String getGoods_name() {
		return goods_name;
	}

	public void setGoods_name(String goods_name) {
		this.goods_name = goods_name;
	}

	public int getGoods_sku() {
		return goods_sku;
	}

	public void setGoods_sku(int goods_sku) {
		this.goods_sku = goods_sku;
	}

	public BigDecimal getGoods_price() {
		return goods_price;
	}

	public void setGoods_price(BigDecimal goods_price) {
		this.goods_price = goods_price;
	}

	public String getInsure_uniquecode() {
		return insure_uniquecode;
	}

	public void setInsure_uniquecode(String insure_uniquecode) {
		this.insure_uniquecode = insure_uniquecode;
	}

	public int getInsure_sku() {
		return insure_sku;
	}

	public void setInsure_sku(int insure_sku) {
		this.insure_sku = insure_sku;
	}

	public BigDecimal getInsure_price() {
		return insure_price;
	}

	public void setInsure_price(BigDecimal insure_price) {
		this.insure_price = insure_price;
	}

	public String getInsure_person() {
		return insure_person;
	}

	public void setInsure_person(String insure_person) {
		this.insure_person = insure_person;
	}

	public String getInsured_person() {
		return insured_person;
	}

	public void setInsured_person(String insured_person) {
		this.insured_person = insured_person;
	}

	public int getInsure_period() {
		return insure_period;
	}

	public void setInsure_period(int insure_period) {
		this.insure_period = insure_period;
	}

	public BigDecimal getSum_insured() {
		return sum_insured;
	}

	public void setSum_insured(BigDecimal sum_insured) {
		this.sum_insured = sum_insured;
	}

	public BigDecimal getPremium() {
		return premium;
	}

	public void setPremium(BigDecimal premium) {
		this.premium = premium;
	}

	public Long getTrade_code() {
		return trade_code;
	}

	public void setTrade_code(Long trade_code) {
		this.trade_code = trade_code;
	}

	public Byte getInsure_type() {
		return insure_type;
	}

	public void setInsure_type(Byte insure_type) {
		this.insure_type = insure_type;
	}

	public Byte getInsure_status() {
		return insure_status;
	}

	public void setInsure_status(Byte insure_status) {
		this.insure_status = insure_status;
	}

	public Date getPay_date() {
		return pay_date;
	}

	public void setPay_date(Date pay_date) {
		this.pay_date = parseDate(pay_date);
	}

	public Date getOrder_date() {
		return order_date;
	}

	public void setOrder_date(Date order_date) {
		this.order_date = parseDate(order_date);
	}

	public Date getOut_date() {
		return out_date;
	}

	public void setOut_date(Date out_date) {
		this.out_date = parseDate(out_date);
	}

	public Date getTrigger_date() {
		return trigger_date;
	}

	public void setTrigger_date(Date trigger_date) {
		this.trigger_date = parseDate(trigger_date);
	}

	public Integer getSale_channel() {
		return sale_channel;
	}

	public void setSale_channel(Integer sale_channel) {
		this.sale_channel = sale_channel;
	}

	public Date getInsure_start_date() {
		return insure_start_date;
	}

	public void setInsure_start_date(Date insure_start_date) {
		this.insure_start_date = parseDate(insure_start_date);
	}

	public Date getInsure_end_date() {
		return insure_end_date;
	}

	public void setInsure_end_date(Date insure_end_date) {
		this.insure_end_date = parseDate(insure_end_date);
	}

	public Date getSap_date() {
		return sap_date;
	}

	public void setSap_date(Date sap_date) {
		this.sap_date = parseDate(sap_date);
	}

	public String getEvidence_no() {
		return evidence_no;
	}

	public void setEvidence_no(String evidence_no) {
		this.evidence_no = evidence_no;
	}

	public Long getOrigin_order_id() {
		return origin_order_id;
	}

	public void setOrigin_order_id(Long origin_order_id) {
		this.origin_order_id = origin_order_id;
	}

	public String getOrigin_goods_uniquecode() {
		return origin_goods_uniquecode;
	}

	public void setOrigin_goods_uniquecode(String origin_goods_uniquecode) {
		this.origin_goods_uniquecode = origin_goods_uniquecode;
	}

	public String getInsure_response() {
		return insure_response;
	}

	public void setInsure_response(String insure_response) {
		this.insure_response = insure_response;
	}

	public Long getPid() {
		return pid;
	}

	public void setPid(Long pid) {
		this.pid = pid;
	}

	public Integer getProduct_type() {
		return product_type;
	}

	public void setProduct_type(Integer product_type) {
		this.product_type = product_type;
	}

	public Date getCreate_date() {
		return create_date;
	}

	public void setCreate_date(Date create_date) {
		this.create_date = parseDate(create_date);
	}

	public Date getUpdate_date() {
		return update_date;
	}

	public void setUpdate_date(Date update_date) {
		this.update_date = parseDate(update_date);
	}

	public BigDecimal getRepair_fee() {
		return repair_fee;
	}

	public void setRepair_fee(BigDecimal repair_fee) {
		this.repair_fee = repair_fee;
	}

	public BigDecimal getRest_sum_insured() {
		return rest_sum_insured;
	}

	public void setRest_sum_insured(BigDecimal rest_sum_insured) {
		this.rest_sum_insured = rest_sum_insured;
	}

	public int getRepair_times() {
		return repair_times;
	}

	public void setRepair_times(int repair_times) {
		this.repair_times = repair_times;
	}

	public String getSupply_order_id() {
		return supply_order_id;
	}

	public void setSupply_order_id(String supply_order_id) {
		this.supply_order_id = supply_order_id;
	}

	//秒转换为毫秒
	private Date parseDate(Date date) {

		if (date == null || String.valueOf(date.getTime()).length() > 12) {
			return date;
		} else {
			return new Date(date.getTime() * 1000);
		}

	}

	public int getXms_repair_times() {
		return xms_repair_times;
	}

	public void setXms_repair_times(int xms_repair_times) {
		this.xms_repair_times = xms_repair_times;
	}

	public BigDecimal getXms_rest_sum_insured() {
		return xms_rest_sum_insured;
	}

	public void setXms_rest_sum_insured(BigDecimal xms_rest_sum_insured) {
		this.xms_rest_sum_insured = xms_rest_sum_insured;
	}
}
