package com.zhenmin.superboot.api.util;

import com.zhenmin.superboot.util.CollectionUtil;

import java.util.List;

/**
 * Created by xuzhenmin on 17-6-5.
 */
public abstract class ApiAllowIP {

	private static final List<String> INNER_IP_LIST_C3 = CollectionUtil.newArrayList();

	static {
		INNER_IP_LIST_C3.add("120.134.34.1-120.134.34.62");
		INNER_IP_LIST_C3.add("58.83.200.*");
	}

	public static final List<String> API_IP_LIST_SUPERBOOT = CollectionUtil.newArrayList();

	static {
		API_IP_LIST_SUPERBOOT.addAll(INNER_IP_LIST_C3);
	}

	public static final List<String> ALLOW_METHOD_LIST = CollectionUtil.newArrayList();

	static {
		ALLOW_METHOD_LIST.add("service.getValidServiceType");
		ALLOW_METHOD_LIST.add("service.checkWarranty");
	}
}
