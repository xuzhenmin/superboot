package com.zhenmin.superboot.log;

import com.zhenmin.superboot.Exception.SuperBootException;
import com.zhenmin.superboot.constant.ModuleType;
import com.zhenmin.superboot.util.JsonUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class LogService {

	private final static String FORMAT = "'{}'.'{}'.'{}',{}";
	private final static String DEFAULT_IDX = "NA_IDX";
	private final static String DEFAULT_RESP_CODE = "NA_RESP_CODE";

	public void info(Class<?> clazz, ModuleType moduleType, Integer code, String idx, Object msg) {
		this.getLogger(clazz).info(FORMAT, idx, this.getModuleTypeDesc(moduleType), code, JsonUtil.writeValueQuite(msg));
	}

	public void info(Class<?> clazz, ModuleType moduleType, String idx, String msg) {
		this.getLogger(clazz).info(FORMAT, idx, this.getModuleTypeDesc(moduleType), DEFAULT_RESP_CODE, msg);
	}

	public void info(Class<?> clazz, ModuleType moduleType, String msg) {
		this.getLogger(clazz).info(FORMAT, DEFAULT_IDX, this.getModuleTypeDesc(moduleType), DEFAULT_RESP_CODE, msg);
	}

	public void info(Class<?> clazz, Object msg) {
		this.getLogger(clazz).info(FORMAT, DEFAULT_IDX, this.getModuleTypeDesc(ModuleType.DEFAULT), DEFAULT_RESP_CODE, JsonUtil.writeValueQuite(msg));
	}

	public void error(Class<?> clazz, SuperBootException e) {
		Logger logger = this.getLogger(clazz);
		logger.error(FORMAT, DEFAULT_IDX, this.getModuleTypeDesc(ModuleType.DEFAULT), e.getCode(), e.getMessage());
	}

	public void error(Class<?> clazz, Integer code, String msg) {
		Logger logger = this.getLogger(clazz);
		logger.error(FORMAT, DEFAULT_IDX, this.getModuleTypeDesc(ModuleType.DEFAULT), code, msg);
	}

	public void error(Class<?> clazz, Integer code, String msg, Throwable cause) {
		Logger logger = this.getLogger(clazz);
		logger.error(FORMAT, DEFAULT_IDX, this.getModuleTypeDesc(ModuleType.DEFAULT), code, msg);
		logger.error(cause.getMessage(), cause);
	}

	public void warn(Class<?> clazz, ModuleType moduleType, Integer secCode, String idx, Object msg) {
		this.getLogger(clazz).warn(FORMAT, idx, this.getModuleTypeDesc(moduleType), secCode, JsonUtil.writeValueQuite(msg));
	}

	public void debug(Class<?> clazz, ModuleType moduleType, Integer secCode, String idx, Object msg) {
		this.getLogger(clazz).debug(FORMAT, idx, this.getModuleTypeDesc(moduleType), secCode, JsonUtil.writeValueQuite(msg));
	}

	public void debug(Class<?> clazz, Object msg) {
		this.getLogger(clazz).debug(FORMAT, DEFAULT_IDX, this.getModuleTypeDesc(ModuleType.DEFAULT), DEFAULT_RESP_CODE, JsonUtil.writeValueQuite(msg));
	}

	private Logger getLogger(Class<?> clazz) {
		return LoggerFactory.getLogger(clazz);
	}

	private String getModuleTypeDesc(ModuleType moduleType) {
		return moduleType.ordinal() + "-" + moduleType.name();
	}
}
