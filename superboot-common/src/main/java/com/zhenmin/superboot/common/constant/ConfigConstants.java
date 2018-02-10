package com.zhenmin.superboot.common.constant;

public class ConfigConstants {
	
	public static final String REG_BEAN_SCAN = "com.zhenmin.superboot.*";
	
	public static final String MASTER_DS_PACKAGE = "com.zhenmin.superboot.dao.master";
	public static final String SLAVE_DS_PACKAGE = "com.zhenmin.superboot.dao.slave";
	
	public static final String MASTER_DS_SS_REF = "masterSqlSessionFactory";
	public static final String MASTER_DS_NAME = "superbootMasterDataSource";
	public static final String PREFIX_DS_SUPERBOOT_MASTER = "ds.superboot.master";
	public static final String NAME_DS_SSF_SUPERBOOT_MASTER = "superbootMasterSqlSessionFactory";
	public static final String NAME_DS_TM_SUPERBOOT_MASTER = "superbootMasterTransactionManager";
	
	public static final String SLAVE_DS_SS_REF = "slaveSqlSessionFactory";
	public static final String SLAVE_DS_NAME = "superbootSlaveDataSource";
	public static final String PREFIX_DS_SUPERBOOT_SLAVE = "ds.superboot.slave";
	public static final String NAME_DS_SSF_SUPERBOOT_SLAVE = "superbootSlaveSqlSessionFactory";
	public static final String NAME_DS_TM_SUPERBOOT_SLAVE = "superbootSlaveTransactionManager";
	
	public static final String NAME_MYBATIS_CONFIG = "mybatis-config.xml";
}
